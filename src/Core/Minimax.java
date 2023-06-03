package Core;

import GUI.Parameter;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.max;
import static java.lang.Math.min;


public class Minimax {
    private final int row = Parameter.row;
    private final int MIN_PLAYER = 2;
    private final int MAX_PLAYER = 1;
    private final int column = Parameter.column;
    private final int maxDepth = 8;
    private static  int[][] regionScore = RegionScore.getRegionScore();

    private int[][] copyBoard(int[][] board, GamePlay gamePlay, int x, int y, int player) {
        int[][] arr = new int[row + 2][column + 2];

        for (int i = 1; i <= row; i++)
            System.arraycopy(board[i], 1, arr[i], 1, column);
        arr[x][y] = player;
        if (player == 1) player = 2; else player = 1;
        gamePlay.flipChess(arr, player, x, y);
        return arr;
    }

    public int decision(int depth, int[][] board, int curPlayer, int alpha, int beta) {
        GamePlay gamePlay = GamePlay.getInstance();
        int nextPlayer = 3 - curPlayer;
        if (depth == maxDepth || gamePlay.checkEndGame(board)) {
            return eval(board, curPlayer);
        }

        // get position can move
        gamePlay.checkPosibleMove(board, nextPlayer);
        ArrayList<cond> move = gamePlay.arrPosibleMove;

        if (move.size() == 0)
            return decision(depth + 1, board, nextPlayer, alpha, beta);
        int bestmove;
        bestmove = curPlayer == 2 ? 100000 : -100000;
        Random rand = new Random();
        for (cond v : move) {
            int val = decision(depth + 1, copyBoard(board, gamePlay, v.x, v.y, nextPlayer), nextPlayer, alpha, beta);
            if (curPlayer == 2) {
                bestmove = min(bestmove, val);
                beta = min(beta, bestmove);
                if (alpha >= beta) {
                    break;
                }
            }
            else {
                bestmove = max(bestmove, val);
                alpha = max(alpha, bestmove);

                if (alpha >= beta) {
                    break;
                }
            }
        }
        return bestmove;
    }

    public cond decision(int x, int y, int[][] board) {

        GamePlay gamePlay = GamePlay.getInstance();
        gamePlay.checkPosibleMove(board, 2);
        ArrayList<cond> moveH = gamePlay.arrPosibleMove;

        cond bestMove = new cond();
        int bestVal = -100000;
        bestMove.x = 1000;
        bestMove.y = 1000;
        for (cond v : moveH) {
            int val = decision(2, copyBoard(board, gamePlay, v.x, v.y, 2), 2, -100000, 100000);
            if (val > bestVal ) {
                bestMove.x = v.x;
                bestMove.y = v.y;
                bestVal = val;
            }
        }
//        System.out.println(bestVal);
        return bestMove;
    }

    private int opponent_mobility(int[][] arr, int turn) {
//        int ret = 0;
        GamePlay gamePlay = GamePlay.getInstance();
        gamePlay.checkPosibleMove(arr, turn);
        ArrayList<cond> moveH = gamePlay.arrPosibleMove;

//        for (cond u: moveH) {
//            ret += regionScore[u.x][u.y];
//        }

        return moveH.size();
    }

    private int mobility(int[][] arr, int turn) {
        int mobility = 0;
        GamePlay gamePlay = GamePlay.getInstance();
        gamePlay.checkPosibleMove(arr, turn);
        ArrayList<cond> moveH = gamePlay.arrPosibleMove;
        mobility = moveH.size();

////        for (cond v: moveH) {
////            mobility += regionScore[v.x][v.y];
////        }
//
//        int opponet_mobility = -100000;
//
//        //best opponent's mobility
//        for (cond v: moveH) {
//            opponet_mobility = max(opponet_mobility,  opponent_mobility(copyBoard(arr, gamePlay, v.x, v.y, turn), 3-turn));
//        }
//
//        mobility -= opponet_mobility;

        return mobility;
    }

    private int stability(int[][] arr, int x, int y) {
        int player = arr[x][y];
        int opponent = 3 - player;
        int ret = 0;

        if (x == 1 || x == row) {
            ret += 2;
        }

        if (y == 1 || y == row) {
            ret += 2;
        }

        int check = 1;

        //check up
        for (int i = x-1 ; i >= 1 ; i--) {
            if (arr[i][y] == opponent) {
                check = 0;
            }
        }

        //check down
        for (int i = 1 ; i < x ; i++) {
            if (arr[i][y] == opponent) {
                check = 0;
                break;
            }
        }
        ret += check;

        //check left
        check = 1;
        for (int i = 1 ; i < y ; i++) {
            if (arr[x][i] == opponent) {
                check = 0;
                break;
            }
        }

        //check right
        for (int i = y + 1 ; i <= column ; i++) {
            if (arr[x][i] == opponent) {
                check = 0;
                break;
            }
        }

        check = 1;

        //check up-right
        for (int i = 1 ; x - i >= 1 && y + i <= column ; i++) {
            if (arr[x - i][y + i] == opponent) {
                check = 0;
                break;
            }
        }
        // check down-left
        for (int i = 1 ; x + i <= row && y - i >= 1 ; i++) {
            if (arr[x + i][y - i] == opponent) {
                check = 0;
                break;
            }
        }

        ret += check;

        check = 1;
        //check down-right
        for (int i = 1 ; x + i <= row && y + i <= column ; i++) {
            if (arr[x+i][y+i] == opponent) {
                check = 0;
                break;
            }
        }

        //check up-left
        for (int i = 1 ; x - i >= 1 && y - i >= 1 ; i++) {
            if (arr[x-i][y-i] == opponent) {
                check = 0;
                break;
            }
        }

        ret += check;
        if (player == 2) {
            ret *= -1;
        }
        return ret;
    }

    private int eval(int[][] arr, int turn) {
        // disc count
        int count = 0;
        for (int i = 1 ; i <= row ; i++) {
            for (int j = 1 ; j <= column ; j++) {
                if (arr[i][j] == 1) {
                    count += 1;
                }
                if (arr[i][j] == 2) {
                    count -= 1;
                }
            }
        }

        //corner
        int corner = 0;
        for (int i = 1; i <= row; i++)
            for (int j = 1; j <= column; j++)
                if (arr[i][j] == 2) corner -= regionScore[i][j];
                else corner += regionScore[i][j];

        //mobility
        int mobility = mobility(arr, turn);
        if (turn == 2) {
            mobility *= -1;
        }

        //stability
        int stability = 0;

        for (int i = 1 ; i <= row ; i++) {
            for (int j = 1 ; j <= column ; j++) {
                if (arr[i][j] == -1) {
                    continue;
                }
                stability += stability(arr, i, j);
            }
        }

        return -(count + mobility * 5 + corner * 10 + stability * 20);
    }

    public cond getOptimalMove(int[][] board, int x, int y) {
        return decision(x, y, board);
    }

    public static Minimax getInstance() {
        return new Minimax();
    }
}