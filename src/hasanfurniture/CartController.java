package hasanfurniture;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CartController implements Initializable {

    @FXML
    private ImageView CartImageViewFx;
    @FXML
    private Label name;
    @FXML
    private Label price;
    @FXML
    private Label dtime;
    @FXML
    private Label amount;

    @FXML
    private Label TotalFx;
    int number = 1;
    double doublePrice = 0;
    String str;
    Furniture furniture;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dtime.setText("Not Confirmed yet!!");
        amount.setText("1");

    }

    public void set2(Furniture f) {

        name.setText(f.name);
        price.setText(Integer.toString(f.price));
        CartImageViewFx.setImage(new Image(f.ImageFilePath));
        TotalFx.setText(Integer.toString(f.price));
        furniture = f;

    }

    @FXML
    private void plus(ActionEvent event) {
        number++;
        int num = number * furniture.price;
        amount.setText(Integer.toString(number));
        TotalFx.setText(Integer.toString(num));
    }

    @FXML
    private void minus(ActionEvent event) {
        if (number > 1) {
            number--;
            int num = number * furniture.price;
            amount.setText(Integer.toString(number));
            TotalFx.setText(Integer.toString(num));
        }
    }
}
