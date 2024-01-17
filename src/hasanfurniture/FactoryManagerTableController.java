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

public class FactoryManagerTableController implements Initializable {

    @FXML
    private TableView<FactoryManager> userstable;

    @FXML
    private TableColumn<FactoryManager, String> useremailcol;

    @FXML
    private TableColumn<FactoryManager, String> userpasswordcol;

    @FXML
    private TableColumn<FactoryManager, String> userdescol;

    @FXML
    private TableColumn<FactoryManager, LocalDate> dojcol;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        useremailcol.setCellValueFactory(new PropertyValueFactory<FactoryManager, String>("Email"));
        userpasswordcol.setCellValueFactory(new PropertyValueFactory<FactoryManager, String>("Password"));
        dojcol.setCellValueFactory(new PropertyValueFactory<FactoryManager, LocalDate>("DateOfJoining"));

        ObjectInputStream ois = null;
        
        ObservableList<FactoryManager> allfm = FXCollections.observableArrayList();
        
        try {
            FactoryManager fm;
            ois = new ObjectInputStream(new FileInputStream("FactoryManagers.bin"));
            while (true) {
                fm = (FactoryManager) ois.readObject();
               allfm.add(fm);
            }

        } catch (IOException | ClassNotFoundException ex) {
            try {
                userstable.setItems(allfm);
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
