import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class IterativeMethodController {
    private Stage stage;
    private Scene scene;
    private Parent root;

        public void switchToResolutionLinearSystemScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("ResolutionLinearSystemScene.fxml"));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
   
       }

       public void switchToJacobiScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("JacobiScene.fxml"));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
   
       }

              public void switchToGaussSeidelScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("GaussSeidelScene.fxml"));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
   
       }
    
}
