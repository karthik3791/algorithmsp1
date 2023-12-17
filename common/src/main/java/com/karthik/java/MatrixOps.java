package com.karthik.java;

public class MatrixOps {

    private void printGrid(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println("");
        }
    }


    public void rotate(int[][] matrix) {
        //grid[r][c] swap with grid[c][n-r-1]

    }

    public void transposeMainDiagonal(int[][] sqMatrix) {
        for (int i = 0; i < sqMatrix.length; i++)
            for (int j = 0; j <= i; j++) {
                int tmp = sqMatrix[i][j];
                sqMatrix[i][j] = sqMatrix[j][i];
                sqMatrix[j][i] = tmp;
            }
    }

    public void transposeAntiDiagonal(int[][] sqMatrix) {
        for (int i = 0; i < sqMatrix.length; i++)
            for (int j = sqMatrix.length - 2 - i; j >= 0; j--) {
                int tmp = sqMatrix[i][sqMatrix.length - 1 - j];
                sqMatrix[i][sqMatrix.length - 1 - j] = sqMatrix[sqMatrix.length - 1 - j][i];
                sqMatrix[sqMatrix.length - 1 - j][i] = tmp;
            }
    }

    public static void main(String[] args) {
        MatrixOps op = new MatrixOps();

        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        op.printGrid(matrix);
        op.transposeAntiDiagonal(matrix);
        op.printGrid(matrix);
    }
}
