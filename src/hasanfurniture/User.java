package hasanfurniture;

import java.io.Serializable;

public class User implements Serializable {

    
    protected String Email;
    protected String Password;
    //protected String UserType;

    public User(String email, String pass) {
     //   this.Email = Email;
     //   this.Password = pass;
       
   }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }


}
