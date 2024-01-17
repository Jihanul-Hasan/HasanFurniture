package hasanfurniture;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class CustomerScene1Controller extends Application implements Initializable {

    @FXML
    private GridPane grid;
    @FXML
    private MenuBar menubar;
    private final int tileSize = 5;
    private static final double ELEMENT_SIZE = 100;
    private static final double GAP = ELEMENT_SIZE / 10;
    @FXML
    private TilePane tilepane;
    @FXML
    private ImageView pbc1;
    @FXML
    private ListView<?> list;
  
    @FXML
    private ImageView avatar;
    private Label random;
    private Button button;
    int j = 1;
    int col = 0;
    int row = 0;
     String string;
    protected Image image; 
    protected Customer customer;
    ObservableList<Furniture> allFurnitures = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        avatar.setImage(image);
        ObjectInputStream ois = null;

        try {
            Furniture furniture;
            ois = new ObjectInputStream(new FileInputStream("Furnitures.bin"));
            while (true) {
                furniture = (Furniture) ois.readObject();
                allFurnitures.add(furniture);
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

        grid.getChildren().clear();
        grid.setMinWidth(1000);

        for (Furniture fur : allFurnitures) {

            try {

                grid.add(loadUIwithdatapass2(fur,"furniture"), col++, row);

            } catch (IOException ex) {
                Logger.getLogger(CustomerScene1Controller.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (col == 3) {
                col = 0;
                row++;
            }

        }

    }

   
    private Parent loadUIwithdatapass2(Furniture f,String ui) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/allFxml/" + ui + ".fxml"));
        Parent p = loader.load();

        FurnitureViewController fv = loader.<FurnitureViewController>getController();
        fv.setInFurnitureViewController(f);

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
            Logger.getLogger(CustomerScene1Controller.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void fabric(ActionEvent event) {
    }

    @FXML
    private void leather(ActionEvent event) {
    }

    @FXML
    private void wooden(ActionEvent event) {
    }

    @FXML
    void showroom(ActionEvent event) throws IOException {
        gotoscene(event, "locateStore");

    }

    @FXML
    void showcart(ActionEvent event) throws IOException {
        gotoscene(event, "cartview");
    }

    @FXML
    void socialmedia(ActionEvent event) throws IOException {

        gotoscene(event, "web");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {

        gotoscene(event, "loginscene");
    }

}
