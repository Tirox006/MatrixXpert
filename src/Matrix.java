public class Matrix {
    public double[][] matrix1=null;//pour le calcul d'inverse seulement

    public Matrix(double[][] matrix1,Type t)
    {
        this.matrix1=matrix1;
        this.t=t;
        this.size=matrix1.length;
    }

    public Matrix(int size,Type t,char d)
    {
        this.matrix1=new double[size][size];
        this.t=t;
        this.size=size;
    }

    public static Type VerifyType(float[][] matrix)
    {
        int sup=matrix.length-1;
        float x=matrix[0][sup];
        int inf=matrix.length-1;
        float y=matrix[inf][0];
        while((sup>0) && (x==0))
        {
            int i=0;
            while((i<matrix.length-sup) && (x==0))
            {
                x=matrix[i][sup+i];
                i++;
            }
            if(x==0)
            {
                sup--;

            }
        }

        while((inf>0) && (y==0))
        {
            int j=0;
            while((j<matrix.length-inf) && (y==0))
            {
                y=matrix[inf+j][j];
                j++;
            }
            if(y==0)
            {
                inf--;
             
            }
        }
        Type t= new Type(0,0);
        if((sup==matrix.length-1) && (inf==matrix.length-1))
        {   t.type=0;
            return t; //matrice dense
        }
        else 
        {
            if((sup==matrix.length-1) && (inf==0))
            {
                t.type=1;
                return t;  //matrice triangulair sup
            }
            else 
            {
                if((sup==0) && (inf==matrix.length-1))
                {
                    t.type=2;
                    return t;  //matrice triangulair inf
                }
                else 
                {
                    if((sup<matrix.length-1) && (inf==0))
                    {
                        t.type=3;
                        t.width=sup;
                        return t;  //matrice bande sup
                    }
                    else
                    {
                        if((sup==0) && (inf<matrix.length-1))
                        {
                            t.type=4;
                            t.width=inf;
                            return t;  //matrice bande inf
                        }
                        else
                        {
                            if((sup<matrix.length-1) && (sup==inf))
                            {
                                t.type=5;
                                t.width=sup;
                                return t; // matrice bande
                            }
                            else
                            {
                                return t; //matrice dense
                            }
                        }
                    }
                }

            }
        }
    }

    public float[][] matrix ;
    public Type t;
    public int size;
    public Matrix(float[][] matrix,Type t)
    {
        this.matrix=matrix;
        this.t=t;
        this.size=matrix.length;
    }

    public Matrix(int size,Type t)
    {
        this.matrix=new float[size][size];
        this.t=t;
        this.size=size;
    }
    




    public static boolean VerifySymetric(Matrix m)
    {
        int i,j;
        if(m.t.type==0)
        {
            i=0;
            while(i<m.size) 
            {
                j=0;
               
                while((j<m.size) && (m.matrix[i][j]==m.matrix[j][i]))
                {
                    j++;
                }
                if(j==m.size) 
                {
                    i++;
                }
                else {i=m.size+1;}
            }
            return (i==m.size);
        }
        else
        {
            if(m.t.type==5)
            {
                i=0;
                while(i<m.size)
                {
                    j=Math.max(0,i-m.t.width);
                   
                    while((j<Math.min(m.size,i+m.t.width)) && (m.matrix[i][j]==m.matrix[j][i]))
                    {
                        j++;
                    }
                    if(j==Math.min(m.size,i+m.t.width))
                    {
                        i++;
                    }
                    else {i=m.size+1;}
                }
                return (i==m.size);
            }
            else 
            {
                return false;
            }
        }
    }


    public static boolean VerifyPositive(Matrix m)
    {
        int i,j;
        float sum,diagonal;
        if(m.t.type==0)
        {
            i=0;
            while(i<m.size) 
            {
                j=0;
                sum=0;
                diagonal=Math.abs(m.matrix[i][i]);
                while((j<m.size) && ((diagonal>sum)))
                {
                    if(j!=i)
                    {
                        sum+=Math.abs(m.matrix[i][j]);
                    }
                    j++;
                }
                if(j==m.size)
                {
                    i++;
                }
                else {i=m.size+1;}
            }
            return (i==m.size);
        }
        else
        {
            if(m.t.type==5)
            {
                i=0;
                while(i<m.size)
                {
                    j=Math.max(0,i-m.t.width);
                    sum=0;
                    diagonal=Math.abs(m.matrix[i][i]);
                    while((j<Math.min(m.size,i+m.t.width)) && ((diagonal>sum)))
                    {
                        if(j!=i)
                        {
                            sum+=m.matrix[i][j];
                        }
                        j++;
                    }
                    if(j==Math.min(m.size,i+m.t.width))
                    {
                        i++;
                    }
                    else {i=m.size+1;}
                }
                return (i==m.size);
            }
            else 
            {
                return false;
            }
        }
    }










    public static Matrix calculate_Inverse(Matrix matrix) {
        int n = matrix.size;
        Matrix augmentedMatrix = augmentMatrix(matrix);

        // Appliquer la méthode de Gauss-Jordan
        for (int i = 0; i < n; i++) {
            // Normaliser la ligne i
            float pivot = augmentedMatrix.matrix[i][i];
            for (int j = 0; j < 2 * n; j++) {
                augmentedMatrix.matrix[i][j] /= pivot;
            }

            // Élimination des autres lignes
            for (int k = 0; k < n; k++) {
                if (k != i) {
                    float factor = augmentedMatrix.matrix[k][i];
                    for (int j = 0; j < 2 * n; j++) {
                        augmentedMatrix.matrix[k][j] -= factor * augmentedMatrix.matrix[i][j];
                    }
                }
            }
        }

        // Extraire la matrice inverse
        float[][] m = new float[n][n];
        Type t=new Type(0,0);
        Matrix inverseMatrix=new Matrix(m,t);
        
        for (int i = 0; i < n; i++) {
            System.arraycopy(augmentedMatrix.matrix[i], n, inverseMatrix.matrix[i], 0, n);
        }

        return inverseMatrix;
    }

    // Méthode pour augmenter une matrice avec une matrice identité
    public static Matrix augmentMatrix(Matrix matrix) {
        int n = matrix.size;
        float[][] m = new float[n][2 * n];
        Type t=new Type(0,0);
        Matrix augmentedMatrix= new Matrix(m,t);

        for (int i = 0; i < n; i++) {
            System.arraycopy(matrix.matrix[i], 0, augmentedMatrix.matrix[i], 0, n);
            augmentedMatrix.matrix[i][n + i] = 1.0f;
        }

        return augmentedMatrix;
    }
    public static float calculateDeterminant(Matrix matrix) {
     
        int size = matrix.size;

        if (size == 1) {
            return matrix.matrix[0][0];
        }

        if (size == 2) {
            return matrix.matrix[0][0] * matrix.matrix[1][1] - matrix.matrix[0][1] * matrix.matrix[1][0];
        }

        float determinant = 0;

        for (int col = 0; col < size; col++) {
            determinant += matrix.matrix[0][col] * cofactor(matrix, 0, col);
        }

        return determinant;
    }

    private static Matrix subMatrix(Matrix matrix, int rowToRemove, int colToRemove) {
        int size = matrix.size;
        float[][] m = new float[size - 1][size - 1];
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

                subMatrix.matrix[newRow][newCol] = matrix.matrix[i][j];
                newCol++;
            }

            newRow++;
        }

        return subMatrix;
    }

    private static float cofactor(Matrix matrix, int row, int col) {
        int sign = (row + col) % 2 == 0 ? 1 : -1;
        Matrix subMatrix = subMatrix(matrix, row, col);
        return sign * calculateDeterminant(subMatrix);
    }




    
}
