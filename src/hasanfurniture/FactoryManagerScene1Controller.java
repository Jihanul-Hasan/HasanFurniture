
package hasanfurniture;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class FactoryManagerScene1Controller implements Initializable {

    @FXML
    private MediaView mediaview;
    private Media media;
    private File file;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        file = new File("1.mp4");
        media = new Media(file.toURI().toString());
        MediaPlayer mp = new MediaPlayer(media);
        mediaview.setMediaPlayer(mp);

        mp.seek(Duration.ZERO);
        
       
      mp.play();
      mp.setStopTime(Duration.seconds(27));
      mp.setCycleCount(10);
     
    }

    @FXML
    void customerreviews(ActionEvent event) {

    }

    @FXML
    void emailandmsg(ActionEvent event) {

    }

    @FXML
    void meetings(ActionEvent event) {

    }

    @FXML
    void reports(ActionEvent event) {

    }

    @FXML
    void workallocate(ActionEvent event) {

    }
}
