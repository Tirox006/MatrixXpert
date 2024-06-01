import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ResolutionLinearSystemController {
    private Stage stage;
    private Scene scene;
    private Parent root;

        public void switchToStartScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("StartScene.fxml"));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
   
       }

        public void switchToDirectMethodScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("DirectMethodScene.fxml"));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
   
       }


        public void switchToIterativeMethodScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("IterativeMethodScene.fxml"));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
   
       }
    
}
