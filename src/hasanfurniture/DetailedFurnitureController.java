package hasanfurniture;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DetailedFurnitureController implements Initializable {

    @FXML
    private ImageView img;
    @FXML
    private Label name;
    @FXML
    private Label price;
    @FXML
    private MenuBar menubar;
    @FXML
    private ImageView pbc1;
    @FXML
    private Label color;
    @FXML
    private VBox vBoxConTainingComents;
    @FXML
    private Slider Slider;
    @FXML
    private Label dateofmanufacture;
    @FXML
    private Button addToCartFx;
    @FXML
    private TextField comment;
    private Furniture furnitureInTheClass;
    String string;

    protected ArrayList<String> allComments = new ArrayList<>();
    protected Customer customer = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    void setInDetailedFurnitureController(Furniture f) {

        furnitureInTheClass = f;
        allComments = f.getCommentsByUsers();

        //allComments = f.getCommentsByUsers();
        //img.setImage(SwingFXUtils.toFXImage(f.getFurimg(), null));
        img.setImage(new Image(f.ImageFilePath));
        name.setText(f.name);
        price.setText(Integer.toString(f.price));
        color.setText(f.color);
        if (f.color.equals("Red")) {
            color.setStyle("-fx-background-color: #E84C2A;");
        }
        if (f.color.equals("Brown")) {
            color.setStyle("-fx-background-color: #7B4C2E;");
        }
        if (f.color.equals("Yello")) {

            color.setStyle("-fx-background-color: #F1D312;");
        }
        if (f.color.equals("Black")) {
            color.setStyle("-fx-background-color: #181C1F;");
        }
        if (f.color.equals("Red")) {

        }
        dateofmanufacture.setText(f.manufactureDate.toString());

    }

    void set(Image image, String n, String p) {

        img.setImage(image);
        name.setText(n);
        price.setText(p);

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

    public ObservableList<Customer> getCustomers() {
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        ObjectInputStream ois = null;
        try {
            Customer c;
            ois = new ObjectInputStream(new FileInputStream("CartCustomer.bin"));
            while (true) {
                c = (Customer) ois.readObject();
                customers.add(c);

            }
        } catch (IOException ex) {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {

            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DetailedFurnitureController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customers;
    }

    @FXML
    private void addtocart(ActionEvent event) {

        ObservableList<Customer> customers = getCustomers();
        File f = null;
        for (Customer cus : customers) {
            System.out.println("Customer for cart: ");
            cus.show();;
            f = new File(cus.getEmail() + "Cart.bin");
        }

        ObjectOutputStream oos = null;
        try {
            if (f.exists()) {
                oos = new AppendableObjectOutputStream(new FileOutputStream(f, true));
            } else {
                oos = new ObjectOutputStream(new FileOutputStream(f));
            }

            oos.writeObject(furnitureInTheClass);

            oos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        addToCartFx.setText("Added To Cart");
        addToCartFx.setStyle("-fx-background-color:#8BF075;");

    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        loadUI(event, "cus1");

    }

    @FXML
    private void commentWriting(ActionEvent event) {

        Label commentlabel = new Label();
        Label commentlabel2 = new Label();
        commentlabel.setStyle("-fx-background-color: #A3C9DF;\n"
                + "-fx-background-radius: 50");
        commentlabel.setText(comment.getText());
        commentlabel.setPadding(new Insets(0, 15, 0, 15));
        vBoxConTainingComents.getChildren().add(commentlabel);
        vBoxConTainingComents.getChildren().add(commentlabel2);
        comment.clear();

    }

    @FXML
    private void showroom(ActionEvent event) {
    }

    @FXML
    private void logout(ActionEvent event) {
    }

}
