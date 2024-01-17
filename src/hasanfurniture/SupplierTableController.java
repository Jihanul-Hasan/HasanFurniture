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

public class SupplierTableController implements Initializable {

    @FXML
    private TableView<Supplier> userstable;

    @FXML
    private TableColumn<Supplier, String> useremailcol;

    @FXML
    private TableColumn<Supplier, String> userpasswordcol;

    @FXML
    private TableColumn<Supplier, String> userdescol;

    @FXML
    private TableColumn<Supplier, LocalDate> dojcol;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        useremailcol.setCellValueFactory(new PropertyValueFactory<Supplier, String>("email"));
        userpasswordcol.setCellValueFactory(new PropertyValueFactory<Supplier, String>("password"));
        dojcol.setCellValueFactory(new PropertyValueFactory<Supplier, LocalDate>("doj"));

        ObjectInputStream ois = null;
        
       
        ObservableList<Supplier> allsup = FXCollections.observableArrayList();
        
        try {
            Supplier s;
            ois = new ObjectInputStream(new FileInputStream("Suppliers.bin"));
            while (true) {
                s = (Supplier) ois.readObject();
               allsup.add(s);
            }

        } catch (IOException | ClassNotFoundException ex) {
            try {
                userstable.setItems(allsup);
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
