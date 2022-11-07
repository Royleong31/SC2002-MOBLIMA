import enums.LoginStatus;
import model.Account.AdminAccount;
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
    private final static ParentConsole[] consolesArr = {new LoginConsole(loginManager), new MovieGoerConsole(), new AdminConsole()};

    /**
     * Contains the main method that runs the program
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Welcome to the MOBLIMA app!");

        while (true) {
            LoginStatus loginStatus = loginManager.getLoginStatus();
            System.out.println(loginStatus);
            // matches the index of this.consolesArr
            int consolesArrIndex = loginStatus.equals(LoginStatus.LOGIN) ? 0 : loginStatus.equals(LoginStatus.MOVIE_GOER) ? 1 : 2;
            // consolesArr[consolesArrIndex].display(loginManager.getCurrentAccount());
            consolesArr[2].display(new AdminAccount("fsdfsd", "fsadkfsad", "1234"));
        }
    }
}
