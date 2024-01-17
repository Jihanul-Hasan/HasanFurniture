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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CustomerLocateStoreController implements Initializable {

    @FXML
    private ImageView mapview;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void loadUI(ActionEvent event, String str) throws IOException {
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
    void showstore(MouseEvent event) {

    }

    @FXML
    void logout(ActionEvent event) throws IOException {
        loadUI(event, "loginscene");
    }

    @FXML
    void back(ActionEvent event) throws IOException {
        loadUI(event, "cus1");
    }

}
