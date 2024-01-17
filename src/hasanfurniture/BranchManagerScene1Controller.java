package hasanfurniture;

import java.io.IOException;
import java.net.URL;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class BranchManagerScene1Controller implements Initializable {

    @FXML
    private ImageView bmhomeimg1;

    @FXML
    private ImageView bmhomeimg2;
    private int count;
    @FXML
    private BorderPane borderpane;
    @FXML
    private Circle avatar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Slideshow();
    }

//-------------------SLIDESHOW----------------------//
    public void Slideshow() {
        ArrayList<Image> images = new ArrayList<>();
        ArrayList<Image> images2 = new ArrayList<>();
        images.add(new Image("/Photos/BM0.jpg"));
        images.add(new Image("/Photos/BM2.jpg"));
        images2.add(new Image("/Photos/BOS/bed.jpg"));
        images2.add(new Image("/Photos/BOS/office.png"));
        FadeTransition fade = new FadeTransition(Duration.millis(2500), bmhomeimg1);
        FadeTransition fade2 = new FadeTransition(Duration.millis(2500), bmhomeimg2);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.setCycleCount(1000);
        fade.setAutoReverse(true);
        fade.play();
        fade2.setFromValue(0);
        fade2.setToValue(1);
        fade2.setCycleCount(1000);
        fade2.setAutoReverse(true);
        fade2.play();
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            bmhomeimg1.setImage(images.get(count));
            bmhomeimg2.setImage(images2.get(count));
            count++;
            if (count == 2) {
                count = 0;
            }
            fade.setFromValue(1);
            fade.setToValue(0);
            fade2.setFromValue(1);
            fade2.setToValue(0);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
//-------------------SLIDESHOW----------------------//

    private void loadUI(String ui) throws IOException {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/allFxml/" + ui + ".fxml"));
            borderpane.setCenter(root);
        } catch (IOException ex) {
            Logger.getLogger(BranchManagerScene1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        } catch (ClassCastException ex) {
            Logger.getLogger(BranchManagerScene1Controller.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void customerreviews(ActionEvent event) {

    }

    @FXML
    void emailandmsg(ActionEvent event) throws IOException {
        gotoscene(event, "Message");
    }

    @FXML
    void ShowInbox(ActionEvent event) {
        

    }

    @FXML
    void meetings(ActionEvent event) throws IOException {

    }

    @FXML
    void reports(ActionEvent event) throws IOException {

        borderpane.setCenter(loadUI2("bm2report"));

    }

    @FXML
    void workallocate(ActionEvent event) throws IOException {

    }

    @FXML
    void logout(ActionEvent event) throws IOException {

        gotoscene(event, "loginscene");

    }

}
