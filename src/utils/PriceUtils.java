package utils;

import controller.SystemManager;
import model.DateTime;
import model.Screening;
import model.Ticket;
import enums.SeatType;
import enums.TicketType;
import enums.CinemaType;
import enums.MovieType;

/**
 * Use this to calculate the price of each ticket at a particular date and time
 */
public class PriceUtils {
  // As the SystemsManager stores the list of holidays and DateTime can calculate the time and whether the date is a weekend, we can use these to calculate the price
  //  so each method requires SystemsManager, DateTime, and screening as parameters
  // Regarding the different types of Cinemas, we can use an enum instead of multiple classes so we can store different prices for each type of cinema. This gives more flexibility than having a price multiplier
  // we can also have different prices for different genres of movies, for example 3D, blockbuster
  // TODO: Extract to constants file and use a mapping
  public static float getPrice(SystemManager sm, Ticket ticket) throws Exception {
    Screening screening = ticket.getScreening();
    SeatType seatType = ticket.getSeat().getSeatType();
    DateTime date = screening.getShowtime();
    TicketType ticketType = ticket.getTicketType();
    CinemaType cinemaType = screening.getCinema().getCinemaType();

    float cinemaMultiplier = sm.getCinemaMultiplier(cinemaType);
    float seatMultiplier = sm.getSeatMultiplier(seatType);

    DateTime eveOfDate = date.getEveOfDate();
    boolean isHoliday = sm.isHoliday(date) || sm.isHoliday(eveOfDate);
    boolean isBlockbuster = screening.getMovie().getMovieType().equals(MovieType.BLOCKBUSTER);
    boolean is3D = screening.getMovie().getMovieType().equals(MovieType.THREE_D);

    float price = 0;
    int dayOfWeek = date.getDayOfWeek();

    if (ticketType.equals(TicketType.CARDS)) { //Preferred Credit & Loyalty Cards
      if (is3D) {
        throw new Exception("Cards tickets not valid for 3D movies");
      }
      else {
        price = 7;
      }
    }
    else if (isHoliday || dayOfWeek == 6 || dayOfWeek == 7) { //Weekends and PH/Eve of PH
      price = is3D ? 15 : 11;
    }
    else if (ticketType.equals(TicketType.SENIOR)) { //Senior Citizens
      if (is3D) {
        throw new Exception("Senior Citizen ticket not valid for 3D movies");
      }
      else if (date.getHour() >= 18) {
        throw new Exception("Senior Citizen ticket only valid for movies before 6pm");
      }
      else {
        price = 4;
      }
    }
    else if (ticketType.equals(TicketType.STUDENT)) { //Students
      if (date.getHour() >= 18) {
        throw new Exception("Student ticket only valid for movies before 6pm");
      }
      else {
        price = is3D ? 9 : 7;
      }
    }
    else if (dayOfWeek == 5) { //Friday
      if (date.getHour() >= 18) {
        price = is3D ? 15 : 11;
      }
      else {
        price = is3D ? 15 : (float) 9.50;
      }
    }
    else if (dayOfWeek == 4) { //Thursday
      price = is3D ? 11 : (float) 9.5;
    }
    else { //Monday - Wednesday
      price = is3D ? 11 : (float) 8.5;
    }

    price = isBlockbuster ? price + 1 : price;

    return price * cinemaMultiplier * seatMultiplier;
  }
}
