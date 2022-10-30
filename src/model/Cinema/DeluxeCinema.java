package model.Cinema;

import model.SeatingPlan;

/**
 * Contains the seating plan and which cineplex this cinema belongs to.
 * Overrides the price modifier to be 1.5
 * @author Roy Leong
 * @version 1.0
 * @since 2022-10-30
 */
public class DeluxeCinema extends Cinema {
  public DeluxeCinema(SeatingPlan seatingPlan) {
    super(seatingPlan);
  }

  /**
   * @return priceModifier, defaults to 1.5
   */
  @Override
  public Number getPriceModifier() {
    return 1.5;
  }
}
