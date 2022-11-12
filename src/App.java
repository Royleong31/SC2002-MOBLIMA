import enums.LoginStatus;
import model.Account.AdminAccount;
import model.Account.MovieGoerAccount;
import controller.BookingManager;
import controller.CineplexManager;
import controller.LoginManager;
import controller.MovieManager;
import controller.ReviewManager;
import controller.ScreeningManager;
import controller.SystemManager;
import view.AdminConsole;
import view.LoginConsole;
import view.MovieGoerConsole;
import view.ParentConsole;
import utils.DataUtils;
import java.util.ArrayList;
import model.Account.*;
import model.*;

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
    static private LoginManager loginManager = new LoginManager();

    static private BookingManager bookingManager = new BookingManager();
    static private CineplexManager cineplexManager = new CineplexManager();
    static private MovieManager movieManager = new MovieManager();
    static private ReviewManager reviewManager = new ReviewManager();
    static private ScreeningManager screeningManager = new ScreeningManager();
    static private SystemManager systemManager = new SystemManager();

    /**
     * Contains the possible consoles that can be selected
     */
    private final static ParentConsole[] consolesArr = {
        new LoginConsole(loginManager), 
        new MovieGoerConsole(loginManager, bookingManager, cineplexManager, movieManager, reviewManager, screeningManager, systemManager), 
        new AdminConsole(loginManager, bookingManager, cineplexManager, movieManager, reviewManager, screeningManager, systemManager)
    };

    /**
     * Contains the main method that runs the program
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Welcome to the MOBLIMA app!");


        //LoginManager Data
        if(DataUtils.checkFile("LoginManager-Accounts") == true){
            ArrayList<Account> temp = (ArrayList<Account>)DataUtils.loadData("LoginManager-Accounts");
            if(temp != null){
                loginManager.setUsers(temp);
                System.out.println("LoginManager-Accounts Loaded");
            } else {
                System.out.println("LoginManager-Accounts Load Failed");
            }         
        };

        if(DataUtils.checkFile("LoginManager-StaffIds") == true){
            ArrayList<String> temp = (ArrayList<String>)DataUtils.loadData("LoginManager-StaffIds");
            if(temp != null){
                loginManager.setUsedStaffIds(temp);
                System.out.println("LoginManager-StaffIds Loaded");
            } else {
                System.out.println("LoginManager-StaffIds Load Failed");
            }         
        };

        //BookingManager
        if(DataUtils.checkFile("BookingManager-Bookings") == true){
            ArrayList<Booking> temp = (ArrayList<Booking>)DataUtils.loadData("BookingManager-Bookings");
            if(temp != null){
                bookingManager.setBookingsArr(temp);
                System.out.println("BookingManager-Bookings Loaded");
            } else {
                System.out.println("BookingManager-Bookings Load Failed");
            }         
        };

        //CineplexManager
        if(DataUtils.checkFile("CineplexManager-Cineplexes") == true){
            ArrayList<Cineplex> temp = (ArrayList<Cineplex>)DataUtils.loadData("CineplexManager-Cineplexes");
            if(temp != null){
                cineplexManager.setCineplexes(temp);
                System.out.println("CineplexManager-Cineplex Loaded");
            } else {
                System.out.println("CineplexManager-Cineplex Load Failed");
            }         
        };

        if(DataUtils.checkFile("CineplexManager-Cinemas") == true){
            ArrayList<Cinema> temp = (ArrayList<Cinema>)DataUtils.loadData("CineplexManager-Cinemas");
            if(temp != null){
                cineplexManager.setCinemas(temp);
                System.out.println("CineplexManager-Cinemas Loaded");
            } else {
                System.out.println("CineplexManager-Cinemas Load Failed");
            }         
        };

        //MovieManager
        if(DataUtils.checkFile("MovieManager-Movies") == true){
            ArrayList<Movie> temp = (ArrayList<Movie>)DataUtils.loadData("MovieManager-Movies");
            if(temp != null){
                movieManager.setMovies(temp);
                System.out.println("MovieManager-Movies Loaded");
            } else {
                System.out.println("MovieManager-Movies Load Failed");
            }         
        };

        //ScreeningManager
        if(DataUtils.checkFile("ScreeningManager-Screenings") == true){
            ArrayList<Screening> temp = (ArrayList<Screening>)DataUtils.loadData("ScreeningManager-Screenings");
            if(temp != null){
                screeningManager.setScreenings(temp);
                System.out.println("ScreeningManager-Screenings Loaded");
            } else {
                System.out.println("ScreeningManager-Screenings Load Failed");
            }         
        };

        //SystemManager
        if(DataUtils.checkFile("SystemManager-Holidays") == true){
            ArrayList<String> temp = (ArrayList<String>)DataUtils.loadData("SystemManager-Holidays");
            if(temp != null){
                systemManager.setHolidays(temp);
                System.out.println("SystemManager-Holidays Loaded");
            } else {
                System.out.println("SystemManager-Holidays Load Failed");
            }         
        };


        

        // if(DataUtils.checkFile("BookingManager") == true){
        //     BookingManager tempBk = (BookingManager)DataUtils.loadData("BookingManager");
        //     if(tempBk != null){
        //         bookingManager = tempBk;
        //         System.out.println("Booking Loaded");
        //     } else {
        //         System.out.println("Booking Load Failed");
        //     }         
        // };

        // if(DataUtils.checkFile("CineplexManager") == true){
        //     CineplexManager tempCp = (CineplexManager)DataUtils.loadData("CineplexManager");
        //     if(tempCp != null){
        //         cineplexManager = tempCp;
        //         System.out.println("Cineplex Loaded");
        //     } else {
        //         System.out.println("Cineplex Load Failed");
        //     }         
        // };
        
        // if(DataUtils.checkFile("LoginManager") == true){
        //     LoginManager tempLg = (LoginManager)DataUtils.loadData("LoginManager");
            
        //     if(tempLg != null){
        //         ArrayList<Account> usrsArr = tempLg.getUserArr();
        //         System.out.println("Printing all users loaded");
        //         for(Account acc:usrsArr) {
        //             System.out.println(acc.getUsername());
        //         }
                
        //         loginManager = tempLg;
        //         System.out.println("Login Loaded");
        //     } else {
        //         System.out.println("Login Load Failed");
        //     }         
        // };

        // if(DataUtils.checkFile("MovieManager") == true){
        //     MovieManager tempMm = (MovieManager)DataUtils.loadData("MovieManager");
        //     if(tempMm != null){
        //         movieManager = tempMm;
        //         System.out.println("Movie Loaded");
        //     } else {
        //         System.out.println("Movie Load Failed");
        //     }         
        // };

        // if(DataUtils.checkFile("ReviewManager") == true){
        //     ReviewManager tempRv = (ReviewManager)DataUtils.loadData("ReviewManager");
        //     if(tempRv != null){
        //         reviewManager = tempRv;
        //         System.out.println("Review Loaded");
        //     } else {
        //         System.out.println("Review Load Failed");
        //     }         
        // };

        // if(DataUtils.checkFile("ScreeningManager") == true){
        //     ScreeningManager tempSc = (ScreeningManager)DataUtils.loadData("ScreeningManager");
        //     if(tempSc != null){
        //         screeningManager = tempSc;
        //         System.out.println("Screening Loaded");
        //     } else {
        //         System.out.println("Screening Load Failed");
        //     }         
        // };

        // if(DataUtils.checkFile("SystemManager") == true){
        //     SystemManager tempSs = (SystemManager)DataUtils.loadData("SystemManager");
        //     if(tempSs != null){
        //         systemManager = tempSs;
        //         System.out.println("System Loaded");
        //     } else {
        //         System.out.println("System Load Failed");
        //     }         
        // };
        
        System.out.println("Load Done...");
        boolean exitStatus = false;
        while (exitStatus == false) {
            LoginStatus loginStatus = loginManager.getLoginStatus();
            System.out.println(loginStatus + " page\n");
            // matches the index of this.consolesArr
            int consolesArrIndex = loginStatus.equals(LoginStatus.LOGIN) ? 0 : loginStatus.equals(LoginStatus.MOVIE_GOER) ? 1 : 2;
            exitStatus = consolesArr[consolesArrIndex].display(loginManager.getCurrentAccount());
            // consolesArr[1].display(new MovieGoerAccount("fasds", "fsadsdfa", "fsdsfad", "fsadfsad", "fsad;l"));
        }

        // if(DataUtils.checkFile("LoginConsole") == true){
        //     ParentConsole temp = (LoginConsole)DataUtils.loadData("LoginConsole");
        //     if(temp != null){
        //         consolesArr[0] = temp;
        //         System.out.println("LoginConsole Loaded");
        //     } else {
        //         System.out.println("LoginConsole Load Failed");
        //     }         
        // };

        // if(DataUtils.checkFile("MovieGoerConsole") == true){
        //     ParentConsole temp = (MovieGoerConsole)DataUtils.loadData("MovieGoerConsole");
        //     if(temp != null){
        //         consolesArr[1] = temp;
        //         System.out.println("MovieGoerConsole Loaded");
        //     } else {
        //         System.out.println("MovieGoerConsole Load Failed");
        //     }         
        // };

        // if(DataUtils.checkFile("AdminConsole") == true){
        //     AdminConsole temp = (AdminConsole)DataUtils.loadData("AdminConsole");
        //     if(temp != null){
        //         consolesArr[2] = temp;
        //         System.out.println("AdminConsole Loaded");
        //     } else {
        //         System.out.println("AdminConsole Load Failed");
        //     }         
        // };


        System.out.println("Saving data...");
        
        //LoginManager
        ArrayList<Account> usersArr = loginManager.getUsers();
        int usersArrErr = DataUtils.saveData(usersArr, "LoginManager-Accounts");
        System.out.println("LoginManager-Accounts Error: " + usersArrErr);

        ArrayList<String> usedStaffIds = loginManager.getUsedStaffIds();
        int usedStaffIdsErr = DataUtils.saveData(usedStaffIds, "LoginManager-StaffIds");
        System.out.println("LoginManager-StaffIds Error: " + usedStaffIdsErr);
        
        //BookingManager
        ArrayList<Booking> bookingsArr = bookingManager.getBookings();
        int bookingsArrErr = DataUtils.saveData(bookingsArr, "BookingManager-Bookings");
        System.out.println("BookingManager-Bookings Error: " + bookingsArrErr);
        
        //BookingManager
        ArrayList<Cineplex> cineplexesArr = cineplexManager.getCineplexes();
        int cineplexesArrErr = DataUtils.saveData(cineplexesArr, "CineplexManager-Cineplexes");
        System.out.println("CineplexManager-Cineplexes Error: " + cineplexesArrErr);
        
        ArrayList<Cinema> cinemasArr = cineplexManager.getCinemasList();
        int cinemasArrErr = DataUtils.saveData(cinemasArr, "CineplexManager-Cinemas");
        System.out.println("CineplexManager-Cinemas Error: " + cinemasArrErr);

        //MovieManager
        ArrayList<Movie> moviesArr = movieManager.getMovies();
        int moviesArrErr = DataUtils.saveData(moviesArr, "MovieManager-Movies");
        System.out.println("MovieManager-Movies Error: " + moviesArrErr);

        //ScreeningManager
        ArrayList<Screening> screeningsArr = screeningManager.getScreenings(null, null, null);
        int screeningsArrErr = DataUtils.saveData(screeningsArr, "ScreeningManager-Screenings");
        System.out.println("ScreeningManager-Screenings Error: " + screeningsArrErr);
        
        //SystemManager
        ArrayList<String> holidaysArr = systemManager.getHolidays();
        int holidaysArrErr = DataUtils.saveData(holidaysArr, "SystemManager-Holidays");
        System.out.println("SystemManager-Holidays Error: " + holidaysArrErr);

        // int bookingManagerErr = DataUtils.saveData(bookingManager, "BookingManager");
        // int cineplexManagerErr = DataUtils.saveData(cineplexManager, "CineplexManager");
        // int loginManagerErr = DataUtils.saveData(loginManager, "LoginManager");
        // int movieManagerErr = DataUtils.saveData(movieManager, "MovieManager");
        // int reviewManagerErr = DataUtils.saveData(reviewManager, "ReviewManager");
        // int screeningManagerErr = DataUtils.saveData(screeningManager, "ScreeningManager");
        // int systemManagerErr = DataUtils.saveData(systemManager, "SystemManager");
        
        // int loginConsoleErr = DataUtils.saveData(consolesArr[0], "LoginConsole");
        // int movieGoerConsoleErr = DataUtils.saveData(consolesArr[1], "MovieGoerConsole");
        // int adminConsoleErr = DataUtils.saveData(consolesArr[2], "AdminConsole");


        
        // System.out.println("Error Codes: ");
        // System.out.println("BookingManager: " + bookingManagerErr);
        // System.out.println("CineplexManager: " + cineplexManagerErr);
        // System.out.println("LoginManager: " + loginManagerErr);
        // System.out.println("MovieManager: " + movieManagerErr);
        // System.out.println("ReviewManager: " + reviewManagerErr);
        // System.out.println("ScreeningManager: " + screeningManagerErr);
        // System.out.println("SystemManager: " + systemManagerErr);
        // System.out.println("LoginConsoleErr: " + loginConsoleErr);
        // System.out.println("MovieGoerConsoleErr: " + movieGoerConsoleErr);
        // System.out.println("AdminConsoleErr: " + adminConsoleErr);

        // if(bookingManagerErr == 0 && cineplexManagerErr == 0 && loginManagerErr == 0 && movieManagerErr == 0 && reviewManagerErr == 0 && screeningManagerErr == 0 && systemManagerErr == 0){
        // System.out.println("All is good, Save Successful!");
        // } else {
        // System.out.println("Save Failed!");
        // }
        System.out.println("EXITED PROGRAM HERE");
    }
}
