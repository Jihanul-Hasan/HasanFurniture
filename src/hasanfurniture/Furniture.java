package hasanfurniture;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Furniture extends FurnitureType implements Serializable {

    protected BufferedImage FurnitureImage;
    protected String name;
    protected int price;
    protected String color;
    protected LocalDate manufactureDate;
    protected String ImageFilePath;
    protected ArrayList<String> CommentsByUsers;

    public Furniture(String imagefilepath, String name, String type, String room,String color, int price, LocalDate manufactureDate) {
        super(type, room);
        //this.FurnitureImage = furimg;
        this.ImageFilePath=imagefilepath;
        this.name = name;
        this.price = price;
        this.color= color;
        this.manufactureDate = manufactureDate;
    }

    public void AddComments(String Comment) {
      
        CommentsByUsers.add(Comment);
    }

    public ArrayList<String> getCommentsByUsers() {
        return CommentsByUsers;
    }

   

   

    public BufferedImage getFurimg() {
        return FurnitureImage;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    void show() {
        System.out.println("name: " + this.name);
        System.out.println("price: " + this.price);
    }
}
