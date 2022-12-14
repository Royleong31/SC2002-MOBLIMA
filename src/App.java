import enums.LoginStatus;
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
import java.util.Scanner;

import utils.DataUtils;
import model.Account.*;
import model.*;
import utils.Utils;
import enums.*;

/**
 * @author Roy Leong, Kish Choy, Augustine Lee
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
     * @param args arguments received
     */
    public static void main(String[] args) {
        System.out.println("\nWelcome to the MOBLIMA app!");
        Scanner sc = new Scanner(System.in);
        Integer choice;

        loadAppData();

        while (true) {
            System.out.println("\n------------------------------");
            System.out.println("Main Menu");
            System.out.println("------------------------------");
            System.out.println("Select your choice : \n 1. Just Browsing \n 2. Login to make booking/staff \n 3. Quit");
            try {
              choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
              System.out.println("Invalid input! Please try again.");
              continue;
            }
            if (choice != 1 && choice != 2 && choice != 3) {
                System.out.println("Invalid input. Please try again.");
                continue;
            }
            if (choice == 1) {
              System.out.println("\n------------------------------");
              System.out.println("GUEST page");
              System.out.println("------------------------------");
              consolesArr[3].display(new GuestAccount());
            }
            else if (choice == 2) {
                while (true) {
                    LoginStatus loginStatus = loginManager.getLoginStatus();
                    // matches the index of this.consolesArr
                    int consolesArrIndex = loginStatus.equals(LoginStatus.LOGIN) ? 0 : loginStatus.equals(LoginStatus.MOVIE_GOER) ? 1 : loginStatus.equals(LoginStatus.ADMIN) ? 2 : 3;
                    if (consolesArrIndex == 3) {
                        break;
                    }
                    System.out.println("\n------------------------------");
                    System.out.println(loginStatus + " page\n");
                    System.out.println("------------------------------");
                    consolesArr[consolesArrIndex].display(loginManager.getCurrentAccount());
                }
            }
            else {
                System.out.println("Goodbye and have a nice day!");
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
        if(DataUtils.checkFile("LoginManager-Accounts")) {
            ArrayList<Account> temp = (ArrayList<Account>)DataUtils.loadData("LoginManager-Accounts");
            loginManager.setUsers(temp);
        };

        if(DataUtils.checkFile("LoginManager-StaffIds")) {
            ArrayList<String> temp = (ArrayList<String>)DataUtils.loadData("LoginManager-StaffIds");
            loginManager.setUsedStaffIds(temp);
        };

        //BookingManager
        if(DataUtils.checkFile("BookingManager-Bookings")) {
            ArrayList<Booking> temp = (ArrayList<Booking>)DataUtils.loadData("BookingManager-Bookings");
            bookingManager.setBookingsArr(temp);
        };

        //CineplexManager
        if(DataUtils.checkFile("CineplexManager-Cineplexes")) {
            ArrayList<Cineplex> temp = (ArrayList<Cineplex>)DataUtils.loadData("CineplexManager-Cineplexes");   
            cineplexManager.setCineplexes(temp);
        };

        if(DataUtils.checkFile("CineplexManager-Cinemas")) {
            ArrayList<Cinema> temp = (ArrayList<Cinema>)DataUtils.loadData("CineplexManager-Cinemas");    
            cineplexManager.setCinemas(temp);
        };

        if(DataUtils.checkFile("CineplexManager-CinemaCounter")) {
            int temp = (int)DataUtils.loadData("CineplexManager-CinemaCounter");    
            cineplexManager.setCinemaCounter(temp);
        };

        //MovieManager
        if(DataUtils.checkFile("MovieManager-Movies")) {
            ArrayList<Movie> temp = (ArrayList<Movie>)DataUtils.loadData("MovieManager-Movies");       
            movieManager.setMovies(temp);
        };

        //ScreeningManager
        if(DataUtils.checkFile("ScreeningManager-Screenings")) {
            ArrayList<Screening> temp = (ArrayList<Screening>)DataUtils.loadData("ScreeningManager-Screenings");   
            screeningManager.setScreenings(temp);
        };

        //SystemManager
        if(DataUtils.checkFile("SystemManager-Holidays")) {
            ArrayList<DateTime> temp = (ArrayList<DateTime>)DataUtils.loadData("SystemManager-Holidays");     
            systemManager.setHolidays(temp);
        };

        if(DataUtils.checkFile("SystemManager-CinemaPrice")) {
            HashMap<CinemaType, Float> temp = (HashMap<CinemaType, Float>)DataUtils.loadData("SystemManager-CinemaPrice");     
            systemManager.setCinemaMultiplierHashmap(temp);
        };

        if(DataUtils.checkFile("SystemManager-SeatPrice")) {
            HashMap<SeatType, Float> temp = (HashMap<SeatType, Float>)DataUtils.loadData("SystemManager-SeatPrice");     
            systemManager.setSeatMultiplierHashmap(temp);
        };

        if(DataUtils.checkFile("SystemManager-MovieSortingCriteria")) {
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
        DataUtils.saveData(usersArr, "LoginManager-Accounts");

        ArrayList<String> usedStaffIds = loginManager.getUsedStaffIds();
        DataUtils.saveData(usedStaffIds, "LoginManager-StaffIds");
        
        //BookingManager
        ArrayList<Booking> bookingsArr = bookingManager.getBookings();
        DataUtils.saveData(bookingsArr, "BookingManager-Bookings");
        
        //BookingManager
        ArrayList<Cineplex> cineplexesArr = cineplexManager.getCineplexes();
        DataUtils.saveData(cineplexesArr, "CineplexManager-Cineplexes");
        
        ArrayList<Cinema> cinemasArr = cineplexManager.getCinemasList();
        DataUtils.saveData(cinemasArr, "CineplexManager-Cinemas");

        int cinemaCounter = cineplexManager.getCinemaCounter();
        DataUtils.saveData(cinemaCounter, "CineplexManager-CinemaCounter");

        //MovieManager
        ArrayList<Movie> moviesArr = movieManager.getMovies();
        DataUtils.saveData(moviesArr, "MovieManager-Movies");

        //ScreeningManager
        ArrayList<Screening> screeningsArr = screeningManager.getScreenings(null, null, null);
        DataUtils.saveData(screeningsArr, "ScreeningManager-Screenings");
        
        //SystemManager
        ArrayList<DateTime> holidaysArr = systemManager.getHolidays();
        DataUtils.saveData(holidaysArr, "SystemManager-Holidays");

        HashMap<CinemaType, Float> cinemaMultMap = systemManager.getCinemaMultiplierHashmap();
        DataUtils.saveData(cinemaMultMap, "SystemManager-CinemaPrice");

        HashMap<SeatType, Float> seatMultMap = systemManager.getSeatMultiplierHashmap();
        DataUtils.saveData(seatMultMap, "SystemManager-SeatPrice");

        SortCriteria movieSortingCriteria = systemManager.getSortingCriteria();
        DataUtils.saveData(movieSortingCriteria, "SystemManager-MovieSortingCriteria");
        
    }
}
