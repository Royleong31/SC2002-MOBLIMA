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
   * movieGoer who made the booking
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

  public Booking(String id, MovieGoerAccount movieGoer, float amountPaid, ArrayList<Ticket> ticketsArr) {
    this.id = id;
    this.movieGoer = movieGoer;
    this.amountPaid = amountPaid;
    this.ticketsArr = ticketsArr;
  }

  /**
   * 
   * @return booking id
   */
  public String getId() {
    return id;
  }
  
  /**
   * 
   * @return movie goer who made the booking
   */
  public MovieGoerAccount getMovieGoer() {
    return movieGoer;
  }

  /**
   * 
   * @return all the tickets booked in this booking
   */
  public ArrayList<Ticket> getTickets() {
    return ticketsArr;
  }

  /**
   * 
   * @return the amount that the movie goer paid for this booking
   */
  public float getAmountPaid() {
    return amountPaid;
  }

  public String getCinemaId() {
    return ticketsArr.get(0).getScreening().getCinemaId();
  }

}