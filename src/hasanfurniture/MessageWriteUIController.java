package hasanfurniture;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MessageWriteUIController implements Initializable {

    @FXML
    private TextField WriteMsgFx;

    @FXML
    private Label MessageUser;

    protected String timeAndDate;
    protected User userInClass;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void sendUserInMessageContollerClass(User user) {
        userInClass = user;
    }

    //...........To get The Logger User.............//
    ObservableList<User> getLoggedUser() {
        ObjectInputStream ois = null;
        ObservableList<User> allUsers = FXCollections.observableArrayList();
        try {
            User u;
            ois = new ObjectInputStream(new FileInputStream("LoggedUser.bin"));
            while (true) {
                u = (User) ois.readObject();
                allUsers.add(u);
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
        return allUsers;
    }

    //..........User To Whom the message is going to be sent.........//
    ObservableList<User> getMessageUser() {
        ObjectInputStream ois = null;
        ObservableList<User> allUsers = FXCollections.observableArrayList();
        try {
            User u;
            ois = new ObjectInputStream(new FileInputStream("MessageUser.bin"));
            while (true) {
                u = (User) ois.readObject();
                allUsers.add(u);
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
        return allUsers;
    }

///////----------TO GET THE MESSAGE SENT TIME----------//
    @FXML
    void WriteMsg(ActionEvent event) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        timeAndDate = dtf.format(now);
    }

    public void addMesagesToBinFile(Message message) {
        File f = null;
        f = new File("AllMessages.bin");

        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(f));
            oos.writeObject(message);

            oos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //************Send Message On click************//
    @FXML
    void SendMessageOnClick(ActionEvent event) {
        ObservableList<User> Sender = FXCollections.observableArrayList();
        ObservableList<User> Reciever = FXCollections.observableArrayList();

        Sender = getLoggedUser();
        Reciever = getMessageUser();
        for (User user : Reciever) {
            userInClass = user;
        }
        MessageUser.setText(userInClass.Email);
        for (User user : Sender) {
            ArrayList<String> messages = new ArrayList<>();
            messages.add(MessageUser.getText());
            Message message = new Message(userInClass, user, messages, timeAndDate);
            addMesagesToBinFile(message);
        }
        WriteMsgFx.clear();
    }

    @FXML
    void AttachPicOnClick(ActionEvent event) {

    }
}
