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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AllUsersLoginController implements Initializable {

    @FXML
    private TextField useremailfx;
    @FXML
    private PasswordField userpasswordfx;
    @FXML
    private CheckBox remembermefx;
    @FXML
    private ComboBox designationfx;
    @FXML
    private Button loginbutton;
    @FXML
    private Label wrongpassmsg;

    // ----------------------------------------------------------------------------//
    ObservableList<Customer> AllCustomers = FXCollections.observableArrayList();
    ObservableList<BranchManager> AllBranchManagers = FXCollections.observableArrayList();
    ObservableList<FactoryManager> AllFactoryManagers = FXCollections.observableArrayList();
    ObservableList<IT> ITDepartment = FXCollections.observableArrayList();
    ObservableList<Supplier> AllSuppliers = FXCollections.observableArrayList();
    // ----------------------------------------------------------------------------//
    String designation = "";
    Customer customer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        designationfx.getItems().add("Branch Manager");
        designationfx.getItems().add("Factory Manager");
        designationfx.getItems().add("IT Department");
        designationfx.getItems().add("Supplier");
        designationfx.getItems().add("Customer");
    }

    // -------------------------Tried Data Passing--------------------------------------------//
    private void loadUI(ActionEvent event, Customer cus, String str) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/allFxml/" + str + ".fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
            window.setMaximized(true);
        } catch (IOException ex) {
            Logger.getLogger(AllUsersLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void loadUI2(ActionEvent event, String str) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/allFxml/" + str + ".fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        window.setMaximized(true);

    }

    @FXML
    private void checked(MouseEvent event) {
    }

    @FXML
    void designationisselected(ActionEvent event) {
        designation = designationfx.getValue().toString();

    }

    //..........Creating Bin File For Customer Object who just Logged in.........//
    void addToCartCustomerBinFile(Customer customer) {
        File f = null;
        f = new File("CartCustomer.bin");

        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(f));
            oos.writeObject(customer);
            customer.show();
            oos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
///...........Creating Bin File For User who just Loged in.............//

    void addToLoggedUserBinFile(User User) {
        File f = null;
        f = new File("LoggedUser.bin");

        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(f));
            oos.writeObject(User);

            oos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

//***************************LOGIN ON CLICK****************************//
    @FXML
    private void loginonclick(ActionEvent event) throws IOException {

        if (designation.equals("Customer")) {
            AllCustomers = getCustomers();
            for (Customer cus : AllCustomers) {
                //cus.show();
                if (cus.getEmail().equals(useremailfx.getText())) {
                    if (cus.getPassword().equals(userpasswordfx.getText())) {
                        loadUI(event, cus, "cus1");

                        addToCartCustomerBinFile(cus);

                    } else {
                        useremailfx.clear();
                        userpasswordfx.clear();
                        loginbutton.setStyle("-fx-background-color: #a32312; ");
                        wrongpassmsg.setText("Wrong Email or Password,try again..");
                    }
                }

            }
        }
        if (designation.equals("Branch Manager")) {
            AllBranchManagers = getBranchManagers();
            for (BranchManager bm : AllBranchManagers) {

                if (bm.getEmail().equals(useremailfx.getText())) {
                    if (bm.getPassword().equals(userpasswordfx.getText())) {
                        loadUI2(event, "bm1");
                        addToLoggedUserBinFile(bm);

                    } else {
                        useremailfx.clear();
                        userpasswordfx.clear();
                        loginbutton.setStyle("-fx-background-color: #a32312; ");
                        wrongpassmsg.setText("Wrong Email or Password,try again..");
                    }
                }

            }
        }
        if (designation.equals("Factory Manager")) {
            AllFactoryManagers = getFactoryManagers();
            for (FactoryManager fm : AllFactoryManagers) {

                if (fm.getEmail().equals(useremailfx.getText())) {
                    if (fm.getPassword().equals(userpasswordfx.getText())) {
                        loadUI2(event, "fm1");
                        addToLoggedUserBinFile(fm);
                    } else {
                        useremailfx.clear();
                        userpasswordfx.clear();
                        loginbutton.setStyle("-fx-background-color: #a32312; ");
                        wrongpassmsg.setText("Wrong Email or Password,try again..");
                    }
                }

            }
        }
        if (designation.equals("IT Department")) {
            ITDepartment = getITDepartment();
            for (IT it : ITDepartment) {

                if (it.getEmail().equals(useremailfx.getText())) {
                    if (it.getPassword().equals(userpasswordfx.getText())) {
                        loadUI2(event, "IT1");
                        addToLoggedUserBinFile(it);
                    } else {
                        useremailfx.clear();
                        userpasswordfx.clear();
                        loginbutton.setStyle("-fx-background-color: #a32312; ");
                        wrongpassmsg.setText("Wrong Email or Password,try again..");
                    }
                }

            }
        }
        if (designation.equals("Supplier")) {
            AllSuppliers = getSuppliers();
            for (Supplier s : AllSuppliers) {

                if (s.getEmail().equals(useremailfx.getText())) {
                    if (s.getPassword().equals(userpasswordfx.getText())) {
                        loadUI2(event, "s1");
                        addToLoggedUserBinFile(s);
                    } else {
                        useremailfx.clear();
                        userpasswordfx.clear();
                        loginbutton.setStyle("-fx-background-color: #a32312; ");
                        wrongpassmsg.setText("Wrong Email or Password,try again..");
                    }
                }

            }
        }
    }

//-------------------Object Reading from file for Customer---------------//
    public ObservableList<Customer> getCustomers() {
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        ObjectInputStream ois = null;
        try {
            Customer c;
            ois = new ObjectInputStream(new FileInputStream("Customers.bin"));
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
            Logger.getLogger(AllUsersLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customers;
    }

    //-------------------Object Reading from file for Branch Manager---------------//
    public ObservableList<BranchManager> getBranchManagers() {
        ObservableList<BranchManager> BranchManagers = FXCollections.observableArrayList();
        ObjectInputStream ois = null;
        try {
            BranchManager bm;
            ois = new ObjectInputStream(new FileInputStream("BranchManagers.bin"));
            while (true) {
                bm = (BranchManager) ois.readObject();
                BranchManagers.add(bm);
            }
        } catch (IOException ex) {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {

            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AllUsersLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return BranchManagers;
    }

    //-------------------Object Reading from file for Factory Manager---------------//
    public ObservableList<FactoryManager> getFactoryManagers() {
        ObservableList<FactoryManager> FactoryManagers = FXCollections.observableArrayList();
        ObjectInputStream ois = null;
        try {
            FactoryManager fm;
            ois = new ObjectInputStream(new FileInputStream("FactoryManagers.bin"));
            while (true) {
                fm = (FactoryManager) ois.readObject();
                FactoryManagers.add(fm);
            }
        } catch (IOException ex) {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {

            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AllUsersLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return FactoryManagers;
    }

    //-------------------Object Reading from file for IT Department---------------//
    public ObservableList<IT> getITDepartment() {
        ObservableList<IT> ItDepartment = FXCollections.observableArrayList();
        ObjectInputStream ois = null;
        try {
            IT it;
            ois = new ObjectInputStream(new FileInputStream("ITDepartment.bin"));
            while (true) {
                it = (IT) ois.readObject();
                ItDepartment.add(it);
            }
        } catch (IOException ex) {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {

            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AllUsersLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ItDepartment;
    }

    //-------------------Object Reading from file for Suppliers---------------//
    public ObservableList<Supplier> getSuppliers() {
        ObservableList<Supplier> Suppliers = FXCollections.observableArrayList();
        ObjectInputStream ois = null;
        try {
            Supplier s;
            ois = new ObjectInputStream(new FileInputStream("Suppliers.bin"));
            while (true) {
                s = (Supplier) ois.readObject();
                Suppliers.add(s);
            }
        } catch (IOException ex) {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {

            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AllUsersLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Suppliers;
    }

    @FXML
    private void forgotpassword(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/allFxml/forgotpasswordfix.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage newWindow = new Stage();
        newWindow.setScene(scene);
        newWindow.show();
    }

}
