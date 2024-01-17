
package hasanfurniture;

import java.io.Serializable;
import javafx.scene.image.Image;


public class FurnitureRoom implements Serializable {
    
    
    protected Image img;
    protected String Roomname;

    public FurnitureRoom(String Roomname) {
        this.Roomname = Roomname;
    }
    
    
    
}
