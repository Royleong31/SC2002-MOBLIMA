package model.Cinema;

import model.Cineplex;
import model.SeatingPlan;

/**
 * Contains the seating plan and which cineplex this cinema belongs to
 * Overrides the price modifier to be 1.5
 * @author Roy Leong
 * @version 1.0
 * @since 2022-10-30
 */
public class PremiumCinema extends Cinema {
  public PremiumCinema(SeatingPlan seatingPlan, Cineplex cineplex) {
    super(seatingPlan, cineplex);
  }

  /**
   * @return priceModifier, defaults to 1.8
   */
  @Override
  public Number getPriceModifier() {
    return 1.8;
  }
}
