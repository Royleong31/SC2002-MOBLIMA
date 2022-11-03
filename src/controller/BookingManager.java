package controller;

import java.util.ArrayList;
import java.lang.Math;

import model.Booking;
import model.Screening;
import model.Seat;
import model.Ticket;
import model.Account.MovieGoerAccount;
import model.DateTime;
import utils.PriceUtils;
import enums.TicketType;

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
  public void makeBooking(MovieGoerAccount movieGoer, Screening screening, ArrayList<Seat> seatsArr, enums.TicketType ticketType) throws Exception {
		
    ArrayList<Ticket> ticketsArr = new ArrayList<Ticket>();
    for(int i=0;i<seatsArr.size();i++){
			
      if(seatsArr.size()>1 && i<=seatsArr.size()-2){
        if(Math.abs((seatsArr.get(i)).getSeatNumber()-(seatsArr.get(i+1)).getSeatNumber())>1){
          throw new Exception("Error: seats must be together!");
		}
	  }
      ticketsArr.add(new Ticket(seatsArr.get(i), screening, ticketType));
	}

    int amountPaid = 0;
    for(Ticket chosenTicket: ticketsArr){
      amountPaid += utils.PriceUtils.getPrice(chosenTicket);
      //TODO: is this the way to get the prices of the tickets to calculate amount paid?
      //		Since screening's getPrice() does not take into account the ticket type
	}
    bookingsArr.add(new Booking((screening.getCinema()).getId() + DateTime.getDateTime(), movieGoer, amountPaid, ticketsArr));
  }

  /**
   * Returns all available seats for a given screening.
   * Used to get the seats that the user can choose from, which is then shown to the user in the console.
   * @param movieGoer
   * @return
   */
  public ArrayList<Seat> getAvailableSeats(Screening screening) {
    return (screening.getSeats()).removeAll(screening.getTakenSeats());
  }

  /**
   * Returns true if a seat is not taken, false otherwise
   * @param screening
   * @param seat
   * @return whether the seat in a screening is available
   */
  public boolean isSeatAvailable(Screening screening, Seat seat) {
    return screening.checkIfSeatIsAvailable(seat);
  }


  /**
   * Returns all bookings that have been made
   * @return
   */
  public ArrayList<Booking> getBookings() {
    return bookingsArr;
  }

  /**
   * Returns all bookings that have been made by a movie goer
   * @param movieGoer
   * @return
   */
  public ArrayList<Booking> getBookingsByUser(MovieGoerAccount movieGoer){
    ArrayList<Booking> checkArr = new ArrayList<Booking>();
	
    for(Booking findBooking: bookingsArr){
      if(findBooking.getMovieGoer() == movieGoer)
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
    for(Booking findBooking: bookingsArr){
      if(findBooking.getId() == bookingId)
      return findBooking;
	}
  }
}
