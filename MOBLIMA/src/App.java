import person.User;

public class App {
    // extract all arrays and primitive variables into a separate class
    // e.g. BookingManager, LoginManager etc.
    private User[] listOfUsers; // store in a binary file and load on startup
    private User currentUser;
    private Console console; // admin, log in or movie goer, initially it's set to log in
    // Console is a static class and its children (AdminConsole, MovieGoerConsole, LoginConsole) are static classes
    // if can't use static class then assign a new class object for whenever the user logs in or out
    // CONSOLE classes should all be stateless

    // array ofn confirmed bookings, Single source of truth for bookings, theatre full or not etc
    private Booking[] listOfBookings; // store in a binary file and load on startup
    private Movies[] listOfMovies; // list of all movies that have been created by the employees


    
    public static void main(String[] args) throws Exception {
        // load the list of users from binary file into listOfUsers
        
        // show the options for the user (log in console)
        // log in console allows you to log in or register
        // when logging in, check from listOfUsers and then set currentUser to the user
        // for creating new user, create a new user and add to listOfUsers, and then set currentUser

        // LOGIN Flow
        // pass the listOfUsers to login console
        // login console will check if the username alr exists
        // if it does, it will prompt user again until it succeeds or the user quits
        // once succesful, return the account
        // set currentUser to the account returned

        // REGISTER flow
        // check for duplicates
        // similar to login, console will create a new user and use it to create a new account and add it to the listOfUsers arr

        // once you are logged in
        // use the user's type, whether admin or movie-goer to determine what type of console to show
        // change console attribute to either admin or movie goer console
        // if admin, show 
        // set current user


        // Console class
        // Use a common function in the Console class to get a movie from the showtimes, cinemas and movies because it's used by both the admins and the movie-goers. This is from the Search Engine class
        // Will accept MoviesCatalog and ScreeningManager as a parameter for movie related functions
        // return the relevant screening


        // logged in Movie-Goer, using the MovieGoerConsole
        // BookingManager is a static class
        // Assume that the user is looged in and is alr a MovieGoer user as it's checked in main
        /*
         * 1. Make a booking
         * 2. Search for all movies, all cineplexes that show that movie and allow the user to choose a showtime for a particular movie and cineplex
         * 3. Submit reviews
         */
        // 1. Make a booking
        // display the available movies to the user and let them choose
        // in makeBooking function, IO will be done to ask the user for their details return a new booking
        // show available seats by calling BookingManager.showAvailableSeats() with the list of bookings for the current cineplex and movie and time
        // show the layout of the cinema
        // check that the selected seat is available to be booked
        // use the data and call BookingManager.makeBooking() to make the booking
        // in makeBooking function, check available seats and then check if the seat is available
        // if available, create the booking and add it to the listOfBookings, return the booking
        // call addSale in SalesManagementSystem to add the sale amount to the total sales AND by movie 
        // create a mapping of movie to total sales for that movie (this allows admin to sort by sales)
        // display the new booking object to the user
        
        // 2. Search for show times and movies
        // use SearchEngine to search for movies and select a cineplex for that movie, and then a showtime for that cineplex and movie, then return that movie
        // the movie object can be reused to book tickets, add review etc in another function
        // display the movie object with cast, reviews, etc


        // 3. Submit review
        // pass in the movie as an argument
        // check whether the user has already submitted a review for the movie, if so, dont' allow the user to add a movie, print out a message saying that they have already submitted a review
        // if not, get the user input to create a new review and append to the listOfReviews
        // apppend the new review to the list of reviews in the Movie object

        // Logged in Admin, using the AdminConsole
        // Assume that the user is looged in and is alr an admin user as it's checked in main
        // MovieManager class contains all the functions for CRUD of movies and storing the list of movies
        // SalesManagementSystem stores the state and CRUD for prices and holidays
        // Movies ranking is done by movies manager (perhaps this shouldn't be there maybe create a filter class)
        /*
         * 1. Create/Read/Update/Delete a movie
         * 2. Create/Read/Update/Delete cineplex
         * 3. Get total sales
         * 4. Create/Read/Update/Delete holidays
         * 5. Create/Read/Update/Delete default prices (prices are calculated from default price and then multiplied by the holiday multiplier)
         * 6. Rank movies by ticket sales/average rating
         * 7. CRUD screenings using Screening Manager
         */
        //  Create (for all create functions)
        // Get the user input details and create a new object and then store in an array in the right location, eg. listOfMovies in MovieCatalog
        // Update (for all update functions)
        // Check that the object exists, if it does, get the user input details and update the object, show the current details in the console 
        // for e.g., if you are updating a movie, show the current movie details and allow the user to key in the new details (small thing for better UX)
        // Delete (for all delete functions)
        // Delete object from array if it exists, otherwise show an error
        // Get total sales
        // Call SalesManagementSystem to get the totalSales for the cineplex and the totalSales for each movie

        // System Configurator class
        // for setting holidays and ticket prices


        // SCREENINGManager
        // stores the screenings and allows the AdminConsole to CRUD screenings with ScreeningManager's methods
        // filter by the movie and cineplex. Movie and cineplex should allow optional params so users can get all movies or all cineplexes
        // an array of screenings is returned

        // Screening
        // contains state for the movie including movie, cineplex etc.
        // getPrice function whereby the price is calculated from the holiday status, holiday premium etc
        // Screening is inside of booking


        // Ticket
        // Ticket contains the screening and the Seat object

    }
}
