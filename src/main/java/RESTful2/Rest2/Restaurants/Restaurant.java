package RESTful2.Rest2.Restaurants;
import RESTful2.Rest2.Menu.Menu;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Restaurant")
public class Restaurant {

    @Column(name="restaurant_id")
    public @Id @GeneratedValue int id;
    public String name;
    public String image;

    Restaurant(){}

    Restaurant(String name, String image){
        this.name=name;
        this.image=image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @OneToMany(fetch = FetchType.EAGER,mappedBy="restaurant",cascade = CascadeType.ALL)
    private List<Menu> menus;

    @Override
    public String toString() {
        return "Restaurant{" + "id=" + this.id + ", name='" + this.name + '\'' + ", image='" + this.image + '\'' + '}';
    }
}
