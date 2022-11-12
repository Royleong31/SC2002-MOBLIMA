import enums.LoginStatus;
import model.Account.AdminAccount;
import model.Account.MovieGoerAccount;
import model.Account.GuestAccount;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

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
import view.GuestConsole;
import view.ParentConsole;
import utils.DataUtils;
import model.Account.*;
import model.*;
import utils.Utils;
import enums.*;

/**
 * @author Roy Leong, Kish Choy
 * @version 1.0
 * @since 2022-10-30
 */
public class App {
    /**
     * Contains stateful details about the user's log in status
     * currentAccount object can be obtained from loginManager and passed to other class methods
     */
    static final private LoginManager loginManager = new LoginManager();

    static final private BookingManager bookingManager = new BookingManager();
    static final private CineplexManager cineplexManager = new CineplexManager();
    static final private MovieManager movieManager = new MovieManager();
    static final private ReviewManager reviewManager = new ReviewManager();
    static final private ScreeningManager screeningManager = new ScreeningManager();
    static final private SystemManager systemManager = new SystemManager();

    /**
     * Contains the possible consoles that can be selected
     */
    private final static ParentConsole[] consolesArr = {
        new LoginConsole(loginManager), 
        new MovieGoerConsole(loginManager, bookingManager, cineplexManager, movieManager, reviewManager, screeningManager, systemManager), 
        new AdminConsole(loginManager, bookingManager, cineplexManager, movieManager, reviewManager, screeningManager, systemManager),
        new GuestConsole(loginManager, bookingManager, cineplexManager, movieManager, reviewManager, screeningManager, systemManager)
    };

    /**
     * Contains the main method that runs the program
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Welcome to the MOBLIMA app!");
        Scanner sc = new Scanner(System.in);

        loadAppData();

        while (true) {
            System.out.println("Select your choice : \n 1. Just Browsing \n 2. Login to make booking/staff \n 3. Quit");
            Integer choice = Integer.parseInt(sc.nextLine());
            if (choice != 1 && choice != 2 && choice != 3) {
                System.out.println("Invalid input. Please try again.");
                continue;
            }
            if (choice == 1) {
                consolesArr[3].display(new GuestAccount());
            }
            else if (choice == 2) {
                while (true) {
                    LoginStatus loginStatus = loginManager.getLoginStatus();
                    System.out.println(loginStatus + " page\n");
                    // matches the index of this.consolesArr
                    int consolesArrIndex = loginStatus.equals(LoginStatus.LOGIN) ? 0 : loginStatus.equals(LoginStatus.MOVIE_GOER) ? 1 : loginStatus.equals(LoginStatus.ADMIN) ? 2 : 3;
                    if (consolesArrIndex == 3) {
                        break;
                    }
                    consolesArr[consolesArrIndex].display(loginManager.getCurrentAccount());
                }
            }
            else {
                sc.close();
                break;
            }
        }

        saveAppData();

    }

    /**
     * Deserialize and load all persistent application data
     */
    private static void loadAppData(){
        //LoginManager Data
        if(DataUtils.checkFile("LoginManager-Accounts") == true){
            ArrayList<Account> temp = (ArrayList<Account>)DataUtils.loadData("LoginManager-Accounts");
            loginManager.setUsers(temp);
        };

        if(DataUtils.checkFile("LoginManager-StaffIds") == true){
            ArrayList<String> temp = (ArrayList<String>)DataUtils.loadData("LoginManager-StaffIds");
            loginManager.setUsedStaffIds(temp);
        };

        //BookingManager
        if(DataUtils.checkFile("BookingManager-Bookings") == true){
            ArrayList<Booking> temp = (ArrayList<Booking>)DataUtils.loadData("BookingManager-Bookings");
            bookingManager.setBookingsArr(temp);
        };

        //CineplexManager
        if(DataUtils.checkFile("CineplexManager-Cineplexes") == true){
            ArrayList<Cineplex> temp = (ArrayList<Cineplex>)DataUtils.loadData("CineplexManager-Cineplexes");   
            cineplexManager.setCineplexes(temp);
        };

        if(DataUtils.checkFile("CineplexManager-Cinemas") == true){
            ArrayList<Cinema> temp = (ArrayList<Cinema>)DataUtils.loadData("CineplexManager-Cinemas");    
            cineplexManager.setCinemas(temp);
        };

        //MovieManager
        if(DataUtils.checkFile("MovieManager-Movies") == true){
            ArrayList<Movie> temp = (ArrayList<Movie>)DataUtils.loadData("MovieManager-Movies");       
            movieManager.setMovies(temp);
        };

        //ScreeningManager
        if(DataUtils.checkFile("ScreeningManager-Screenings") == true){
            ArrayList<Screening> temp = (ArrayList<Screening>)DataUtils.loadData("ScreeningManager-Screenings");   
            screeningManager.setScreenings(temp);
        };

        //SystemManager
        if(DataUtils.checkFile("SystemManager-Holidays") == true){
            ArrayList<DateTime> temp = (ArrayList<DateTime>)DataUtils.loadData("SystemManager-Holidays");     
            systemManager.setHolidays(temp);
        };

        if(DataUtils.checkFile("SystemManager-CinemaPrice") == true){
            HashMap<CinemaType, Float> temp = (HashMap<CinemaType, Float>)DataUtils.loadData("SystemManager-CinemaPrice");     
            systemManager.setCinemaMultiplierHashmap(temp);
        };

        if(DataUtils.checkFile("SystemManager-SeatPrice") == true){
            HashMap<SeatType, Float> temp = (HashMap<SeatType, Float>)DataUtils.loadData("SystemManager-SeatPrice");     
            systemManager.setSeatMultiplierHashmap(temp);
        };

        if(DataUtils.checkFile("SystemManager-MovieSortingCriteria") == true){
            SortCriteria temp = (SortCriteria)DataUtils.loadData("SystemManager-MovieSortingCriteria");
            try{
                systemManager.setSortingCriteria(temp);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        };
        
        System.out.println("Data Load Done...");
    }

    /**
     * Serialize and save all persistent application data
     */
    private static void saveAppData(){
        System.out.println("Saving data...");
        
        //LoginManager
        ArrayList<Account> usersArr = loginManager.getUsers();
        int usersArrErr = DataUtils.saveData(usersArr, "LoginManager-Accounts");

        ArrayList<String> usedStaffIds = loginManager.getUsedStaffIds();
        int usedStaffIdsErr = DataUtils.saveData(usedStaffIds, "LoginManager-StaffIds");
        
        //BookingManager
        ArrayList<Booking> bookingsArr = bookingManager.getBookings();
        int bookingsArrErr = DataUtils.saveData(bookingsArr, "BookingManager-Bookings");
        
        //BookingManager
        ArrayList<Cineplex> cineplexesArr = cineplexManager.getCineplexes();
        int cineplexesArrErr = DataUtils.saveData(cineplexesArr, "CineplexManager-Cineplexes");
        
        ArrayList<Cinema> cinemasArr = cineplexManager.getCinemasList();
        int cinemasArrErr = DataUtils.saveData(cinemasArr, "CineplexManager-Cinemas");

        //MovieManager
        ArrayList<Movie> moviesArr = movieManager.getMovies();
        int moviesArrErr = DataUtils.saveData(moviesArr, "MovieManager-Movies");

        //ScreeningManager
        ArrayList<Screening> screeningsArr = screeningManager.getScreenings(null, null, null);
        int screeningsArrErr = DataUtils.saveData(screeningsArr, "ScreeningManager-Screenings");
        
        //SystemManager
        ArrayList<DateTime> holidaysArr = systemManager.getHolidays();
        int holidaysArrErr = DataUtils.saveData(holidaysArr, "SystemManager-Holidays");

        HashMap<CinemaType, Float> cinemaMultMap = systemManager.getCinemaMultiplierHashmap();
        int cinemaMultMapErr = DataUtils.saveData(cinemaMultMap, "SystemManager-CinemaPrice");

        HashMap<SeatType, Float> seatMultMap = systemManager.getSeatMultiplierHashmap();
        int seatMultMapErr = DataUtils.saveData(seatMultMap, "SystemManager-SeatPrice");

        SortCriteria movieSortingCriteria = systemManager.getSortingCriteria();
        int movieSortingCriteriaErr = DataUtils.saveData(movieSortingCriteria, "SystemManager-MovieSortingCriteria");
        
    }
}
