package Core;

public class RegionScore {
    private static final int column = 8;
    private static final int row = 8;
    private static int[][] regionScore = new int[row + 2][column + 2];

    private static void setRegionScore() {
        int[][] temp =
                {
                        {4, -3, 2, 2, 2, 2, -3, 4},
                        {-3, -4, -1, -1, -1, -1, -4, -3},
                        {2, -1, 1, 0, 0, 1, -1, 2},
                        {2, -1, 0, 1, 1, 0, -1, 2},
                        {2, -1, 0, 1, 1, 0, -1, 2},
                        {2, -1, 1, 0, 0, 1, -1, 2},
                        {-3, -4, -1, -1, -1, -1, -4, -3},
                        {4, -3, 2, 2, 2, 2, -3, 4}
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