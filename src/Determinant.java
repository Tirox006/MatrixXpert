
public class Determinant {
	public static double calculateDeterminant(Matrix matrix) {
     
        int size = matrix.size;

        if (size == 1) {
            return matrix.matrix1[0][0];
        }

        if (size == 2) {
            return matrix.matrix1[0][0] * matrix.matrix1[1][1] - matrix.matrix1[0][1] * matrix.matrix1[1][0];
        }

        double determinant = 0;

        for (int col = 0; col < size; col++) {
            determinant += matrix.matrix1[0][col] * cofactor(matrix, 0, col);
        }

        return determinant;
    }

    private static Matrix subMatrix(Matrix matrix, int rowToRemove, int colToRemove) {
        int size = matrix.size;
        double[][] m = new double[size - 1][size - 1];
        Type t=new Type(0,0);
        Matrix subMatrix=new Matrix(m,t);

        for (int i = 0, newRow = 0; i < size; i++) {
            if (i == rowToRemove) {
                continue;
            }

            for (int j = 0, newCol = 0; j < size; j++) {
                if (j == colToRemove) {
                    continue;
                }

                subMatrix.matrix1[newRow][newCol] = matrix.matrix1[i][j];
                newCol++;
            }

            newRow++;
        }

        return subMatrix;
    }

    private static double cofactor(Matrix matrix, int row, int col) {
        int sign = (row + col) % 2 == 0 ? 1 : -1;
        Matrix subMatrix = subMatrix(matrix, row, col);
        return sign * calculateDeterminant(subMatrix);
    }
}
