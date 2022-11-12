package controller;

import java.util.ArrayList;

import enums.TicketType;

import model.Booking;
import model.Screening;
import model.Seat;
import model.Ticket;
import model.Account.MovieGoerAccount;
import utils.DateTimeUtils;

/**
 * Handles the booking of tickets for a movie screening.
 *
 @author Roy Leong, Ryan Ng
 @version 1.1
 @since 2022-10-30
*/
public class BookingManager {

  /**
   * The ArrayList containing all existing bookings that have been made.
   */
  private ArrayList<Booking> bookingsArr = new ArrayList<Booking>();
  
  /**
   * Creates a booking from the user's choice of screening and seats.
   * Multiple tickets of the same type and screening can be created in a single booking.
   * Selected seats will be checked for one-seat gap and if seats is/are taken, if found booking will not be made successfully
   * @param movieGoer the moviegoer account making the booking
   * @param screening the screening that the moviegoer wishes to make the booking of
   * @param seatsArr the seats the moviegoer wishes to book
   * @param ticketType the type of ticket the moviegoer wishes to book
   * @param systemManager the systemmanager
   */
  public void makeBooking(MovieGoerAccount movieGoer, Screening screening, ArrayList<Seat> seatsArr, TicketType ticketType, SystemManager systemManager) throws Exception {
    if (seatsArr.size() == 0) {
      throw new Exception("No seats selected.");
    }

    ArrayList<Ticket> ticketsArr = new ArrayList<Ticket>();

    if(seatsArr.size() > 1) {
      // Assuming the seats in seatsArr are not in order of seat number, need to cycle through every seat and check against other seats
      for (int i=0; i<seatsArr.size(); i++) {
        for (int j=1; j<seatsArr.size(); j++) {
          // If seats are in the same row, check for one-seat gap. No concern if they are different rows.
          if (seatsArr.get(i).getRow() != seatsArr.get(j).getRow()) {
            continue;
          }

          if (seatsArr.get(i).getColumn()-seatsArr.get(j).getColumn()==2) {
            boolean checker = true;

            for (Seat checkSeat: seatsArr){
              if (checkSeat.getColumn() == (seatsArr.get(j).getColumn()+1)) 
               checker = false;
            }

            // If checker is still true, means between seats i and j of this booking exists a one-seat gap.
            if (checker)
              throw new Exception("Error: please do not leave a one-seat gap.");
          }

          else if (seatsArr.get(j).getColumn()-seatsArr.get(i).getColumn()==2) {
            boolean checker = true;

            for (Seat checkSeat: seatsArr) {
              if (checkSeat.getColumn()==(seatsArr.get(i).getColumn()+1)) 
                checker = false;
              }

            // If checker is still true, means between seats i and j of this booking exists a one-seat gap.
              if (checker)
                throw new Exception("Error: please do not leave a one-seat gap.");
          }
        }
      }
    }

    for (Seat seat: seatsArr) {
      if (seat.isTaken()) {
        throw new Exception("Error: seat is already taken.");
      }

      ticketsArr.add(new Ticket(seat, screening, ticketType));
    }

    int amountPaid = 0;
    for (Ticket chosenTicket: ticketsArr){
      amountPaid += utils.PriceUtils.getPrice(systemManager, chosenTicket);
    }
    
    for (Seat seat: seatsArr) {
      seat.setTaken(true);
    }


    this.bookingsArr.add(new Booking(screening.getCinemaId() + DateTimeUtils.getDateTime(), movieGoer, amountPaid, ticketsArr));
  }

  /**
   * Returns all available seats for a given screening.
   * Used to get the seats that the user can choose from, which is then shown to the user in the console.
   * @param screening the specific screening to check for available seats
   * @return availableSeats the ArrayList of available seats
   */
  public ArrayList<Seat> getAvailableSeats(Screening screening) {
    ArrayList<Seat> allSeats = screening.getSeats();
    ArrayList<Seat> availableSeats = new ArrayList<Seat>();

    for (Seat seat : allSeats) {
      if (!seat.isTaken()) {
        availableSeats.add(seat);
      }
    }

    return availableSeats;
  }

  /**
   * Returns all bookings that have been made
   * @return bookingsArr the ArrayList of bookings that have been made
   */
  public ArrayList<Booking> getBookings() {
    return this.bookingsArr;
  }

  /**
   * Returns all bookings that have been made specifically by a movie goer
   * @param movieGoer the moviegoer account that wants to get the bookings it has made
   * @return checkArr the ArrayList of bookings made by the moviegoer
   */
  public ArrayList<Booking> getBookingsByUser(MovieGoerAccount movieGoer){
    ArrayList<Booking> checkArr = new ArrayList<Booking>();
	
    for(Booking findBooking: bookingsArr){
      if(findBooking.getMovieGoer().equals(movieGoer))
        checkArr.add(findBooking);
    }
    return checkArr;
  }

  /**
   * Get the booking based on a given booking id
   * @param bookingId the id of the wanted booking
   * @return the booking that matches this booking id
   */
  public Booking getBookingById(String bookingId){
    for (Booking findBooking: bookingsArr) {
      if (findBooking.getId().equals(bookingId))
      return findBooking;
    }

    return null;
  }

  /**
   * Cascade deletes bookings specific to a screening
   * @param screening is the target screening of which bookings belong to a screening
   */
  public void deleteBooking(Screening screening) {
    this.bookingsArr.removeIf(
      findBooking -> {
        ArrayList<Ticket> ticketsArr = new ArrayList<Ticket>();

        for (Ticket ticket: findBooking.getTickets()) {
          if (ticket.getScreening().equals(screening)) {
            ticketsArr.add(ticket);
          }
        }

        // Remove if it has tickets for the screening
        return ticketsArr.size() != 0;
      }
      );
  }
}
