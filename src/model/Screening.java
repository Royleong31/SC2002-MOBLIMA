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
  private DateTime dateTime;
  private ArrayList<Seat> seats = new ArrayList<Seat>();

  public Screening(Movie movie, Cinema cinema, DateTime dateTime, float price) {
    this.movie = movie;
    this.cinema = cinema;
    this.dateTime = dateTime;
  }

  public void addSeat(Seat seat) {
    seats.add(seat);
  }

  public boolean checkIfSeatIsAvailable(Seat targetSeat) {
    for (Seat seat : this.seats) {
      if (seat.equals(targetSeat)) {
        return false;
      }
    }
    return true;
  }

  public void updateSeat(Seat seat) {
    seat.setTaken();
    addSeat(seat);
  }

  public String getMovieTitle() {
    return this.movie.getTitle();
  }

  public String getCinemaId() {
    return this.cinema.getId();
  }

  public String getDateTime() {
    return this.dateTime.getDate();
  }

  public Cinema getCinema() {
    return this.cinema;
  }

  public void setDateTime(DateTime dateTime) {
    this.dateTime = dateTime;
  }
}
