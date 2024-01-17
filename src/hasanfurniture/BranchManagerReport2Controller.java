
package hasanfurniture;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;

public class BranchManagerReport2Controller implements Initializable {

    
    @FXML
    private RadioButton p;

    @FXML
    private RadioButton i;

    @FXML
    private BorderPane borderpane;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    
    private Parent loadUI2(String ui) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/allFxml/" + ui + ".fxml"));
        Parent p = loader.load();
        return p;
    }
    @FXML
    void itemsold(ActionEvent event) throws IOException {
        
         borderpane.setCenter(loadUI2("report"));
    }

    @FXML
    void profit(ActionEvent event) {

    }
    
}
