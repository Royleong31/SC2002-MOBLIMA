package model;

import java.util.ArrayList;

import model.Cinema.Cinema;

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
  private Date date;
  private boolean isPH; // TODO: Do we need this? i think we can check the system config if it's a special date
  private ArrayList<Seat> seats = new ArrayList<Seat>();

  public Screening(Movie movie, Cinema cinema, Date date, boolean isPH, float price) {
    this.movie = movie;
    this.cinema = cinema;
    this.date = date;
    this.isPH = isPH;
    this.price = price;
  }

  public void addSeat(Seat seat) {
    seats.add(seat);
  }

  public float getPrice() {
    return 0;
  }

  public boolean checkIfSeatIsAvailable() {
    return false;
  }

  public void updateSeat(ArrayList<Seat> seats) {}
}
