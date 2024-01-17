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
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ITUpdateFurnitureController implements Initializable {

    @FXML
    private BorderPane borderpane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    private Parent loadUI2(String ui) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/allFxml/" + ui + ".fxml"));
        Parent p = loader.load();
        return p;
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
            Logger.getLogger(CustomerScene1Controller.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void showFurnitureAddWindow(ActionEvent event) throws IOException {

        borderpane.setCenter(loadUI2("UpdateFurnitureByIT2"));;
    }

    @FXML
    void BackOnClick(ActionEvent event) throws IOException {

        gotoscene(event, "IT2");
    }
}
