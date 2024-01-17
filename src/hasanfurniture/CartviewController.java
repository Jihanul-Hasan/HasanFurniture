package hasanfurniture;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CartviewController implements Initializable {

    private ListView<Furniture> list;
    @FXML
    private GridPane grid;
    @FXML
    private MenuBar menubar;
    @FXML
    private ImageView pbc1;

    @FXML
    private Label CartIsEmpty;
    private Parent p;
    Furniture furnitureInTheClass;
    ObservableList<Furniture> allFurnituresInCart = FXCollections.observableArrayList();
    int col = 0, row = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList<Customer> customers = getCustomers();
        Customer cus = customers.get(0);
        ObjectInputStream ois = null;

        //************READING FROM SPECIFIC BIN FILE FOR SPECIFIC Customer*******//
        try {
            Furniture furniture;
            ois = new ObjectInputStream(new FileInputStream(cus.Email + "Cart.bin"));
            while (true) {
                furniture = (Furniture) ois.readObject();
                allFurnituresInCart.add(furniture);
            }

        } catch (IOException | ClassNotFoundException ex) {
            try {

                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            ex.printStackTrace();
        }

        if (allFurnituresInCart == null) {

            CartIsEmpty.setText("Cart is Empty");
        } else {
            grid.getChildren().clear();
            //grid.getChildren().clear();
            grid.setMinWidth(800);
            grid.setMinHeight(1050);

            allFurnituresInCart.forEach((furniture) -> {
                try {
                    grid.add(loadUIwithdatapass(furniture, "cart"), col++, row);
                    if (col == 2) {
                        col = 0;
                        row++;
                    }

                } catch (IOException ex) {
                    Logger.getLogger(CartviewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }
    }

    //***************Reading CartCustomer to know whos cart is this******************//
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
    void back(ActionEvent event) throws IOException {
        gotoscene(event, "cus1");

    }

    @FXML
    void logout(ActionEvent event) throws IOException {

        gotoscene(event, "loginscene");
    }

    @FXML
    void showroom(ActionEvent event) throws IOException {
        gotoscene(event, "locateStore");

    }

    @FXML
    void PaymentSceneOnClick(ActionEvent event) throws IOException {
        gotoStage(event, "CustomerPaymentScene");
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
            Logger.getLogger(CustomerScene1Controller.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Parent loadUIwithdatapass(Furniture furniture, String ui) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/allFxml/" + ui + ".fxml"));
        Parent p = loader.load();
        CartController cc = loader.getController();
        cc.set2(furniture);
        return p;
    }

    private void gotoStage(ActionEvent event, String ui) throws IOException {
        Parent fileChooserViewParent = FXMLLoader.load(getClass().getResource("/allFxml/" + ui + ".fxml"));
        Scene fileChooserViewScene = new Scene(fileChooserViewParent);
        //Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Stage newWindow = new Stage();
        newWindow.setTitle("Payment Process");
        newWindow.setScene(fileChooserViewScene);
        newWindow.show();
    }

}
