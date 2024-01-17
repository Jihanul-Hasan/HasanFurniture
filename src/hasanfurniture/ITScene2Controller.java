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
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ITScene2Controller implements Initializable {

    @FXML
    private BorderPane itscene;
    @FXML
    private Button showallusers;

    
    //*****This Scene Deals With All User Related Things*******//
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    private void loadUI(String ui) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/allFxml/" + ui + ".fxml"));
            itscene.setCenter(root);
        } catch (IOException ex) {
            Logger.getLogger(ITScene2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void gotoscene(ActionEvent event, String str) throws IOException {
        Parent scene2Parent = FXMLLoader.load(getClass().getResource("/allFxml/" + str + ".fxml"));
        Scene scene2 = new Scene(scene2Parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();
        window.setMaximized(true);

    }

    @FXML
    void adduser(ActionEvent event) {
        loadUI("IT3AddUser");
    }

    @FXML
    void back(ActionEvent event) throws IOException {
        gotoscene(event, "IT1");
    }

    @FXML
    private void showallusersonclick(ActionEvent event) throws IOException {

        gotoscene(event, "IT4");
    }

}
