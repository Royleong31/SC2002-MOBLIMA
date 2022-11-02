package model;

import java.util.ArrayList;

import model.Cinema.Cinema;

/**
 * Cineplex class
 * Contains its constituent cinemas and movies
 *
 @author Roy Leong, Augustine Lee
 @version 1.1
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
   * @param location
   */
  public Cineplex(String location) {
    this.location = location;
    cinemasArr = new ArrayList<Cinema>();
    movieCollection = new ArrayList<Movie>();
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

  public void addMovie(Movie movie) throws Exception {
    // add in a movie to the moviesArr
    // check that a movie of the same name doesn't alr exist
    for (int i = 0; i < movieCollection.size(); i++) {
      if (movieCollection.get(i).getTitle() == movie.getTitle()) {
        // throw exception
        throw new Exception("Movie already exists in cineplex's movie collection.");
      }
    }
    movieCollection.add(movie);
  }

  /**
   * Add a new cinema to the cineplex
   * @param cinema
   */
  public void addCinema(Cinema cinema) {
    cinemasArr.add(cinema);
  }

  /**
   * Remove a cinema from this cineplex
   * @param cineplex
   * @param cinema
   */

  public void deleteCinema(Cinema cinema) throws Exception {
    /* Check if cinema object exists */
    for (int i = 0; i < cinemasArr.size(); i++) {
      if (cinema.equals(cinemasArr.get(i))) {
        // cinema object found
        cinemasArr.remove(i);
      }
    }
    // throw exception if cinema do not exist
    throw new Exception("Cinema do not exist in cineplex.");
  }

  /**
   * Delete a movie from this cineplex
   * @param movie
   */
  public void deleteMovie(Movie movie) throws Exception {
    /* Check if cinema object exists */
    for (int i = 0; i < movieCollection.size(); i++) {
      if (movie.equals(movieCollection.get(i))) {
        // movie object found
        movieCollection.remove(i);
      }
    }
    // throw exception if movie do not exist
    throw new Exception("Movie do not exist in cineplex's movie collection.");
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
  public void setLocation(String location) {
    this.location = location;
  }
}
