package model;
import model.Seat;
import model.Screening;
import enums.TicketType;

/**
 * Ticket issued during booking
 * Contains screening and seat details
 *
 @author Roy Leong, Ryan Ng
 @version 1.1
 @since 2022-11-02
*/
public class Ticket {
  /**
   * The seat that the ticket is associated with.
   * There will be no mutator for this.
   */	
  private Seat seat;
  
  /**
   * The screening that the ticket is tied to.
   * There will be no mutator for this.
   */
  private Screening screening;
  
  /**
   * The type of ticket.
   * There will be no mutator for this.
   */  
  private TicketType ticketType;

  /**
   * Constructor for the Ticket class
   * @param seat This ticket's seat.
   * @param screening This ticket's screening.
   * @param ticketType This ticket's type.
   */
  public Ticket(Seat seat, Screening screening, enums.TicketType ticketType) {
    this.seat = seat;
    this.screening = screening;
    this.ticketType = ticketType;
  }

  /**
   * Gets the seat that the ticket is associated with.
   * @return Seat The seat that the ticket is associated with.
   */
  public Seat getSeat() {
    return seat;
  }

  /**
   * Gets the screening that the ticket is tied to.
   * @return Screening The screening that the ticket is tied to.
   */
  public Screening getScreening() {
    return screening;
  }

  /**
   * Gets the type of the ticket.
   * @return TicketType The type of the ticket.
   */
  public TicketType getTicketType() {
    return ticketType;
  }
}
