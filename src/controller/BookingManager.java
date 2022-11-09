package controller;

import java.util.ArrayList;

import enums.TicketType;

import java.lang.Math;

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
  private ArrayList<Booking> bookingsArr = new ArrayList<Booking>();
  
  /**
   * Creates a booking from the user's choice of screening and seats.
   * Multiple tickets of the same type and screening can be created in a single booking.
   * @param movieGoer
   * @param screening
   * @param seatsArr
   * @param ticketType
   */
  public void makeBooking(MovieGoerAccount movieGoer, Screening screening, ArrayList<Seat> seatsArr, TicketType ticketType, SystemManager systemManager) throws Exception {
    ArrayList<Ticket> ticketsArr = new ArrayList<Ticket>();
    // TODO: Figure out a way to validate no single unoccupied seat is left between two occupied seats

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
   * @param movieGoer
   * @return
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
   * @return
   */
  public ArrayList<Booking> getBookings() {
    return this.bookingsArr;
  }

  /**
   * Returns all bookings that have been made by a movie goer
   * @param movieGoer
   * @return
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
   * 
   * @param bookingId
   * @return the booking that matches this booking id
   */
  public Booking getBookingById(String bookingId){
    for (Booking findBooking: bookingsArr) {
      if (findBooking.getId().equals(bookingId))
      return findBooking;
    }

    return null;
  }

  // cascade delete
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
