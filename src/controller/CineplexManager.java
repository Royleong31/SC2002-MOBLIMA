package controller;

import java.util.ArrayList;

import enums.CinemaType;
import model.Cinema;
import model.Cineplex;
import model.SeatingPlan;

/**
 * Cineplex Manager
 * Responsible for handling all addition/deletion/information retrieval of cineplexes and its constituent cinemas
 *
 @author Roy Leong, Augustine Lee
 @version 1.1
 @since 2022-10-30
*/
public class CineplexManager {
  /**
   * Collection of all cineplexes the company owns
   */
  private ArrayList<Cineplex> cineplexesArr = new ArrayList<Cineplex>();
  
  /**
   * Collection of all cinema halls the company owns
   */
  private ArrayList<Cinema> cinemasArr = new ArrayList<Cinema>();

  /**
   * Add new cineplex
   * @param location
   * @throws Exception if there already exists a cineplex in the same location
   */
  public void addCineplex(String location) throws Exception {
    for (Cineplex cur: this.cineplexesArr) {
      if (cur.getLocation().equals(location)) {
        // throw exception
        throw new Exception("Cineplex already exists.");
      }
    }

    this.cineplexesArr.add(new Cineplex(location));
  }

  /**
   * Add new cinema hall in a particular cineplex
   * @param cineplex
   * @param rows
   * @param columns
   * @param aisle
   * @param cinemaType
   * @return new cinema object
   * @throws Exception if cinema already exists
   */
  public Cinema addCinema(Cineplex cineplex, int rows, int columns, int aisle, CinemaType cinemaType) throws Exception {
    SeatingPlan seatingPlan = new SeatingPlan(rows, columns, aisle);
    Cinema cinema = new Cinema(seatingPlan, cineplex, cinemaType);
    this.cinemasArr.add(cinema);
    cineplex.addCinema(cinema);
    return cinema;
  }

  /**
   * Get a particular cinema object from its ID
   * @param cinemaId
   * @return cinema object
   * @throws Exception if ID does not link to any cinema does not exist
   */
  public Cinema getCinemaById(String cinemaId) throws Exception {
    for (Cinema cur: this.cinemasArr) {
      if (cur.getId().equals(cinemaId)) {
        return cur;
      }
    }
    
    throw new Exception("A cinema with the specified ID does not exist.");
  }

  /**
   * Get cineplex based on its location
   * @param cineplexLocation
   * @return cineplex object
   * @throws Exception if there is no cineplex at that location
   */
  public Cineplex getCineplexByLocation(String cineplexLocation) throws Exception {
    for (Cineplex cur: this.cineplexesArr) {
      if (cur.getLocation().equals(cineplexLocation)) {
        return cur;
      }
    }
    
    throw new Exception("A cineplex with the specified location does not exist.");
  }

  /**
   * Delete a particular cinema hall
   * @param cineplex
   * @param cinema
   * @throws Exception if that cinema does not exist in that cineplex
   */
  public void deleteCinema(Cineplex cineplex, Cinema cinema) throws Exception {
    cineplex.deleteCinema(cinema);
  }

  /**
   * Get an arraylist of all cineplexes
   * @return
   */
  public ArrayList<Cineplex> getCineplexes() {
    return this.cineplexesArr;
  }

  /**
   * Get all cinema halls of a cineplex
   * @param cineplex
   * @return
   * @throws Exception if the cineplex does not exist
   */
  public ArrayList<Cinema> getCinemas(Cineplex cineplex) throws Exception {
    /* Check if cineplex exists */
    for (Cineplex cur: this.cineplexesArr) {
      if (cineplex.equals(cur)) {
        /* Cineplex found in cineplex array */
        return cineplex.getCinemas();
      }
    }
    throw new Exception("Cineplex does not exists");
  }
}