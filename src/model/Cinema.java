package model;

import enums.CinemaType;
import utils.IdUtils;

/**
 * Cinema class
 * Contains the id, seating plan, cineplex and cinema type
 * @author Roy Leong, Augustine Lee
 * @version 1.1
 * @since 2022-10-30
 */
public class Cinema {
  /**
   * id of the cinema
   */
  private final String id;

  /**
   * static counter to be passed into static generateCinemaID function to generate id for each cinema
   */
  private static int cinemaCounter;

  /**
   * seating plan for the cinema
   */
  private SeatingPlan seatingPlan;

  /**
   * cineplex that the cinema belongs to
   */
  private Cineplex cineplex;

  /**
   * type of cinema (enum)
   */
  private CinemaType cinemaType;

  /**
   * Constructor for Cinema class
   * @param seatingPlan
   * @param cineplex
   * @param cinemaType
   */
  public Cinema(SeatingPlan seatingPlan, Cineplex cineplex, CinemaType cinemaType) {
    this.id = IdUtils.generateCinemaID(cinemaCounter); /* generate ID for the cinema */
    this.seatingPlan = seatingPlan;
    this.cineplex = cineplex;
    this.cinemaType = cinemaType;
    Cinema.cinemaCounter++; /* increment cinemaCounter by 1 to denote an increase in cinemas */
  }

  /**
   * Return the ID of the cinema
   * @return
   */
  public String getId() {
    return id;
  }

  /**
   * Return the cinema type of the cinema
   * @return
   */
  public CinemaType getCinemaType() {
    return cinemaType;
  }

  /**
   * Return the corresponding cineplex of the cinema
   * @return
   */
  public Cineplex getCineplex() {
    return cineplex;
  }

  /**
   * Return the seating plan of the cinema
   * @return seatingPlan
   */
  public SeatingPlan getSeatingPlan() {
    return seatingPlan;
  }
}
