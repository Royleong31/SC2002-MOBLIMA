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
  private ArrayList<Cinema> cineplex;
  private ArrayList<Movie> movieCollection;
  private String location;

  public ArrayList<Cinema> getCineplex() {
    return cineplex;
  }

  public ArrayList<Movie> getMovies() {
    return movieCollection;
  }

  public void addMovie(Movie movie) {
  }

  public void addCinema(Cinema cinema) {
    
  }

  public void deleteCinema(Cineplex cineplex, Cinema cinema) {

  }


  public void deleteMovie(Movie movie) {

  }
  
  public String getLocation() {
    return location;
  }

  public void setLocation() {

  }
}
