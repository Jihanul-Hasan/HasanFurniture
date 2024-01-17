package hasanfurniture;

import java.io.Serializable;
import java.time.LocalDate;
import javafx.scene.image.Image;

public class IT extends User implements Serializable {

    protected Image ProfilePhoto;
    protected LocalDate DateOfJoining;

    public IT(String email, String password, LocalDate doj) {

        super(email, password);
        this.Email = email;
        this.Password = password;
        this.DateOfJoining = doj;
    }
}
