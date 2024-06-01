public class ResolutionTriangulair {
        public static Vector resolutionTriangulaire(Matrix M,Vector b){
            Vector x=new Vector(M.size);//Vecteur resultat
            int i,j;//les compteur quon va utilisier dans les boucles 
            switch (M.t.type){
                case 2://resoltion du matrice triang inf 
                for(i=0;i<M.size;i++){
                    x.vect[i]=b.vect[i];
                    for(j=0;j<i;j++){
                     x.vect[i]-=M.matrix[i][j]*x.vect[j]; }
                x.vect[i]/=M.matrix[i][i];}
                break;
                case 1://resoltion du matrice triang supp 
                for(i=M.size-1;i>=0;i--){
                    x.vect[i]=b.vect[i];
                    for(j=i+1;j<M.size;j++){
                        x.vect[i]-=M.matrix[i][j]*x.vect[j];}
                x.vect[i]/=M.matrix[i][i];}
                break;
                case 3://resolution du matrice bande sup
                for(i=M.size-1;i>=0;i--){
                    x.vect[i]=b.vect[i];
                    for(j=i+1;j<Math.min(i+M.t.width+1,M.size);j++){
                        x.vect[i]-=M.matrix[i][j]*x.vect[j];}
                x.vect[i]/=M.matrix[i][i];}
                break;
                case 4://resolution du matrice bande inf
                for(i=0;i<M.size;i++){
                    x.vect[i]=b.vect[i];
                    for(j=Math.max(0, i-M.t.width);j<i;j++){
                        x.vect[i]-=M.matrix[i][j]*x.vect[j];}
                x.vect[i]/=M.matrix[i][i];}
                break;
        
            }
            return x;
        }
    
    }
    

