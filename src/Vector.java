//import java.util.Scanner;

public class Vector {
   //attribut
    public float [] vect ;
    public int size;
    //Scanner myInput=new Scanner(System.in);
   //constructeur1
    public Vector(int size)
    {
        this.size=size;
       this.vect=new float[size];
    }
  //constructeur2
    public Vector(float[] vect)
    {
        this.size=vect.length;
       this.vect=vect;
    }
    
    /* 
    public void initialize(){
        for (int i = 0; i < size; i++) {
            vect[i]=myInput.nextFloat();
        }
    }
    */
    public void afficher(){
        for (int i = 0; i < size; i++) {
          System.out.print(vect[i]+"\t"); 
        }
    }
    
}

