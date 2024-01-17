package hasanfurniture;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import static javafx.fxml.FXMLLoader.load;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class MessageController implements Initializable {

    @FXML
    private Circle avatar;
    @FXML
    private BorderPane borderpane;
    @FXML
    private VBox allBranchManager;

    @FXML
    private VBox allFactoryManagers;

    @FXML
    private VBox ITDEpartment;

    @FXML
    private VBox allSuppliers;

    ObservableList<BranchManager> allbm = FXCollections.observableArrayList();

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        allbm = getAllBranchManagers();
        for (BranchManager bm : allbm) {

            Label label = new Label();
            Label label2 = new Label();
            Button button = new Button();

            allBranchManager.getChildren().add(label2);

            label.setText("        " + bm.Email + "      ");
            label.setStyle("-fx-background-color: #FFFFFF;\n"
                    + "    -fx-background-radius: 50;");

            button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try {
                        borderpane.setCenter(loadUI("MessageWriteUI"));
                        addMessageUserBinFile(bm);
                    } catch (IOException ex) {
                        Logger.getLogger(MessageController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
            );
            allBranchManager.getChildren().add(button);
        }

    }

    void addMessageUserBinFile(User User) {
        File f = null;
        f = new File("MessageUser.bin");

        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(f));
            oos.writeObject(User);

            oos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    ObservableList<BranchManager> getAllBranchManagers() {
        ObjectInputStream ois = null;
        ObservableList<BranchManager> allbm = FXCollections.observableArrayList();
        try {
            BranchManager bm;
            ois = new ObjectInputStream(new FileInputStream("BranchManagers.bin"));
            while (true) {
                bm = (BranchManager) ois.readObject();
                allbm.add(bm);
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
        return allbm;
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

    private Parent loadUI(String ui) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/allFxml/" + ui + ".fxml"));
        MessageWriteUIController mwuc = loader.getController();
        //mwuc.sendUserInMessageContollerClass(user);
        Parent p = loader.load();
        return p;
    }

    @FXML
    private void logout(ActionEvent event) {
    }

    @FXML
    private void BackOnClick(ActionEvent event) {

    }
}
