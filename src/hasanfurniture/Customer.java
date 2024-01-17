package hasanfurniture;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.time.LocalDate;

public class Customer extends User implements Serializable {

    protected BufferedImage ProfilePhoto;
    protected LocalDate DateOfJoining;

    public Customer(BufferedImage Pp, String email, String password, LocalDate doj) {

        super(email, password);
        this.Email = email;
        this.Password = password;
        this.DateOfJoining = doj;
        this.ProfilePhoto = Pp;
    }

    public LocalDate getDoj() {
        return DateOfJoining;
    }

    @Override
    public String getEmail() {
        return Email;
    }

    @Override
    public String getPassword() {
        return Password;
    }

    public void setDoj(LocalDate doj) {
        this.DateOfJoining = doj;
    }

    @Override
    public void setEmail(String email) {
        this.Email = email;
    }

    @Override
    public void setPassword(String password) {
        this.Password = password;
    }

    public void show() {
        System.out.println("Email: " + this.Email);
        System.out.println("Password: " + this.Password);
        System.out.println("DOJ: " + this.DateOfJoining.toString());
    }
}
