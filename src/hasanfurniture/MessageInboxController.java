package hasanfurniture;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MessageInboxController implements Initializable {
    
    protected User userInClass;
    protected Message messageInClass;
    
    @FXML
    private VBox AllMessagesInInbox;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<User> allUsers = FXCollections.observableArrayList();
        allUsers = getLoggedUser();
        for (User user : allUsers) {
            userInClass = user;
        }
        
        ObservableList<Message> allmsgs = FXCollections.observableArrayList();
        allmsgs = getMessages();
        
        for (Message messageInclass : allmsgs) {
            
            if (userInClass.equals(messageInClass.Receiver)) {
                
                Label label = new Label();
                label.setText(messageInclass.allMessages.get(0));
                AllMessagesInInbox.getChildren().add(label);
                Label label2 = new Label();
                label.setText(messageInclass.MessageSendTime);
                
            }
            
        }
        
    }
    
    ObservableList<Message> getMessages() {
        ObjectInputStream ois = null;
        ObservableList<Message> allmsgs = FXCollections.observableArrayList();
        try {
            Message u;
            ois = new ObjectInputStream(new FileInputStream("AllMessages.bin"));
            while (true) {
                u = (Message) ois.readObject();
                allmsgs.add(u);
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
        return allmsgs;
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
    
}
