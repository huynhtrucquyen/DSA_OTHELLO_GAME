package GUI;

import Core.Coordinate;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class RenderChess extends javax.swing.JFrame {
    private Coordinate position = new Coordinate();
    private static RenderChess instance;

    private RenderChess() {

    }


    public void playerIcon(Graphics g) {
        BufferedImage player1Icon = null, player2Icon = null, iconWhite = null, iconBlack = null;
        try {
            player1Icon = ImageIO.read(new File(Parameter.iconplayer1));
            player2Icon = ImageIO.read(new File(Parameter.iconplayer2));
            iconWhite = ImageIO.read(new File(Parameter.iconwhite));
            iconBlack = ImageIO.read(new File(Parameter.iconblack));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(player1Icon, 15, Parameter.yStart + 160, null);
        g.drawImage(player2Icon, Parameter.xStart + 650, Parameter.yStart + 160, null);
        g.drawImage(iconBlack, 15, Parameter.yStart + 270, null);
        g.drawImage(iconWhite, Parameter.xStart + 640, Parameter.yStart + 270, null);

    }


    public void paintScore(Graphics g, int p1Score, int p2Score) {

        g.setColor(Color.WHITE);
        g.setFont(new Font("Tw Cen MT", Font.BOLD, 35));

        g.drawString(Parameter.player1, 20, Parameter.yStart + 30);
        g.drawString(Parameter.player2, Parameter.xStart + 645, Parameter.yStart + 30);


        g.setColor(Color.WHITE);
        g.setFont(new Font("Comic Sans MS", Font.BOLD, 50));

        g.drawString("" + p1Score, 95, Parameter.yStart + 300);
        g.drawString("" + p2Score, Parameter.xStart + 720, Parameter.yStart + 300);

    }

    public void paintCurrentMove(Graphics g, int step) {
        BufferedImage thinking = null;
        try {
            thinking = ImageIO.read(new File(Parameter.thinking));
        } catch (IOException e) {
            e.printStackTrace();
        }

        g.setColor(Color.white);
        g.setFont(new Font("Uni Sans", Font.BOLD, 35));

        String move = "WHITE";
        if (step == 1)
        {   move = "BLACK";
        g.drawString(move + " TURN ", 380, 50);
        g.drawImage(thinking, 35, Parameter.yStart + 50, null);
        }
        else {
            g.drawString(move + " TURN ", 420, 50);
            g.drawImage(thinking, Parameter.xStart + 650, Parameter.yStart + 50, null);}

    }

    public void paintChess(Graphics g, ArrayList<Coordinate> board) {
        BufferedImage player1Image = null, player2Image = null, suggestImage = null;
        try {
            player1Image = ImageIO.read(new File(Parameter.blackChess));
            player2Image = ImageIO.read(new File(Parameter.whiteChess));
            suggestImage = ImageIO.read(new File(Parameter.suggest));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Coordinate v : board) {
            BufferedImage image = null;
            int x = v.x - 1, y = v.y - 1;
            if (v.type == 1)
                image = player1Image;
            else if (v.type == 2)
                image = player2Image;
            else if (v.type == 3)
                image = suggestImage;
            g.drawImage(image, Parameter.xStart + Parameter.stepSize * x,
                    Parameter.yStart + Parameter.stepSize * y, Parameter.width, Parameter.height, null);
        }
    }

    public static RenderChess instance() {
        if (instance == null) {
            instance = new RenderChess();
        }
        return instance;
    }

}
