public class Resolution {

public static Vector GaussJordan(Matrix m, Vector v)
{
    //Add second membre

    float[][] matrix;
    matrix = new float[v.size][v.size+1];

    if(m.t.type==0)  //matrice dense
    {
    for(int i=0;i<v.size;i++)
    {
        for(int j=0;j<v.size;j++)
        {
            matrix[i][j]=m.matrix[i][j]; 
        }
    }

    for(int i=0;i<v.size;i++)
    {
        matrix[i][v.size]=v.vect[i];
    }

    //resolution
    for (int k=0;k<v.size;k++) 
    {
        for(int j=k+1;j<v.size+1;j++) 
        {
            matrix[k][j]/=matrix[k][k];
        }

        for(int i=0;i<k;i++)
        {
            for(int j=k+1;j<v.size+1;j++)
            {
                matrix[i][j]=matrix[i][j]-matrix[i][k]*matrix[k][j];
            }
        }
        for(int i=k+1;i<v.size;i++)
        {
            for(int j=k+1;j<v.size+1;j++)
            {
                matrix[i][j]=matrix[i][j]-matrix[i][k]*matrix[k][j];
            }
        }
    }} //fin cas matrice dense
    else
    { 
        if(m.t.type==5) // matrice bande
        {
            for (int i=0;i<=m.t.width;i++) 
            {
                for (int j=0;j<=i+m.t.width;j++) 
                {
                    matrix[i][j] = m.matrix[i][j];

                }
            }

            for (int i=m.t.width+1;i<m.size;i++) 
            {
                for (int j=i-m.t.width;j<m.size;j++) 
                {
                matrix[i][j] = m.matrix[i][j];

                }
            }

            for(int i=0;i<v.size;i++)
            {
                matrix[i][v.size]=v.vect[i];
            }

            //resolution
            for (int k=0;k<v.size;k++) 
            {
                for(int j=k+1;j<Math.min(k+m.t.width+1,m.size);j++) 
                {
                    matrix[k][j]/=matrix[k][k];
                }
                matrix[k][v.size]/=matrix[k][k];

                for(int i=0;i<k;i++)
                {
                    for(int j=k+1;j<Math.min(k+m.t.width+1,m.size);j++) 
                    {
                        matrix[i][j]=matrix[i][j]-matrix[i][k]*matrix[k][j];
                    }
                    matrix[i][v.size]=matrix[i][v.size]-matrix[i][k]*matrix[k][v.size];
                }
                for(int i=k+1;i<v.size;i++)
                {
                    for(int j=k+1;j<Math.min(k+m.t.width+1,m.size);j++) 
                    {
                        matrix[i][j]=matrix[i][j]-matrix[i][k]*matrix[k][j];
                    }
                    matrix[i][v.size]=matrix[i][v.size]-matrix[i][k]*matrix[k][v.size];
                }
            }
        } 
    }//fin cas matrice dense

    Vector vec= new Vector(v.size);
    for(int i=0;i<v.size;i++)
    {
        vec.vect[i]=matrix[i][v.size];
    }

    for(int i=0;i<v.size;i++)
        {
            vec.vect[i]= (float) (Math.round(matrix[i][v.size]*1000.0)/1000.0);
        }
return vec;
}




public static Vector GaussResolution(Matrix m,Vector v)
{
    //Add second membre

    float[][] matrix;
    matrix = new float[v.size][v.size+1];
    if(m.t.type==0)
    {
        for(int i=0;i<v.size;i++)
        {
            for(int j=0;j<v.size;j++)
            {
                matrix[i][j]=m.matrix[i][j]; 
            }
        }

        for(int i=0;i<v.size;i++)
        {
            matrix[i][v.size]=v.vect[i];
        }

        //resolution
        for (int k=0;k<v.size-1;k++) 
        {   
            for(int i=k+1;i<v.size;i++) 
            {
                matrix[i][k]/=matrix[k][k];
                for(int j=k+1;j<v.size+1;j++)
                {
                    matrix[i][j]=matrix[i][j]-matrix[i][k]*matrix[k][j];
                }
            }
        }

        for(int i=v.size-1;i>=0;i--)
        {
            for(int j=i+1;j<v.size;j++)
            {
                matrix[i][v.size]-=matrix[i][j]*matrix[j][v.size];
            }
            matrix[i][v.size]=matrix[i][v.size]/matrix[i][i];
        }
    }
    else 
    {
        if(m.t.type==5)
        {   
            for (int i=0;i<=m.t.width;i++) 
            {
                for (int j=0;j<=i+m.t.width;j++) 
                {
                    matrix[i][j] = m.matrix[i][j];

                }
            }

            for (int i=m.t.width+1;i<m.size;i++) 
            {
                for (int j=i-m.t.width;j<m.size;j++) 
                {
                matrix[i][j] = m.matrix[i][j];

                }
            }

            for(int i=0;i<v.size;i++)
            {
                matrix[i][v.size]=v.vect[i];
            }

            //resolution
            for (int k=0;k<m.size;k++) 
            {
                for(int i = k+1;i<Math.min(k+m.t.width+1,m.size) ;i++)  
                {
                    matrix[i][k]/=matrix[k][k];
                    for(int j = k+1;j<Math.min(k+m.t.width+1,m.size); j++)
                    {
                        matrix[i][j]=matrix[i][j]-matrix[i][k]*matrix[k][j];
                    }
                    matrix[i][v.size]=matrix[i][v.size]-matrix[i][k]*matrix[k][v.size];

                }
            }

            for(int i=v.size-1;i>=0;i--)
            {
                for(int j = i+1;j<Math.min(i+m.t.width+1,m.size); j++)
                {
                    matrix[i][v.size]-=matrix[i][j]*matrix[j][v.size];
                }
                matrix[i][v.size]=matrix[i][v.size]/matrix[i][i];
            }
        }
    }

    Vector vec= new Vector(v.size);
    for(int i=0;i<v.size;i++)
    {
        vec.vect[i]=matrix[i][v.size];
    }

    for(int i=0;i<v.size;i++)
        {
            vec.vect[i]= (float) (Math.round(matrix[i][v.size]*1000.0)/1000.0);
        }
return vec;
}


public static float[][] ChangePivot(float[][] matrix,int k)
{
    float max=matrix[k][k];
    float[] aux;
    int x=k;
    for(int i=k+1;i<matrix.length;i++)
    {
        if (Math.abs(matrix[i][k]) > Math.abs(max))
        {
            max=matrix[i][k];
            x=i;
        }
    }
    if(x!=k)
    {
        aux=matrix[x];
        matrix[x]=matrix[k];
        matrix[k]=aux;
    }
    return matrix;
}

public static void ChangePivotBande(Matrix m,Vector v,int k)
{
    float[] aux;
    int x=k;
    for(int i=k+1;i<m.size;i++)
    {
        if (Math.abs(m.matrix[i][k]) > Math.abs(m.matrix[x][k]))
        {
            x=i;
        }
    }
    if(x!=k)
    {
        aux=m.matrix[x];
        m.matrix[x]=m.matrix[k];
        m.matrix[k]=aux;

        float aux2 = v.vect[k];
        v.vect[k] = v.vect[x];
        v.vect[x] = aux2;   
    }

}



public static Vector GaussPivotage(Matrix m,Vector v)
{
    //Add second membre

    float[][] matrix;
    matrix = new float[v.size][v.size+1];
    Vector vec= new Vector(v.size);
    if(m.t.type==0)
    {
        for(int i=0;i<v.size;i++)
        {
            for(int j=0;j<v.size;j++)
            {
                matrix[i][j]=m.matrix[i][j]; 
            }
        }

        for(int i=0;i<v.size;i++)
        {
            matrix[i][v.size]=v.vect[i];
        }

        //resolution
        for (int k=0;k<v.size-1;k++) 
        {
            matrix=ChangePivot(matrix, k);
            
            for(int i=k+1;i<v.size;i++) 
            {
                matrix[i][k]/=matrix[k][k];
                for(int j=k+1;j<v.size+1;j++)
                {
                    matrix[i][j]=matrix[i][j]-matrix[i][k]*matrix[k][j];
                }
            }
        }

        for(int i=v.size-1;i>=0;i--)
        {
            for(int j=i+1;j<v.size;j++)
            {
                matrix[i][v.size]-=matrix[i][j]*matrix[j][v.size];
            }
            matrix[i][v.size]=matrix[i][v.size]/matrix[i][i];
        }
        for(int i=0;i<v.size;i++)
        {
            vec.vect[i]=matrix[i][v.size];
        }

        for(int i=0;i<v.size;i++)
        {
            vec.vect[i]= (float) (Math.round(matrix[i][v.size]*1000.0)/1000.0);
        }
    }
    else 
    {
        if(m.t.type==5)
        {   
            for (int k=0;k<m.size-1;k++) 
            {
                ChangePivotBande(m, v, k);
                for(int i = k+1;i<Math.min(k+m.t.width+1,m.size) ;i++)  
                {
                    m.matrix[i][k]/=m.matrix[k][k];
                    for(int j = k+1;j<Math.min(k+m.t.width+1,m.size); j++)
                    {
                        m.matrix[i][j]=matrix[i][j]-m.matrix[i][k]*m.matrix[k][j];
                    }
                    v.vect[i]-=m.matrix[i][k]/=m.matrix[k][k]* v.vect[k];
                }
            }

            for(int i=v.size-1;i>=0;i--)
            {
                vec.vect[i]=v.vect[i];
                for(int j = i+1;j<Math.min(i+m.t.width+1,m.size); j++)
                {
                   vec.vect[i]-=matrix[i][j]*vec.vect[j];
                }
                vec.vect[i]/=m.matrix[i][i];
            }
        }
    }
return vec;
}





public static Vector Choleskey(Matrix m,Vector v)
{
    Vector y=new Vector(m.size);
	Vector x=new Vector(m.size);
    if(m.t.type==0)
    {
        Type t1=new Type(2,0);
	    Matrix L=new Matrix(m.size,t1);
	    Type t2=new Type(1,0);
	    Matrix TL=new Matrix(m.size,t2);
    for(int j=0;j<m.size;j++)
    {
        L.matrix[j][j]=m.matrix[j][j];
        for(int k=0;k<j;k++)
        {
            L.matrix[j][j]-= L.matrix[j][k]* L.matrix[j][k];
        }
        L.matrix[j][j]= (float)Math.sqrt(L.matrix[j][j]);

        for(int i=j+1;i<m.size;i++)
        {
            L.matrix[i][j]=m.matrix[i][j];
            for(int k=0;k<j;k++)
            {
               L.matrix[i][j]-=L.matrix[i][k]*L.matrix[j][k];
            }
            L.matrix[i][j]=L.matrix[i][j]/L.matrix[j][j];
        }
    }

    for(int i=0;i<L.size;i++)
    {
        for(int j=i;j<L.size;j++)
        {
            TL.matrix[i][j]=L.matrix[j][i];

        }
    }
    y=ResolutionLinear.resolution_inf(L,v);
	x=ResolutionLinear.resolution_sup(TL,y);

    }
    else
    {
        if(m.t.type==5)
        {
            Type t1=new Type(4,m.t.width);
	        Matrix L=new Matrix(m.size,t1);
	        Type t2=new Type(3,m.t.width);
	        Matrix TL=new Matrix(m.size,t2);

            for(int j=0;j<m.size;j++)
            {
               L.matrix[j][j]=m.matrix[j][j];
                for(int k=Math.max(0,j-m.t.width);k<j;k++)
                {
                   L.matrix[j][j]-=L.matrix[j][k]*L.matrix[j][k];
                }
               L.matrix[j][j]= (float)Math.sqrt( L.matrix[j][j]);

                for(int i=j+1;i<Math.min(m.size,j+m.t.width+1);i++)
                {
                   L.matrix[i][j]=m.matrix[i][j];
                    for(int k=0;k<j;k++)
                    {
                       L.matrix[i][j]-= L.matrix[i][k] * L.matrix[j][k];
                    }
                   L.matrix[i][j]= L.matrix[i][j]/L.matrix[j][j];

                }
            }


            for(int i=0;i<L.size;i++)
            {
                for(int j=i;j<Math.min(L.size,j+m.t.width+1);j++)
            {
                TL.matrix[i][j]=L.matrix[j][i];
  
            }
        }
        y=ResolutionLinear.resolution_inf_bande(L,v);
	    x=ResolutionLinear.resolution_sup_bande(TL,y);

        }
    }
    for(int i=0;i<v.size;i++)
        {
            x.vect[i]= (float) (Math.round(x.vect[i]*1000.0)/1000.0);
        }
    return x;
}


//jacobi
public static Vector Jacobi(Matrix m, Vector v)
{
    Vector x= new Vector(v.size);
    Vector y= new Vector(v.size);
    float max;
    float s;
    int nn=0;
    for(int i=0;i<m.size;i++)
    {
        y.vect[i]=0;
    }
    if(m.t.type==0)
    {
        do 
        {
            for(int i=0;i<m.size;i++)
            {
                x.vect[i]=y.vect[i];
            }
            for (int i = 0; i < m.size; i++) 
            {
                s = v.vect[i];
                for (int j = 0; j < m.size; j++) 
                {
                    if (j != i) 
                    {
                        s -= m.matrix[i][j] * x.vect[j];
                    }
                }
                y.vect[i] = s / m.matrix[i][i];
            }
            max = calculateError(x, y);
            nn++;
        }
        while(max>(float) 1e-10);
    }
    else
    {
        if(m.t.type==5)
        {
            do 
            {
                for(int i=0;i<m.size;i++)
                {
                    x.vect[i]=y.vect[i];
                }
                for (int i = 0; i < m.size; i++) 
                {
                    s = v.vect[i];
                    for (int j = Math.max(0,i-m.t.width); j < Math.min(m.size,i+m.t.width+1); j++) 
                    {
                        if (j != i) 
                        {
                            s -= m.matrix[i][j] * x.vect[j];
                        }
                    }   
                    y.vect[i] = s / m.matrix[i][i];
                }
                max = calculateError(x, y);
                nn++;

            }
            while(max>(float) 1e-10);
        }
    }
    for(int i=0;i<v.size;i++)
        {
            y.vect[i]= (float) (Math.round(y.vect[i]*1000.0)/1000.0);
        }
    return y;
}

// Jacobi when there is max iteration
public static Vector Jacobi(Matrix m, Vector v, int MaxIterations)
{
    Vector x= new Vector(v.size);
    Vector y= new Vector(v.size);
    float s;
    for(int i=0;i<m.size;i++)
    {
        y.vect[i]=0;
    }
    if(m.t.type==0)
    {
       for(int k=0;k<MaxIterations;k++)
       {
            for(int i=0;i<m.size;i++)
            {
                x.vect[i]=y.vect[i];
            }
            for (int i = 0; i < m.size; i++) 
            {
                s = v.vect[i];
                for (int j = 0; j < m.size; j++) 
                {
                    if (j != i) 
                    {
                        s -= m.matrix[i][j] * x.vect[j];
                    }
                }
                y.vect[i] = s / m.matrix[i][i];
            }
        }
    }
    else
    {
        if(m.t.type==5)
        {
            for(int k=0;k<MaxIterations;k++)
            {
                for(int i=0;i<m.size;i++)
                {
                    x.vect[i]=y.vect[i];
                }
                for (int i = 0; i < m.size; i++) 
                {
                    s = v.vect[i];
                    for (int j = Math.max(0,i-m.t.width); j < Math.min(m.size,i+m.t.width+1); j++) 
                    {
                        if (j != i) 
                        {
                            s -= m.matrix[i][j] * x.vect[j];
                        }
                    }   
                    y.vect[i] = s / m.matrix[i][i];
                }
            }
        }
    }
    return y;
}


// Gauss Seidel
public static Vector GaussSeidel(Matrix m, Vector v)
{
    Vector x= new Vector(v.size);
    float s;
    float max;
    if(m.t.type==0)
    {
        for(int i=0;i<m.size;i++)
        {
            x.vect[i]=0;
        }

        do 
        {
            max=0;
            for(int i=0;i<m.size;i++)
            {
                s = 0;
                for (int j = 0; j < m.size; j++) 
                {
                    if (j != i) 
                    {
                        s += m.matrix[i][j] * x.vect[j];
                    }
                }
                s= (v.vect[i] - s)/m.matrix[i][i];
                if(max<Math.abs(x.vect[i]-s))
                {
                    max=Math.abs(x.vect[i]-s);
                }
                x.vect[i]=s;
            }
        }
        while(max>(float) 1e-10);
    }
    else
    {
        if(m.t.type==5)
        {
            for(int i=0;i<m.size;i++)
            {
                x.vect[i]=0;
            }

            do 
            {
                max=0;
                for(int i=0;i<m.size;i++)
                {
                    s = 0;
                    for (int j = Math.max(0,i-m.t.width); j < Math.min(m.size,i+m.t.width+1); j++)
                    {
                        if (j != i) 
                        {
                            s += m.matrix[i][j] * x.vect[j];
                        }
                    }
                    s= (v.vect[i] - s)/m.matrix[i][i];
                    if(max<Math.abs(x.vect[i]-s))
                    {
                        max=Math.abs(x.vect[i]-s);
                    }
                    x.vect[i]=s;
                }
            }
            while(max>(float) 1e-10);
        }
    }
    return x;
}



// GaussSeidel when there is max iteration
public static Vector GaussSeidel(Matrix m, Vector v, int MaxIterations)
{
    Vector x= new Vector(v.size);
    float s;
    if(m.t.type==0)
    {
        for(int i=0;i<m.size;i++)
        {
            x.vect[i]=0;
        }

       for(int k=0;k<MaxIterations;k++)
       {
            for(int i=0;i<m.size;i++)
            {
                s = 0;
                for (int j = 0; j < m.size; j++) 
                {
                    if (j != i) 
                    {
                        s += m.matrix[i][j] * x.vect[j];
                    }
                }
                s= (v.vect[i] - s)/m.matrix[i][i];
                x.vect[i]=s;
            }
        }
    }
    else
    {
        if(m.t.type==5)
        {
            for(int i=0;i<m.size;i++)
            {
                x.vect[i]=0;
            }

            for(int k=0;k<MaxIterations;k++)
            {
                for(int i=0;i<m.size;i++)
                {
                    s = 0;
                    for (int j = Math.max(0,i-m.t.width); j < Math.min(m.size,i+m.t.width+1); j++)
                    {
                        if (j != i) 
                        {
                            s += m.matrix[i][j] * x.vect[j];
                        }
                    }
                    s= (v.vect[i] - s)/m.matrix[i][i];
                    x.vect[i]=s;
                }
            }
        }
    }
    return x;
}



/////// LU
private static Vector resolution_inf(Matrix M, Vector b)
{
Vector y=new Vector(M.size);
int i,j;
for(i=0;i<M.size;i++)
{
    y.vect[i]=b.vect[i];
    for(j=0;j<i;j++)
    {
    y.vect[i]-=M.matrix[i][j]*y.vect[j];
    }
}


return y;
}

private static Vector resolution_inf_bande(Matrix M, Vector b)
{
Vector y=new Vector(M.size);
int i,j;
for(i=0;i<M.size;i++)
{
    y.vect[i]=b.vect[i];
    for(j=Math.max(0, i-M.t.width);j<i;j++)
    {
        y.vect[i]-=M.matrix[i][j]*y.vect[j];
    }
}

return y;
}

	public static Vector ResolutionLU(Matrix M, Vector b)
	{
		
	Vector y=new Vector(M.size);
	Vector x=new Vector(M.size);
	int i,j,k;
	 if(M.t.type==0)//Dense
	 {
	Type t1=new Type(2,0);
	Matrix L=new Matrix(M.size,t1);
	Type t2=new Type(1,0);
	Matrix U=new Matrix(M.size,t2);
	L.matrix=new float[M.size][M.size];
	U.matrix=new float[M.size][M.size];
	for(i=0;i<M.size;i++)
	{
	L.matrix[i][i]=1;
	for(j=0;j<=i-1;j++)
	{
	L.matrix[i][j]=M.matrix[i][j];
	for(k=0;k<=j-1;k++)
	{
	L.matrix[i][j]-=L.matrix[i][k]*U.matrix[k][j];
	
	}
	L.matrix[i][j]/=U.matrix[j][j];
	}
	for(j=i;j<M.size;j++)
	{
	U.matrix[i][j]=M.matrix[i][j];
	for(k=0;k<=i-1;k++)
	{
	U.matrix[i][j]-=L.matrix[i][k]*U.matrix[k][j];
	
	}
	}
	
	}

	  y=resolution_inf(L,b);
	  x=ResolutionLinear.resolution_sup(U,y);
    
	 }
	 if(M.t.type==5)//Bande
	 {
	Type t1=new Type(4,M.t.width);
	Matrix L=new Matrix(M.size,t1);
	Type t2=new Type(3,M.t.width);
	Matrix U=new Matrix(M.size,t2);
	L.matrix=new float[M.size][M.size];
	U.matrix=new float[M.size][M.size];
	for(i=0;i<M.size;i++)
	{
	L.matrix[i][i]=1;
	for(j=Math.max(0, i-M.t.width);j<=i-1;j++)
	{
	L.matrix[i][j]=M.matrix[i][j];
	for(k=0;k<=j-1;k++)
	{
	L.matrix[i][j]-=L.matrix[i][k]*U.matrix[k][j];
	
	}
	L.matrix[i][j]/=U.matrix[j][j];
	}
	for(j=i;j<Math.min(M.size,i+M.t.width+1);j++)
	{
	U.matrix[i][j]=M.matrix[i][j];
	for(k=0;k<=i-1;k++)
	{
	U.matrix[i][j]-=L.matrix[i][k]*U.matrix[k][j];

	}
	}
	
	}

	 y=resolution_inf_bande(L,b);
     x=ResolutionLinear.resolution_sup_bande(U,y);
    
	 } 
	return x;
}



private static float calculateError(Vector x, Vector y) {
    float maxError = 0;
    for (int i = 0; i < x.size; i++) {
        float error = Math.abs(y.vect[i] - x.vect[i]);
        if (error > maxError) {
            maxError = error;
        }
    }
    return maxError;
}






}


