package RESTful2.Rest2.Menu;

public class MenuNotFoundException extends RuntimeException{
    public MenuNotFoundException(int menu_id) {
        super("could not find restaurant " + menu_id);
    }
}
