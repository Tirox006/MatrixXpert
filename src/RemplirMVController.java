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
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RemplirMVController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    @FXML
    private Button calmv;

    @FXML
    private TextField tfsize;

    @FXML
    private GridPane matrixPane1;

    @FXML
    private GridPane matrixPane2;

    @FXML
    private GridPane matrixPane3;

    @FXML
    private GridPane resultContainer;

    @FXML
    private Label resultLabel;

    public void generateMatrixVector() {
        calmv.setVisible(true);
        int size = getSizeFromTextField();

        matrixPane1.getChildren().clear();
        matrixPane2.getChildren().clear();

        // Logique pour générer la matrice 1
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                TextField tf1 = new TextField();
                tf1.setPrefHeight(50);
                tf1.setPrefWidth(50);
                tf1.setAlignment(Pos.CENTER);
                matrixPane1.add(tf1, x, y);
            }
        }

        // Logique pour générer la matrice 2 (vecteur)
        for (int z = 0; z < size; z++) {
            TextField tf2 = new TextField();
            tf2.setPrefHeight(50);
            tf2.setPrefWidth(50);
            tf2.setAlignment(Pos.CENTER);
            matrixPane2.add(tf2, 0, z);
        }
    }

    public void calculateResult() {
    
        Type t=Matrix.VerifyType(getMatrixFromPane(matrixPane1));
        Matrix m = new Matrix(getMatrixFromPane(matrixPane1),t);
        Vector v= new Vector(getVectorFromPane(matrixPane2));
        resultContainer.getChildren().clear();
        Vector result = Multiply.multiply(m,v);

       

        
        // Display the result vector in TextFields within the resultContainer GridPane
        for (int i = 0; i < result.size; i++) {
            TextField resultTextField = new TextField(Float.toString(result.vect[i]));
            resultTextField.setPrefHeight(50);
            resultTextField.setPrefWidth(50);
            resultTextField.setEditable(false); // Make the TextField read-only
            GridPane.setConstraints(resultTextField, 0, i); // Set the constraints for GridPane
            resultContainer.getChildren().add(resultTextField); // Use add method from Pane
        }
    }

    public void switchToStartScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("StartScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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


    private float[][] getMatrixFromPane(GridPane matrixPane) {
        int size = getSizeFromTextField();
        float[][] matrix = new float[size][size];
    
        for (Node node : matrixPane.getChildren()) {
            if (node instanceof TextField) {
                TextField textField = (TextField) node;
                String text = textField.getText().trim(); // Trim to remove leading/trailing spaces
    
                // Retrieve row and column indices
                Integer rowIndex = GridPane.getRowIndex(node);
                Integer colIndex = GridPane.getColumnIndex(node);
    
                // Check for null values
                if (rowIndex != null && colIndex != null) {
                    if (!text.isEmpty()) {
                        matrix[rowIndex][colIndex] = Float.parseFloat(text);
                    } else {
                        // If the text is empty, set the corresponding matrix value to 0
                        matrix[rowIndex][colIndex] = 0.0f;
                    }
                }
            }
        }
    
        return matrix;
    }
    

    private float[] getVectorFromPane(GridPane vectorPane) {
        int size = getSizeFromTextField();
        float[] vector = new float[size];

        for (Node node : vectorPane.getChildren()) {
            if (node instanceof TextField) {
                TextField textField = (TextField) node;
                String text = textField.getText().trim(); // Trim to remove leading/trailing spaces

                if (!text.isEmpty()) {
                    vector[GridPane.getRowIndex(node)] = Float.parseFloat(text);
                } else {
                    // If the text is empty, set the corresponding vector value to 0
                    vector[GridPane.getRowIndex(node)] = 0.0f;
                }
            }
        }

        return vector;
    }

}
