package hasanfurniture;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CustomerTableController implements Initializable {

    @FXML
    private TableView<Customer> userstable;

    @FXML
    private TableColumn<Customer, String> emailcol;

    @FXML
    private TableColumn<Customer, String> passwordcol;


    @FXML
    private TableColumn<Customer, LocalDate> dojcol;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        emailcol.setCellValueFactory(new PropertyValueFactory<Customer,String>("Email"));
        passwordcol.setCellValueFactory(new PropertyValueFactory<Customer,String>("Password"));
        dojcol.setCellValueFactory(new PropertyValueFactory<Customer,LocalDate>("DateOfJoining"));

        ObjectInputStream ois = null;
        
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        
        
        try {
            Customer c;
            ois = new ObjectInputStream(new FileInputStream("Customers.bin"));
            while (true) {
                c = (Customer) ois.readObject();
             
                customers.add(c);
                c.show();
            }

        } catch (IOException  ex ) {
            try {
                userstable.setItems(customers);
                
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e ) {
                
            }
           
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CustomerTableController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    }

}
