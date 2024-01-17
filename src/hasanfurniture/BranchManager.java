
package hasanfurniture;

import java.time.LocalDate;
import javafx.scene.image.Image;


public class BranchManager extends User{
     protected Image ProfilePhoto;
     protected LocalDate DateOfJoining;

    public BranchManager(String email, String password,  LocalDate doj) {

        super(email, password);
        this.Email= email;
        this.Password= password;
        this.DateOfJoining = doj;
    }
    
}
