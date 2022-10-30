package model.Cinema;

import model.Cineplex;
import model.SeatingPlan;

/**
 * Contains the seating plan and which cineplex this cinema belongs to.
 * contains a price modifier which is used to calculate the price of the movie, can be overriden by child classes
 * @author Roy Leong
 * @version 1.0
 * @since 2022-10-30
 */
public class Cinema {
  private SeatingPlan seatingPlan;
  private int numberOfSeats;
  private Cineplex cineplex;

  public Cinema(SeatingPlan seatingPlan, int numberOfSeats, Cineplex cineplex) {
    this.seatingPlan = seatingPlan;
    this.numberOfSeats = numberOfSeats;
    this.cineplex = cineplex;
  }

  /**
   * @return priceModifier, defaults to 1
   */
  public Number getPriceModifier() {
    return 1;
  }

  /**
   * 
   * @return seatingPlan
   */
  public SeatingPlan getSeatingPlan() {
    return seatingPlan;
  }


}
