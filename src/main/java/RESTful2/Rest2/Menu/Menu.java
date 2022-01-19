package RESTful2.Rest2.Menu;

import RESTful2.Rest2.Restaurants.Restaurant;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@Table(name="Menu")
public class Menu {

    @Column(name="menu_id")
    public @Id @GeneratedValue int menu_id;
    public String title;

    Menu(){}

    Menu(String title){
        this.title=title;
    }

    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="restaurant_id")
    private Restaurant restaurant;

    @Override
    public String toString() {
        return "Menu{" + "id=" + this.menu_id + ", title='" + this.title + '}';
    }

//    public void retrieveMenus()
//    {
//        // Get list of all Restaurants and Menus
//        List<Restaurant> RestaurantList = RestaurantRepository.findAll();
//
//        // Displaying the Menu details
//        for (Restaurant restaurant : RestaurantList)
//        {
//            System.out.println("*** Restaurant Details ***");
//
//            System.out.println("Restaurant Id   : " + restaurant.getId());
//            System.out.println("Restaurant Name : " + restaurant.getName());
//
//            System.out.println("*** Restaurant Menu Details ***");
//            List<Menu> MenuList=MenuRepository.findAll();
//            for (Menu menu : MenuList)
//            {
//                System.out.println("Menu  : " + menu.getTitle());
//                System.out.println("Restaurant_ID    : " + menu.restaurant.getId());
//            }
//        }
//    }
}
