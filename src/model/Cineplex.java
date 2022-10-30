package model;

import java.util.ArrayList;

import model.Cinema.Cinema;

/**
 * Account for a staff member.
 * Contains the staff id
 *
 @author Roy Leong
 @version 1.0
 @since 2022-10-30
*/
public class Cineplex {
  /**
   * id of this cineplex
  */
  private final String id;

  /**
   * All the cinemas in this cineplex
   */
  private ArrayList<Cinema> cinemasArr;

  /**
   * All the movies showing in this cineplex
   */
  private ArrayList<Movie> movieCollection;

  /**
   * Location of this cineplex
   */
  private String location;

  /**
   * Constructor for Cineplex
   * @param id
   * @param location
   */
  Cineplex(String location) {
    // TODO: Figure out a way to generate a unique id for each cineplex (maybe maintain a static counter so it can be CP1, CP2 etc)
    // this.id = 
    this.location = location;
    cinemasArr = new ArrayList<Cinema>();
    movieCollection = new ArrayList<Movie>();
  }

  /**
   * @return cineplex id
   */
  public String getId() {
    return id;
  }

  /*
   * returns all the cinemas in this cineplex
   */
  public ArrayList<Cinema> getCinemas() {
    return cinemasArr;
  }

  /**
   * @return all the movies shown in this cinema
   */
  public ArrayList<Movie> getMovies() {
    return movieCollection;
  }

  /**
   * @param movie
   */
  public void addMovie(Movie movie) {
    // add in a movie to the moviesArr
    // check that a movie of the same name doesn't alr exist
  }

  /**
   * Add a new cinema to the cineplex
   * @param cinema
   */
  public void addCinema(Cinema cinema) {
    
  }

  /**
   * Remove a cinema from this cineplex
   * @param cineplex
   * @param cinema
   */
  public void deleteCinema(Cineplex cineplex, Cinema cinema) {

  }

  /**
   * Delete a movie from this cineplex
   * @param movie
   */
  public void deleteMovie(Movie movie) {

  }
  
  /**
   * Get the location of this cineplex
   * @return
   */
  public String getLocation() {
    return location;
  }

  /**
   * Set the location of this cineplex
   */
  public void setLocation() {

  }
}
