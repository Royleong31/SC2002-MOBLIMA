package controller;

import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.Booking;
import model.Movie;
import model.Screening;
import model.Seat;
import model.Ticket;
import model.Account.MovieGoerAccount;
import model.Cinema.*;
import model.SeatingPlan;
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
  
  private ArrayList<Ticket> ticketsArr;
  private String id;
  private int amountPaid;
  private SeatingPlan findPlan;
  private ArrayList<Seat> availableSeats;
  private ArrayList<Booking> checkArr;
  
  /**
   * Creates a booking from the user's choice of screening and seats.
   * Multiple tickets of the same type and screening can be created in a single booking.
   * @param movieGoer
   * @param screening
   * @param seatsArr
   * @param ticketType
   */
  public void makeBooking(MovieGoerAccount movieGoer, Screening screening, ArrayList<Seat> seatsArr, enums.TicketType ticketType) {
		
	ticketsArr = new ArrayList<Ticket>();
	for(Seat chosenSeat: seatsArr){
		ticketsArr.add(new Ticket(chosenSeat, screening, ticketType));
	}
		
	amountPaid = 0;
	for(Ticket chosenTicket: ticketsArr){
		amountPaid += utils.PriceUtils.getPrice(chosenTicket);
		//TODO: is this the way to get the prices of the tickets to calculate amount paid?
		//		Since screening's getPrice() does not take into account the ticket type
	}
		
	bookingsArr.add(new Booking((screening.getCinema()).getId()
								+ new SimpleDateFormat("yyyyMMddhhmm").format(new java.util.Date()), 
								movieGoer, amountPaid, ticketsArr));
	//TODO: Screening Class needs a getCinema() method.
  }

  /**
   * Returns all available seats for a given screening.
   * Used to get the seats that the user can choose from, which is then shown to the user in the console.
   * @param movieGoer
   * @return
   */
  public ArrayList<Seat> getAvailableSeats(Screening screening) {
	findPlan = (screening.getCinema()).getSeatingPlan();
	availableSeats = new ArrayList<Seat>();
	availableSeats = findPlan.getSeats();
	//TODO: SeatingPlan class needs a getSeats() method.
	availableSeats.removeAll(screening.getTakenSeats());
	//TODO: Screening Class needs a getTakenSeats() method.

	//TODO: do i need to do something if no available seats?
	//		Cuz doesn't make sense to end execution via throws just cuz no more seats
	return availableSeats;
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
  public ArrayList<Booking> getBookingsByUser(MovieGoerAccount movieGoer) throws Exception{

	checkArr = new ArrayList<Booking>();
	for(Booking findBooking: bookingsArr){
		if(findBooking.getMovieGoer() == movieGoer)
			checkArr.add(findBooking);
	}
	if(checkArr.isEmpty())
		throw new Exception("Error: no bookings found!");
	
    return checkArr;
  }

  /**
   * 
   * @param bookingId
   * @return the booking that matches this booking id
   */
  public Booking getBookingById(String bookingId) throws Exception {
    for(Booking findBooking: bookingsArr){
		if(findBooking.getId() == bookingId)
			return findBooking;
	}
	throw new Exception("Error: no such booking found!");
  }
}
