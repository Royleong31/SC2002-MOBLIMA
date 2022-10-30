package model;

enum TicketType {
  NORMAL, STUDENT, SENIOR, CHILD
}

/**
 * Account for a staff member.
 * Contains the staff id
 *
 @author Roy Leong
 @version 1.0
 @since 2022-10-30
*/
public class Ticket {
  private Seat seat;
  private Screening screening;
  private TicketType ticketType;

  public Ticket(Seat seat, Screening screening, TicketType ticketType) {
    this.seat = seat;
    this.screening = screening;
    this.ticketType = ticketType;
  }

  public Seat getSeat() {
    return seat;
  }

  public Screening getScreening() {
    return screening;
  }

  public TicketType getTicketType() {
    return ticketType;
  }
}
