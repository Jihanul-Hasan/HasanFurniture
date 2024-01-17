package hasanfurniture;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

public class ITUpdateFurnitureController2 implements Initializable {

    @FXML
    private ImageView FurnitureImageFx;
    @FXML
    private TextField namefx;

    @FXML
    private TextField pricefx;

    @FXML
    private TextField colorfx;

    @FXML
    private DatePicker manufactureDate;
    @FXML
    private AnchorPane myAnchor;
    private BufferedImage bimage;
    private String imgPath;
    private Image imageInClass;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    //...........Choosing Image For Furniture Update..............//
    @FXML
    void addImagefx(ActionEvent event) throws IOException {

        FileChooser fc = new FileChooser();

        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image files", "*.jpg", "*.png"));
        File f = fc.showOpenDialog(null);
        if (f != null) {

            String s = f.toURI().toString();
            System.out.println("PATH: " + s);
            Image image = new Image(f.toURI().toString());
            imageInClass = image;
            //imgPath = f.toURI().toString();
            imgPath = s;
            System.out.println("Image path: " + imgPath);
            FurnitureImageFx.setImage(image);

            //BufferedImage img = ImageIO.read(new File(f.toURI().toString()));
            //bimage= img;
        }

    }

    @FXML
    void addToBinFileOnClick(ActionEvent event) {

        File f = null;
        Furniture furniture = new Furniture(imgPath, namefx.getText(), "", "", colorfx.getText(),
                parseInt(pricefx.getText()), manufactureDate.getValue());
        f = new File("Furnitures.bin");

        ObjectOutputStream oos = null;
        try {
            if (f.exists()) {
                oos = new AppendableObjectOutputStream(new FileOutputStream(f, true));
            } else {
                oos = new ObjectOutputStream(new FileOutputStream(f));
            }

            oos.writeObject(furniture);
            furniture.show();
            oos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        FurnitureImageFx.setImage(null);
        namefx.clear();
        pricefx.clear();;
        colorfx.clear();;

    }
}
