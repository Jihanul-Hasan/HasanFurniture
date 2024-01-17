
package hasanfurniture;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ITScene1Controller implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    void User(ActionEvent event) throws IOException {

        gotoscene(event, "IT2");

    }

    @FXML
    void updateFurniture(ActionEvent event) throws IOException {

        gotoscene(event, "UpdateFurnitureByIT");

    }

    private void gotoscene(ActionEvent event, String str) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/allFxml/" + str + ".fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
            window.setMaximized(true);
        } catch (ClassCastException ex) {
            Logger.getLogger(BranchManagerScene1Controller.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void logout(ActionEvent event) throws IOException {
        gotoscene(event, "loginscene");
    }
      
    @FXML
    void GoToMsgSceneOnClick(ActionEvent event) throws IOException {

        gotoscene(event, "Message");
        
    }
    
}
