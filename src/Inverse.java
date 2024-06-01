
public class Inverse {
    public static Matrix calculateInverse(Matrix matrix) {
        int n = matrix.size;
        Matrix augmentedMatrix = augmentMatrix(matrix);

        // Apply the Gauss-Jordan elimination method
        for (int i = 0; i < n; i++) {
            // Normalize the row i
            double pivot = augmentedMatrix.matrix1[i][i];

            // Handle division by zero or very small numbers
            if (Math.abs(pivot) < 1e-8) {
                pivot = 1e-8;
            }

            for (int j = 0; j < 2 * n; j++) {
                augmentedMatrix.matrix1[i][j] /= pivot;
            }

            // Elimination of other rows
            for (int k = 0; k < n; k++) {
                if (k != i) {
                    double factor = augmentedMatrix.matrix1[k][i];
                    for (int j = 0; j < 2 * n; j++) {
                        augmentedMatrix.matrix1[k][j] -= factor * augmentedMatrix.matrix1[i][j];
                    }
                }
            }
        }

        // Extract the inverse matrix
        double[][] m = new double[n][n];
        Type t = new Type(0, 0);
        Matrix inverseMatrix = new Matrix(m, t);

        for (int i = 0; i < n; i++) {
            System.arraycopy(augmentedMatrix.matrix1[i], n, inverseMatrix.matrix1[i], 0, n);
        }

        for(int i = 0; i < n; i++)
         for(int j = 0; j < n; j++)
        {
           inverseMatrix.matrix1[i][j]= (double) (Math.round(inverseMatrix.matrix1[i][j]*1000.0)/1000.0);
        }

        return inverseMatrix;
    }

    // Method to augment a matrix with an identity matrix
    private static Matrix augmentMatrix(Matrix matrix) {
        int n = matrix.size;
        double[][] m = new double[n][2 * n];
        Type t = new Type(0, 0);
        Matrix augmentedMatrix = new Matrix(m, t);

        for (int i = 0; i < n; i++) {
            System.arraycopy(matrix.matrix1[i], 0, augmentedMatrix.matrix1[i], 0, n);
            augmentedMatrix.matrix1[i][n + i] = 1.0;
        }

        return augmentedMatrix;
    }

    public static double[][] floatToDouble(float[][] m)
    {
        double[][] result=new double[m.length][m.length];
        for(int i=0;i<m.length;i++)
        for(int j=0;j<m.length;j++)
        {
        result[i][j]=m[i][j];
        }
    return result;
    }


}