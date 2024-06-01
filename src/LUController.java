import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LUController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private Label rq;
    @FXML
    private TextField sizeTextField;

    @FXML
    private GridPane matrixGridPane;

    @FXML
    private GridPane resultGridPane;

    @FXML
    private GridPane vectorGridPane;

    @FXML
    private Button createMatrixButton;

    @FXML
    private Label resultVectorLabel;

    @FXML
    private Label MatrixLabel;
    
    @FXML
    private Label ResultLabel;
    

    private float[][] matrix;
    private float[] vector;

    private boolean matrixCreated = false;

    @FXML
    
    private void handleValidateButton() {
        int size = Integer.parseInt(sizeTextField.getText());

        matrixGridPane.getChildren().clear();
        vectorGridPane.getChildren().clear();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                TextField textField = new TextField();
                matrixGridPane.add(textField, j, i);
                GridPane.setFillWidth(textField, true);
                GridPane.setFillHeight(textField, true);
            }

            TextField vectorTextField = new TextField();
            vectorGridPane.add(vectorTextField, size, i);
            GridPane.setFillWidth(vectorTextField, true);
            GridPane.setFillHeight(vectorTextField, true);
        }

        matrix = new float[size][size];
        vector = new float[size];

        matrixCreated = true;

        // Clear the resultGridPane when creating a new matrix
        resultGridPane.getChildren().clear();
        resultVectorLabel.setVisible(false); // Hide the result vector label

        // Display matrix label
        MatrixLabel.setVisible(true);
        // Display the result label
        ResultLabel.setVisible(true);
    }

    @FXML
    private void handleRetrieveAndCalculateButton() {
        if (!matrixCreated) {
            // If the matrix has not been created, do nothing
            return;
        }

        // Retrieve the values of the matrix
        for (int i = 0; i < matrixGridPane.getRowCount(); i++) {
            for (int j = 0; j < matrixGridPane.getColumnCount(); j++) {
                TextField textField = (TextField) matrixGridPane.getChildren().get(i * matrixGridPane.getColumnCount() + j);
                matrix[i][j] = (float) (textField.getText().isEmpty() ? 0.0 : Double.parseDouble(textField.getText()));
            }
        }

        // Retrieve the values of the vector
        for (int i = 0; i < vectorGridPane.getRowCount(); i++) {
            TextField vectorTextField = (TextField) vectorGridPane.getChildren().get(i);
            vector[i] = (float) (vectorTextField.getText().isEmpty() ? 0.0 : Double.parseDouble(vectorTextField.getText()));
        }

        Type t=Matrix.VerifyType(matrix);
        Matrix m = new Matrix(matrix,t);
        Vector v= new Vector(vector);


        if(Matrix.VerifyPositive(m) && Matrix.VerifySymetric(m) && (m.t.type==0 || m.t.type==5)){

               Vector productVector = Resolution.ResolutionLU(m,v);
               rq.setTextFill(Color.GREEN);
               rq.setText("Valid Matrix");
        // Display the result vector label
        resultVectorLabel.setVisible(true);
        // Display the result in the resultGridPane
        displayVectorInGridPane(productVector, resultGridPane);
        }else{

            rq.setTextFill(Color.RED);
            rq.setText("Invalid Matrix");
            
        }
    }



    private void displayVectorInGridPane(Vector vector, GridPane gridPane) {
        gridPane.getChildren().clear();

        for (int i = 0; i < vector.size; i++) {
            TextField textField = new TextField(Double.toString(vector.vect[i]));
            gridPane.add(textField, 0, i);
            GridPane.setFillWidth(textField, true);
            GridPane.setFillHeight(textField, true);
        }
    }


            public void switchToDirectMethodScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("DirectMethodScene.fxml"));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
   
       }
}
