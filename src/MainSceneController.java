import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class MainSceneController {
    
    private Stage stage;
    private Scene scene;
    private Parent root; 


   @FXML
    void switchToRemplirMMScene(ActionEvent event) throws IOException {   
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RemplirMMScene.fxml"));
        root = loader.load();
        RemplirMMcontroler controller = loader.getController();
        Scene newScene = new Scene(root);
        controller.setScene(newScene);
        controller.generateMatrix();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(newScene);
        stage.show();
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }


      @FXML
    void switchToRemplirMVScene(ActionEvent event) throws IOException {   
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RemplirMVScene.fxml"));
        root = loader.load();
        RemplirMVController controller = loader.getController();
        Scene newScene = new Scene(root);
        controller.setScene(newScene);
        controller.generateMatrixVector();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(newScene);
        stage.show();
    }


    




           @FXML
     public void switchToTriangularLinearSystemResolution(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("ResolutionLinearTriangularSystemScene.fxml"));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
   
       }


        @FXML
        public void switchTonewResolutionLinearSystem(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("ResolutionLinearSystemScene.fxml"));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
   
       }







   


    }