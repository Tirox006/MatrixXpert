import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class RemplirMMcontroler {
    private Stage stage;
    private Scene scene;
    private Parent root; 

    @FXML
    private Button inverseButton;
    
    @FXML
    private Label rq;
    
    @FXML
    private GridPane matrixPane2;

    @FXML
    private GridPane resultMatrixPane;
    

        public void setScene(Scene scene) {
        this.scene = scene;
    }
   // Declare matrix1 and matrix2 as instance variables
    private float[][] matrix1;
    private float[][] matrix2;

    @FXML
    public void generateMatrix() {

        // Assuming the matrixPane1 and matrixPane2 GridPanes are defined in the FXML file
        GridPane matrixPane1 = (GridPane) scene.lookup("#matrixPane1");
        GridPane matrixPane2 = (GridPane) scene.lookup("#matrixPane2");

        if (matrixPane1 != null && matrixPane2 != null) {
            matrixPane1.getChildren().clear();
            matrixPane2.getChildren().clear();

            // Use the logic from the App class to generate the matrices
            int size = getSizeFromTextField(); // Implement a method to get the size from the TextField
            int length = size;
            int width = size;

            // Create a data structure to store the user inputs for each matrix
            matrix1 = new float[length][width];
            matrix2 = new float[length][width];

            for (int y = 0; y < length; y++) {
                for (int x = 0; x < width; x++) {
                    TextField tf1 = new TextField();
                    tf1.setPrefHeight(50);
                    tf1.setPrefWidth(50);
                    tf1.setAlignment(Pos.CENTER);
                    GridPane.setRowIndex(tf1, y);
                    GridPane.setColumnIndex(tf1, x);
                    matrixPane1.getChildren().add(tf1);

                    TextField tf2 = new TextField();
                    tf2.setPrefHeight(50);
                    tf2.setPrefWidth(50);
                    tf2.setAlignment(Pos.CENTER);
                    GridPane.setRowIndex(tf2, y);
                    GridPane.setColumnIndex(tf2, x);
                    matrixPane2.getChildren().add(tf2);

                    // Add a listener to each TextField to capture user inputs
                    tf1.textProperty().addListener((observable, oldValue, newValue) ->
                        matrix1[GridPane.getRowIndex(tf1)][GridPane.getColumnIndex(tf1)] = Float.parseFloat(newValue));

                    tf2.textProperty().addListener((observable, oldValue, newValue) ->
                        matrix2[GridPane.getRowIndex(tf2)][GridPane.getColumnIndex(tf2)] = Float.parseFloat(newValue));
                }
            }

            inverseButton.setVisible(true);
           
            // You can now access the matrix1 and matrix2 arrays to retrieve the user inputs
        }

    } 

    // Access the matrices from other parts of your code
    public float[][] getMatrix1() {
        return matrix1;
    }

    public float[][] getMatrix2() {
        return matrix2;
    }

    public int getSizeFromTextField() {
        TextField tfsize = (TextField) scene.lookup("#tfsize");
        if (tfsize != null && !tfsize.getText().isEmpty()) {
            try {
                return Integer.parseInt(tfsize.getText());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    private float[][] multiplyMatrices(float[][] matrix1, float[][] matrix2) {
        int rows1 = matrix1.length;
        int columns1 = matrix1[0].length;
        int rows2 = matrix2.length;
        int columns2 = matrix2[0].length;
    
        // Check if matrices are compatible for multiplication
        if (columns1 != rows2) {
            System.out.println("Matrices cannot be multiplied. Incompatible dimensions.");
            return null;
        }
    
        float[][] resultMatrix = new float[rows1][columns2];
    
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < columns2; j++) {
                for (int k = 0; k < rows2; k++) {
                    resultMatrix[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
    
        return resultMatrix;
    }
    



    public void calculateAndDisplayResult() {
        // Assuming matrixPane1 and matrixPane2 are defined similarly as in generateMatrix method
        GridPane matrixPane1 = (GridPane) scene.lookup("#matrixPane1");
        GridPane matrixPane2 = (GridPane) scene.lookup("#matrixPane2");


        if (matrixPane1 != null && matrixPane2 != null) {
            float[][] resultMatrix = multiplyMatrices(matrix1, matrix2);//*************** */

            // Clear existing content in resultMatrixPane
            resultMatrixPane.getChildren().clear();

            // Display the result matrix in the resultMatrixPane using TextAreas
            int rows = resultMatrix.length;
            int columns = resultMatrix[0].length;

            for (int y = 0; y < rows; y++) {
                for (int x = 0; x < columns; x++) {
                    TextArea resultTextArea = new TextArea(String.valueOf(resultMatrix[y][x]));
                    resultTextArea.setEditable(false);
                    resultTextArea.setPrefHeight(10);
                    resultTextArea.setPrefWidth(10);
                    resultMatrixPane.add(resultTextArea, x, y);
                }
            }
        }
    }

    
        public void switchToStartScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("StartScene.fxml"));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
   
       }

       Matrix inverseMatrix;
       public void calculateInverse() {
        Type t=Matrix.VerifyType(matrix1);
        double[][] matrixf;
        matrixf=Inverse.floatToDouble(matrix1);
        Matrix m = new Matrix(matrixf,t);

        // Calculez l'inverse de la première matrice
        if(Determinant.calculateDeterminant(m)!=0){
            inverseMatrix = Inverse.calculateInverse(m);
            fillMatrixPane(matrixPane2, inverseMatrix);
            rq.setTextFill(Color.GREEN);
            rq.setText("Invertible Matrix");
            
            Matrix resultInv =Multiply.multiplyInv(m,inverseMatrix);
            int rows = resultInv.size;
            int columns = resultInv.size;

            for (int y = 0; y < rows; y++) {
                for (int x = 0; x < columns; x++) {
                    TextArea resultTextArea = new TextArea(String.valueOf(resultInv.matrix1[y][x]));
                    resultTextArea.setEditable(false);
                    resultTextArea.setPrefHeight(10);
                    resultTextArea.setPrefWidth(10);
                    resultMatrixPane.add(resultTextArea, x, y);
                } 
            }
 
        }else{
            rq.setTextFill(Color.RED);
            rq.setText("Non-Nnvertible Matrix");  
        }
        
        

        
    }



    private void fillMatrixPane(GridPane matrixPane, Matrix matrix) {
        // Assurez-vous que la matricePane n'est pas nulle
        if (matrixPane != null) {
            matrixPane.getChildren().clear(); // Effacez les anciens éléments
    
            int rows = matrix.size;
            int columns = matrix.matrix1[0].length;
    
            for (int y = 0; y < rows; y++) {
                for (int x = 0; x < columns; x++) {
                    double value = (Math.round(matrix.matrix1[y][x]*1000.0)/1000.0);
                     
                    // Créez un TextArea pour afficher la valeur de la matrice
                    TextArea valueTextArea = new TextArea(String.valueOf(value));
                    valueTextArea.setEditable(false);
                    valueTextArea.setPrefHeight(50); // Ajustez la hauteur selon vos besoins
                    valueTextArea.setPrefWidth(50);  // Ajustez la largeur selon vos besoins
    
                    // Ajoutez le TextArea à la matricePane
                    GridPane.setRowIndex(valueTextArea, y);
                    GridPane.setColumnIndex(valueTextArea, x);
                    matrixPane.getChildren().add(valueTextArea);
                }
            }
        }
    }
    

}
