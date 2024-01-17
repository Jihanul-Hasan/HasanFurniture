
package hasanfurniture;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.time.LocalDate;


public class Supplier extends User implements Serializable {
    protected BufferedImage ProfilePhoto;
    protected LocalDate DateOfJoining;
     
    public Supplier (String email, String password,  LocalDate doj) {

        super(email, password);
        this.Email= email;
        this.Password= password;
        this.DateOfJoining = doj;
    }
    
}
