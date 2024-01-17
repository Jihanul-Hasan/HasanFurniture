package hasanfurniture;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class SupplierScene1Controller implements Initializable {

    @FXML
    private Circle avatar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    private void loadUI(ActionEvent event, String str) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/allFxml/" + str + ".fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        window.setMaximized(true);

    }

    @FXML
    private void emailandmsg(ActionEvent event) {
    }

    @FXML
    private void workallocate(ActionEvent event) {
    }

    @FXML
    private void meetings(ActionEvent event) {
    }

    @FXML
    private void customerreviews(ActionEvent event) {
    }

    @FXML
    private void reports(ActionEvent event) {
    }

    @FXML
    void logOutOnClick(ActionEvent event) throws IOException {
        loadUI(event, "loginscene");
    }
}
