package RESTful2.Rest2.Restaurants;

public class RestaurantNotFoundException extends RuntimeException {
    public RestaurantNotFoundException(int id) {
        super("could not find restaurant " + id);
    }
}
