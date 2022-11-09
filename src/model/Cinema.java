package model;

import enums.CinemaType;
import utils.IdUtils;
import java.io.Serializable;

/**
 * Contains the seating plan and which cineplex this cinema belongs to.
 * contains a price modifier which is used to calculate the price of the movie, can be overriden by child classes
 * @author Roy Leong, Augustine Lee
 * @version 1.1
 * @since 2022-10-30
 */
public class Cinema implements Serializable{
  private final String id;
  private static int cinemaCounter;
  private SeatingPlan seatingPlan;
  private Cineplex cineplex;
  private CinemaType cinemaType;

  public Cinema(SeatingPlan seatingPlan, Cineplex cineplex, CinemaType cinemaType) {
    this.id = IdUtils.generateCinemaID(cinemaCounter);
    this.seatingPlan = seatingPlan;
    this.cineplex = cineplex;
    this.cinemaType = cinemaType;
    Cinema.cinemaCounter++; /* increment cinemaCounter by 1 to denote an increase in cinemas */
  }

  public String getId() {
    return id;
  }

  public CinemaType getCinemaType() {
    return cinemaType;
  }

  public Cineplex getCineplex() {
    return cineplex;
  }

  /**
   *
   * @return seatingPlan
   */
  public SeatingPlan getSeatingPlan() {
    return seatingPlan;
  }
}
