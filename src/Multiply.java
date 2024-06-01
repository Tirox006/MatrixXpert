class Multiply{
    // matrice * vecteur 

    public static Vector multiply (Matrix m, Vector v){
        Vector result =new Vector(v.size);
        switch (m.t.type) {
            case 0:
                for (int i = 0; i < v.size; i++) 
                {
                    for (int j = 0; j < v.size; j++) 
                    {
                    result.vect[i]=result.vect[i]+m.matrix[i][j]*v.vect[j];
                    }    
                }
                break;
            case 1:
                for (int i = 0; i < v.size; i++) 
                {
                    for (int j = i; j < v.size; j++)
                    {
                        result.vect[i]=result.vect[i]+m.matrix[i][j]*v.vect[j];
                    } 
                }
                break;
            case 2:
                for (int i = 0; i < v.size; i++) 
                {
                    for (int j = 0; j <= i; j++) 
                    {
                    result.vect[i]=result.vect[i]+m.matrix[i][j]*v.vect[j];
                    }
                }
                break;
            case 3:
                for( int i=0 ; i < v.size ; i++)
                {
                    for ( int j=i ; j< Math.min(i+m.t.width+1, v.size);j++)
                    {
                        result.vect[i]=result.vect[i]+m.matrix[i][j]*v.vect[j]; 
                    }
                }
                break;
            case 4:
                for(int i=0 ; i < v.size ; i++)
                {
                    for (int j=Math.max(0, i-m.t.width) ; j<=i ; j++)
                    {
                        result.vect[i]=result.vect[i]+m.matrix[i][j]*v.vect[j];
                    }
                }
                break;
            default:
                for (int i = 0; i < v.size; i++) 
                {
                    for (int j = 0; j < v.size; j++) 
                    {
                    result.vect[i]=result.vect[i]+m.matrix[i][j]*v.vect[j];
                    }    
                }
                break;
        }
        return result;    
    }

    //matrice * matrice


    public static Matrix multiply (Matrix m1, Matrix m2)
    {
        Matrix result = new Matrix(m1.size, null);
        if(((m1.t.type==5) && (m2.t.type==4)))
        {/*Bande * bande inf */
            for (int i = 0; i < m1.size; ++i) {
                for (int j = 0; j < m1.size; ++j) {
                    for (int k = Math.max(0, i - m1.t.width); k <= Math.min(m1.size - 1, i + m1.t.width); ++k) {
                        result.matrix[i][j] += m1.matrix[i][k] * m2.matrix[k][j];
                    }
                }}
          
        }
        else
        { /*BANDE INF * bande sup */
            if(((m1.t.type==4) && (m2.t.type==3)))
            {
                for (int i = 0; i < m1.size; ++i) {
                    for (int j = 0; j < m1.size; ++j) {

                        for (int k = Math.max(0, i - m1.t.width); k <= Math.min(m1.size - 1, i); ++k) {
                            result.matrix[i][j] += m1.matrix[i][k] * m2.matrix[k][j];
                        }
                    }
                }
            }
            else
            {
                    /*BANDE * TR */
                    if((m1.t.type==5) && (Transpose(m1.matrix,m2.matrix)==true)) 
                    {
                        for (int i = 0; i < m1.size; ++i) {
                            for (int j = 0; j < m1.size; ++j) {
                                for (int k = Math.max(0, i - m1.t.width); k <= Math.min(m1.size - 1, i + m1.t.width); ++k) {
                                    result.matrix[i][j] += m1.matrix[i][k] * m1.matrix[j][k];
                                }
                            }
                    }}
                    else{
                        /*DENSE * DENSE */
                        for(int i=0;i<m1.size;i++){
                            for(int j=0;j<m2.size;j++){
                                for(int k=0;k<m1.size;k++){
                                    result.matrix[i][j]+=m1.matrix[i][k]*m2.matrix[k][j];
                                }
                            }
                        }
                    }
                    
                
            }
        }
        return result;  
    }
    public static boolean Transpose (float[][] m1,float [][] m2){
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m1.length; j++) {
                if (m1[i][j]!=m2[j][i]) {
                    return false;
                }
            }
        }
        return true ;
    }
public static Matrix multiplyInv(Matrix m,Matrix inv){

    Type t=new Type(0, 0);
    Matrix result=new Matrix(m.size,t,'d');
    for (int i = 0; i < m.size; ++i) {
        for (int j = 0; j < m.size; ++j) {
            for (int k = Math.max(0, i - m.t.width); k <= Math.min(m.size - 1, i + m.t.width); ++k) {
                result.matrix1[i][j] += m.matrix1[i][k] * inv.matrix1[k][j];
                result.matrix1[i][j]= (double) (Math.round(result.matrix1[i][j]*1000.0)/1000.0);

            }
        }
    }
    return result;
}






}

