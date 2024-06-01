public class ResolutionLinear 
{
    public static Vector resolution_inf(Matrix M, Vector b)
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
        y.vect[i]/=M.matrix[i][i];
	}
	y.afficher();
	
	return y;
	}
	
	public static Vector resolution_sup(Matrix M, Vector b)
	{
	Vector x=new Vector(M.size);
	int i,j;
	for(i=M.size-1;i>=0;i--)
	{
	x.vect[i]=b.vect[i];
	for(j=i+1;j<M.size;j++)
	{
	x.vect[i]-=M.matrix[i][j]*x.vect[j];
	}
	x.vect[i]/=M.matrix[i][i];
	}
	return x;
	}
	
	public static Vector resolution_inf_bande(Matrix M, Vector b)
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
        y.vect[i]/=M.matrix[i][i];
	}
	y.afficher();
	return y;
	}
	
	public static Vector resolution_sup_bande(Matrix M, Vector b)
	{
	Vector x=new Vector(M.size);
	int i,j;
	for(i=M.size-1;i>=0;i--)
	{
		x.vect[i]=b.vect[i];
		for(j=i+1;j<Math.min(i+M.t.width+1,M.size);j++)
		{
			x.vect[i]-=M.matrix[i][j]*x.vect[j];
		}
	x.vect[i]/=M.matrix[i][i];
	}
	return x;
	}
	
}