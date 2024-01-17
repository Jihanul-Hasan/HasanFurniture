
package hasanfurniture;

import java.io.Serializable;
import javafx.scene.image.Image;


public class FurnitureType extends FurnitureRoom implements Serializable{
    
    
    
    protected Image iftypemg;
    protected String typename;

   

    public FurnitureType(String typename, String Roomname) {
        super(Roomname);
        this.typename = typename;
    }
    
    
}
