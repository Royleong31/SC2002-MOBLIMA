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
  private String transactionId;
  private MovieGoerAccount movieGoer;
  private float amountPaid;
  private ArrayList<Ticket> ticketsArr;

  public Booking(String transactionId, MovieGoerAccount movieGoer, float amountPaid, ArrayList<Ticket> ticketsArr) {
    this.transactionId = transactionId;
    this.movieGoer = movieGoer;
    this.amountPaid = amountPaid;
    this.ticketsArr = ticketsArr;
  }

  public ArrayList<Ticket> getTicketsArr() {
    return ticketsArr;
  }
}
