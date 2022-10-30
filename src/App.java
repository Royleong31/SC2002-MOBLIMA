import controller.LoginManager;
import view.AdminConsole;
import view.LoginConsole;
import view.MovieGoerConsole;
import view.ParentConsole;

/**
 * @author Roy Leong
 * @version 1.0
 * @since 2022-10-30
 */
public class App {
    /**
     * Contains stateful details about the user's log in status
     * currentAccount object can be obtained from loginManager and passed to other class methods
     */
    static final private LoginManager loginManager = new LoginManager();

    /**
     * Contains the possible consoles that can be selected
     */
    private final ParentConsole[] consolesArr = {new LoginConsole(loginManager), new MovieGoerConsole(), new AdminConsole()};

    /**
     * Varies based on the user's current log in status
     * Initially set to the 0(login console)
     * If the user logs into a movie goer account, it will be set to 1(movie goer console)
     * If the user logs into an admin account, it will be set to 2(admin console)
     */
    static private int currentConsoleIndex = 0; // varies based on the user's log in status


    /**
     * Contains the main method that runs the program
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
