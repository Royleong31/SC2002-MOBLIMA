package model;

import java.util.ArrayList;

/**
 * Account for a staff member.
 * Contains the staff id
 *
 @author Roy Leong
 @version 1.0
 @since 2022-10-30
*/
public class Screening {
  private Movie movie;
  private Cinema cinema;
  private DateTime dateTime;
 

  private boolean isPH; // TODO: Do we need this? i think we can check the system config if it's a special date
  private ArrayList<Seat> seats = new ArrayList<Seat>();

  public Screening(Movie movie, Cinema cinema, DateTime dateTime, boolean isPH, float price) {
    this.movie = movie;
    this.cinema = cinema;
    this.dateTime = dateTime;
    this.isPH = isPH;
  }

  public void addSeat(Seat seat) {
    seats.add(seat);
  }

  public boolean checkIfSeatIsAvailable() {
    return false;
  }

  public void updateSeat(ArrayList<Seat> seats) {}

   public DateTime getDateTime() {
    return dateTime;
  }

  public void setDateTime(DateTime dateTime) {
    this.dateTime = dateTime;
  }
}
