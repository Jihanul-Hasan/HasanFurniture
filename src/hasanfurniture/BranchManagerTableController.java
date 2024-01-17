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

public class BranchManagerTableController implements Initializable {

    @FXML
    private TableView<BranchManager> userstable;

    @FXML
    private TableColumn<BranchManager, String> useremailcol;

    @FXML
    private TableColumn<BranchManager, String> userpasswordcol;

    @FXML
    private TableColumn<BranchManager, String> userdescol;

    @FXML
    private TableColumn<BranchManager, LocalDate> dojcol;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        useremailcol.setCellValueFactory(new PropertyValueFactory<BranchManager, String>("Email"));
        userpasswordcol.setCellValueFactory(new PropertyValueFactory<BranchManager, String>("Password"));
        dojcol.setCellValueFactory(new PropertyValueFactory<BranchManager, LocalDate>("DateOfJoining"));
       
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
                userstable.setItems(allbm);
                
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
