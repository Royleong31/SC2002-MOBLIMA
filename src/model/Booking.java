package model;

import java.util.ArrayList;

import model.Account.MovieGoerAccount;

/**
 * Account for a staff member.
 * Contains the staff id
 *
 @author Roy Leong
 @version 1.0
 @since 2022-10-30
*/
public class Booking {
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
  public ArrayList<Ticket> getTicketsArr() {
    return ticketsArr;
  }

  /**
   * 
   * @return the amount that the movie goer paid for this booking
   */
  public float getAmountPaid() {
    return amountPaid;
  }

}
