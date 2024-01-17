package hasanfurniture;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class FurnitureViewController implements Initializable {

    @FXML
    private ImageView imgV;
    @FXML
    private Label name;
    @FXML
    private Label price;

    Furniture fur;
    Customer customer;
    String string;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void set(Image img, String n, String p) {

        imgV.setImage(img);
        name.setText(n);
        price.setText(p);
    }

    public void setInFurnitureViewController(Furniture f) {

        // imgV.setImage(SwingFXUtils.toFXImage(f.getFurimg(), null));
        imgV.setImage(new Image(f.ImageFilePath));
        name.setText(f.getName());
        price.setText(Integer.toString(f.price));
        fur = f;

    }

 
    private void loadUIwithDataPass(Furniture f, Customer customer, MouseEvent event, String ui) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/allFxml/" + ui + ".fxml"));
        Parent p = loader.load();
        DetailedFurnitureController dfc = loader.getController();
        dfc.setInDetailedFurnitureController(f);
        Scene scene = new Scene(p);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    void viewproduct(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        try {
            loadUIwithDataPass(fur, customer, event, "detailedfurniture");
        } catch (IOException ex) {
            Logger.getLogger(FurnitureViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
