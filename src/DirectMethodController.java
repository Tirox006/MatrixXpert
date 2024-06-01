import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DirectMethodController {
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

        public void switchToLUScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("LUScene.fxml"));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
   
       }

       public void switchToGaussEliminationScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("GaussEliminationScene.fxml"));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
   
       }

       public void switchToGaussEliminationWithPartielPivotingScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("GaussEliminationWithPartielPivotingScene.fxml"));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
   
       }

       public void switchToCholeskyScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("CholeskyScene.fxml"));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
   
       }

       public void switchToGaussJordanScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("GaussJordanScene.fxml"));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
   
       }
    
}
