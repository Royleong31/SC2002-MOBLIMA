package model;

import enums.CinemaType;
import utils.IdUtils;
import java.io.Serializable;

/**
 * Cinema class
 * Contains the id, seating plan, cineplex and cinema type
 * @author Roy Leong, Augustine Lee
 * @version 1.1
 * @since 2022-10-30
 */

public class Cinema implements Serializable{
  /**
   * id of the cinema
   */
  private final String id;

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
   * @param seatingPlan seating plan of the cinema
   * @param cineplex cineplex that the cinema is located at
   * @param cinemaType the type of cinema
   */
  public Cinema(SeatingPlan seatingPlan, Cineplex cineplex, CinemaType cinemaType, int cinemaCounter) {
    this.id = IdUtils.generateCinemaID(cinemaCounter); /* generate ID for the cinema */
    this.seatingPlan = seatingPlan;
    this.cineplex = cineplex;
    this.cinemaType = cinemaType;
  }

  /**
   * Return the ID of the cinema
   * @return ID of the cinema
   */
  public String getId() {
    return id;
  }

  /**
   * Return the cinema type of the cinema
   * @return Type of the cinema
   */
  public CinemaType getCinemaType() {
    return cinemaType;
  }

  /**
   * Return the corresponding cineplex of the cinema
   * @return the cineplex the cinema is located at
   */
  public Cineplex getCineplex() {
    return cineplex;
  }

  /**
   * Return the seating plan of the cinema
   * @return seatingPlan of the cinema
   */
  public SeatingPlan getSeatingPlan() {
    return seatingPlan;
  }
}
