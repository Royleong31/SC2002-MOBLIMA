package controller;

import java.util.ArrayList;
import java.io.Serializable;

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
public class CineplexManager implements Serializable{
  /**
   * Collection of all cineplexes the company owns
   */
  private ArrayList<Cineplex> cineplexesArr = new ArrayList<Cineplex>();
  
  /**
   * Collection of all cinema halls the company owns
   */
  private ArrayList<Cinema> cinemasArr = new ArrayList<Cinema>();

  /**
   * Number of cinemas that have been created so far, used to create the cinema ids for each cinema
   */
  private int cinemaCounter = 0;

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
   * @param cineplex the cineplex where the new cinema is located at
   * @param rows the number of rows in the cinema
   * @param columns the number of columns in the cinema
   * @param aisle the number of aisles in the cinema
   * @param cinemaType the type of cinema
   * @return new cinema object
   * @throws Exception if cinema already exists
   */
  public Cinema addCinema(Cineplex cineplex, int rows, int columns, int aisle, CinemaType cinemaType) throws Exception {
    SeatingPlan seatingPlan = new SeatingPlan(rows, columns, aisle);
    this.cinemaCounter++;
    Cinema cinema = new Cinema(seatingPlan, cineplex, cinemaType, this.cinemaCounter);
    this.cinemasArr.add(cinema);
    cineplex.addCinema(cinema);
    return cinema;
  }

  /**
   * Get a particular cinema object from its ID
   * @param cinemaId the ID of the cinema
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
   * @param cineplexLocation the location of the cineplex
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
   * @param cineplex the cineplex that the cinema is located at
   * @param cinema the cinema object
   * @throws Exception if that cinema does not exist in that cineplex
   */
  public void deleteCinema(Cineplex cineplex, Cinema cinema) throws Exception {
    cineplex.deleteCinema(cinema);
  }

  /**
   * Get an arraylist of all cineplexes
   * @return arraylist of cineplexes
   */
  public ArrayList<Cineplex> getCineplexes() {
    return this.cineplexesArr;
  }

  /**
   * Sets arraylist of all cineplexes
   * @param cineplexesArr arraylist of cineplexes
   */
  public void setCineplexes(ArrayList<Cineplex> cineplexesArr) {
    this.cineplexesArr = new ArrayList<Cineplex>(cineplexesArr);
  }
  
  /**
   * Set the last cinema count so the cinema id is unique
   * @param lastCinemaCounter
   */
  public void setCinemaCounter(int lastCinemaCounter) {
    this.cinemaCounter = lastCinemaCounter;
  }

  /**
   * Get all cinema halls of a cineplex
   * @param cineplex the selected cineplex
   * @return arraylist of cinemas in the cineplex
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

  /**
   * 
   * @return cinemasArr
   */
  public ArrayList<Cinema> getCinemasList(){
    return new ArrayList<Cinema>(cinemasArr);
  }

  /**
   * 
   * @param cinemasArr
   */
  public void setCinemas(ArrayList<Cinema> cinemasArr) {
    this.cinemasArr = cinemasArr;
  }

  /**
   * 
   * @return cinemaCounter
   */
  public int getCinemaCounter() {
    return cinemaCounter;
  }
}