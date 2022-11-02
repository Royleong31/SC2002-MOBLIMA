package model.Cinema;

import enums.CinemaType;
import model.Cineplex;
import model.SeatingPlan;
import utils.IdUtils;
import utils.CinemaPremium;

/**
 * Contains the seating plan and which cineplex this cinema belongs to.
 * contains a price modifier which is used to calculate the price of the movie, can be overriden by child classes
 * @author Roy Leong, Augustine Lee
 * @version 1.1
 * @since 2022-10-30
 */
public class Cinema {
  private final String id;
  private static IdUtils idGenerator = new IdUtils(0);
  private SeatingPlan seatingPlan;
  private Cineplex cineplex;
  private CinemaType cinemaType;
  private CinemaPremium premiumGenerator = new CinemaPremium();

  public Cinema(SeatingPlan seatingPlan, Cineplex cineplex) {
    this.id = idGenerator.generateCinemaID();
    this.seatingPlan = seatingPlan;
    this.cineplex = cineplex;
  }


  public String getId() {
    return id;
  }
  
  /**
   * @return priceModifier, defaults to 1
   */
  public Number getPriceModifier() {
    return premiumGenerator.getPremium(cinemaType);
  }

  /**
   *
   * @return seatingPlan
   */
  public SeatingPlan getSeatingPlan() {
    return seatingPlan;
  }


}
