
package hasanfurniture;

import java.io.Serializable;
import java.util.ArrayList;


public class Message implements Serializable{
    
    protected User Receiver;
    protected User Sender;
    protected String MessageSendTime;
    protected ArrayList<String> allMessages;

    public Message(User user,User sender,  ArrayList<String> allMessages,String MessageSendTime) {
        this.Receiver = user;
        this.Sender=sender;
        this.MessageSendTime = MessageSendTime;
        this.allMessages = allMessages;
    }
    
  
    
}
