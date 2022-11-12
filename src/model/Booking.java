package model;

import java.util.ArrayList;
import java.io.Serializable;

import model.Account.MovieGoerAccount;

/**
 * Booking information for each movie goer.
 * Contains overall information with regards to booking. 
 *
 @author Roy Leong, Ryan Ng
 @version 1.1
 @since 2022-11-02
*/
public class Booking  implements Serializable{
  /**
   * Booking ID following this format : <cinemaCode>YYYYMMDDhhmm
   */
  private final String id;

  /**
   * movieGoer account that made the booking
   */
  private final MovieGoerAccount movieGoer;

  /**
   * Ammount paid for all the tickets in this booking
   */
  private final float amountPaid;

  /**
   * All tickets booked in this booking
   */
  private final ArrayList<Ticket> ticketsArr;
 
  /**
   * Constructor for the Booking class, used to create new bookings.
   * @param id is the Booking ID
   * @param movieGoer is the movie goer account making the booking
   * @param amountPaid is the amount paid overall for the booking.
   * @param ticketsArr is the ArrayList of tickets under this booking
   */

  public Booking(String id, MovieGoerAccount movieGoer, float amountPaid, ArrayList<Ticket> ticketsArr) {
    this.id = id;
    this.movieGoer = movieGoer;
    this.amountPaid = amountPaid;
    this.ticketsArr = ticketsArr;
  }

  /**
   * Gets the Booking ID
   * @return id of the booking
   */
  public String getId() {
    return id;
  }
  
  /**
   * Gets the movie goer account that made a specific booking
   * @return movieGoer that made the booking
   */
  public MovieGoerAccount getMovieGoer() {
    return movieGoer;
  }

  /**
   * Gets the ArrayList of tickets under this booking
   * @return ticketsArr is the tickets under this booking
   */
  public ArrayList<Ticket> getTickets() {
    return ticketsArr;
  }

  /**
   * Gets amount paid for this booking
   * @return amountPaid by the movie goer for this booking
   */
  public float getAmountPaid() {
    return amountPaid;
  }
 
 /**
  * Gets the ID of the cinema for the screening linked to the tickets in the booking.
  * @return ticketsArr.get(0).getScreening().getCinemaId() is the ID of the cinema for the screening linked to the tickets in the booking.
  */
  public String getCinemaId() {
    return ticketsArr.get(0).getScreening().getCinemaId();
  }

}
