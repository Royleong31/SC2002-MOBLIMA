package model;

import java.util.ArrayList;

/**
 * Cineplex class
 * Contains its location, constituent cinemas and movies
 *
 @author Roy Leong, Augustine Lee
 @version 1.1
 @since 2022-10-30
*/
public class Cineplex {

  /**
   * All the cinemas in this cineplex
   */
  private ArrayList<Cinema> cinemasArr = new ArrayList<Cinema>();

  /**
   * All the movies showing in this cineplex
   */
  private ArrayList<Movie> movieCollection = new ArrayList<Movie>();

  /**
   * Location of this cineplex
   */
  private String location;

  /**
   * Constructor for Cineplex
   * @param location location of the cineplex
   */
  public Cineplex(String location) {
    this.location = location;
  }
  
  /**
   * Return all the cinemas in this cineplex
   * @return arraylist of cinemas in the cineplex
   */
  public ArrayList<Cinema> getCinemas() {
    return this.cinemasArr;
  }

  /**
   * Return all the cinemas in this cineplex
   * @return arraylist of movies the cineplex is showing
   */
  public ArrayList<Movie> getAllMovies() {
    return this.movieCollection;
  }

  /**
   * Add a new movie to the cineplex's movie collection
   * @param movie the new movie to be added
   * @throws Exception if movie already exists in cineplex's movie collection
   */
  public void addMovie(Movie movie) throws Exception {
    // add in a movie to the moviesArr
    // check that a movie of the same name doesn't alr exist
    for (Movie cur: this.movieCollection) {
      if (cur.getTitle().equals(movie.getTitle())) {
        throw new Exception("Movie already exists in cineplex's movie collection.");
      }
    }

    this.movieCollection.add(movie);
  }

  /**
   * Add a new cinema to the cineplex
   * @param cinema
   * @throws Exception if cinema already exists in cineplex
   */
  public void addCinema(Cinema cinema) throws Exception {
    // throw exception if cinema already exist
    for (Cinema cur: this.cinemasArr) {
      if (cur.getId().equals(cinema.getId()))
        throw new Exception("Cinema already exist in cineplex.");
    }

    this.cinemasArr.add(cinema);
  }

  /**
   * Remove a cinema from this cineplex
   * @param cineplex
   * @param cinema
   * @throws Exception if cinema does not exist in cineplex
   */

  public void deleteCinema(Cinema cinema) throws Exception {
    // throw exception if cinema do not exist
    if (!cinemasArr.contains(cinema)) {
      throw new Exception("Cinema do not exist in cineplex.");
    }
    
    this.cinemasArr.removeIf(value -> cinema.equals(value));
  }

  /**
   * Delete a movie from this cineplex
   * @param movie
   * @throws Exception if movie does not exist in cineplex's movie collection
   */
  public void removeMovie(Movie movie) throws Exception {
    // throw exception if movie do not exist
     if (!movieCollection.contains(movie)) {
      throw new Exception("Movie do not exist in cineplex's movie collection.");
    }
    
    this.movieCollection.removeIf(value -> movie.equals(value));
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
   * @param location
   */
  public void setLocation(String location) {
    this.location = location;
  }
}
