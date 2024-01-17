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
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class WebController implements Initializable {

    @FXML
    private WebView webview;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        webview.getEngine().load("https://www.youtube.com/watch?v=MQeD8xPMkcg");

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

        } catch (ClassCastException ex) {
            Logger.getLogger(WebController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void backToScene1(ActionEvent event) throws IOException {
        gotoscene(event, "cus1");
    }
}
