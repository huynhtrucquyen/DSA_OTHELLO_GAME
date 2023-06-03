package Core;

public class RegionScore {
    private static final int column = 8;
    private static final int row = 8;
    private static int[][] regionScore = new int[row + 2][column + 2];

    private static void setRegionScore() {
        int[][] temp =
                {
                        {120, -20, 20,  5,  5, 20, -20, 120},
                        {-20, -40, -5, -5, -5, -5, -40, -20},
                        {20,  -5, 15,   3,  3, 15,  -5,  20},
                        {5,   -5,  3,   3,  3,  3,  -5,   5},
                        {5,   -5,  3,   3,  3,  3,  -5,   5},
                        {20,  -5, 15,   3,  3, 15,  -5,  20},
                        {-20, -40, -5, -5, -5, -5, -40, -20},
                        {120, -20, 20,  5,  5, 20, -20, 120}
                };
        for (int i = 1; i <= row; i++)
            for (int j = 1; j <= column; j++)
                regionScore[i][j] = temp[i - 1][j - 1];
    }

    public static int[][] getRegionScore() {
        setRegionScore();
        return regionScore;
    }
}