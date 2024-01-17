package hasanfurniture;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ITTableController implements Initializable {

    @FXML
    private TableView<IT> userstable;

    @FXML
    private TableColumn<IT, String> useremailcol;

    @FXML
    private TableColumn<IT, String> userpasswordcol;

    @FXML
    private TableColumn<IT, String> userdescol;

    @FXML
    private TableColumn<IT, LocalDate> dojcol;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        useremailcol.setCellValueFactory(new PropertyValueFactory<IT, String>("Email"));
        userpasswordcol.setCellValueFactory(new PropertyValueFactory<IT, String>("Password"));
        dojcol.setCellValueFactory(new PropertyValueFactory<IT, LocalDate>("DateOfJoining"));

        ObjectInputStream ois = null;
       
        ObservableList<IT> allit = FXCollections.observableArrayList();
        
        try {
            IT it;
            ois = new ObjectInputStream(new FileInputStream("ITDepartment.bin"));
            while (true) {
                it = (IT) ois.readObject();
               allit.add(it);
            }

        } catch (IOException | ClassNotFoundException ex) {
            try {
                userstable.setItems(allit);
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            ex.printStackTrace();
        }

        
    }
}
