package hasanfurniture;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

public class IT3AddUserController implements Initializable {

    @FXML
    private TextField emailfx;
    @FXML
    private TextField passwordfx;
    @FXML
    private ComboBox designationfx;
    @FXML
    private DatePicker doj;

    public String designationstr;
    Customer cus = null;
    BranchManager bm;
    FactoryManager fm;
    IT it;
    Supplier s;
    BufferedImage img = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        designationfx.getItems().add("Branch Manager");
        designationfx.getItems().add("Factory Manager");
        designationfx.getItems().add("IT Department");
        designationfx.getItems().add("Supplier");
        designationfx.getItems().add("Customer");

    }

    @FXML
    void designation(ActionEvent event) {

        designationstr = designationfx.getValue().toString();

    }

    private BufferedImage AddUserImage(BufferedImage buffered) throws IOException {

        FileChooser fc = new FileChooser();
        File f = fc.showOpenDialog(null);
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text files", ".JPG"));

        if (f != null) {

           // String s = f.toURI().toString();

           // Image image = new Image(s);
            BufferedImage bi = ImageIO.read(f);
            buffered = bi;

            //File file = new File("/src/Photos/report.png");
            //ImageIO.write(SwingFXUtils.fromFXImage(image, null), "PNG", file);

        }
        return buffered;
    }

    @FXML
    void AddUserImageFx(ActionEvent event) throws IOException {

        AddUserImage(img);
    }

    @FXML
    private void addToBinFileOnClick(ActionEvent event) throws IOException {

        if (designationstr.equals("Customer")) {

            
            File f = null;
            Customer customer = new Customer(img, emailfx.getText(), passwordfx.getText(), doj.getValue());

            f = new File("Customers.bin");

            ObjectOutputStream oos = null;
            try {
                if (f.exists()) {
                    oos = new AppendableObjectOutputStream(new FileOutputStream(f, true));
                } else {
                    oos = new ObjectOutputStream(new FileOutputStream(f));
                }

                oos.writeObject(customer);
                oos.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

        if (designationstr.equals("Branch Manager")) {

            File f = null;
            BranchManager branchmanager = new BranchManager(emailfx.getText(), passwordfx.getText(), doj.getValue());
            f = new File("BranchManagers.bin");
            ObjectOutputStream oos = null;
            try {
                if (f.exists()) {
                    oos = new AppendableObjectOutputStream(new FileOutputStream(f, true));
                } else {
                    oos = new ObjectOutputStream(new FileOutputStream(f));
                }

                oos.writeObject(branchmanager);
                oos.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

        if (designationstr.equals("Factory Manager")) {
            File f = null;
            FactoryManager factorymanager = new FactoryManager(emailfx.getText(), passwordfx.getText(), doj.getValue());

            f = new File("FactoryManagers.bin");
            ObjectOutputStream oos = null;
            try {
                if (f.exists()) {
                    oos = new AppendableObjectOutputStream(new FileOutputStream(f, true));
                } else {
                    oos = new ObjectOutputStream(new FileOutputStream(f));
                }

                oos.writeObject(factorymanager);
                oos.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (designationstr.equals("IT Department")) {
            File f = null;
            IT it = new IT(emailfx.getText(), passwordfx.getText(), doj.getValue());

            f = new File("ITDepartment.bin");
            ObjectOutputStream oos = null;
            try {
                if (f.exists()) {
                    oos = new AppendableObjectOutputStream(new FileOutputStream(f, true));
                } else {
                    oos = new ObjectOutputStream(new FileOutputStream(f));
                }
                oos.writeObject(it);
                oos.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if (designationstr.equals("Supplier")) {
            File f = null;
            Supplier suplier = new Supplier(emailfx.getText(), passwordfx.getText(), doj.getValue());

            f = new File("Suppliers.bin");
            ObjectOutputStream oos = null;
            try {
                if (f.exists()) {
                    oos = new AppendableObjectOutputStream(new FileOutputStream(f, true));
                } else {
                    oos = new ObjectOutputStream(new FileOutputStream(f));
                }

                oos.writeObject(suplier);
                oos.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        emailfx.setText(null);
        passwordfx.setText(null);
    }

}
