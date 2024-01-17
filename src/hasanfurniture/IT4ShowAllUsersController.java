/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class IT4ShowAllUsersController implements Initializable {

    @FXML
    private BorderPane itscene;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
     //***********This Scene Loads Table showing all Types of Users******//
    
    private void loadUI(String ui) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/allFxml/" + ui + ".fxml"));
            itscene.setCenter(root);
        } catch (IOException ex) {
            Logger.getLogger(ITScene2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void gotoscene(ActionEvent event, String str) throws IOException {
        Parent scene2Parent = FXMLLoader.load(getClass().getResource("/allFxml/" + str + ".fxml"));
        Scene scene2 = new Scene(scene2Parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();
        window.setMaximized(true);
    }

    @FXML
    void showallcus(ActionEvent event) {

        loadUI("cusTable");

    }

    @FXML
    void showallbm(ActionEvent event) {
        loadUI("bmtable");

    }

    @FXML
    void showallfm(ActionEvent event) {
        loadUI("fmTable");
    }

    @FXML
    void showallit(ActionEvent event) {
        loadUI("itTable");
    }

    @FXML
    void showallsup(ActionEvent event) {
        loadUI("supTable");
    }

    @FXML
    void nothing(MouseEvent event) {

    }

    @FXML
    void BackOnClick(ActionEvent event) throws IOException {
        gotoscene(event, "IT2");
    }
}
