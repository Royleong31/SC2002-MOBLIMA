package controller;

import java.util.ArrayList;

import model.Movie;

enum SortCriteria {
  TITLE, RATING, SALES
}

/**
 * Account for a staff member.
 * Contains the staff id
 *
 @author Roy Leong
 @version 1.0
 @since 2022-10-30
*/
public class MovieManager {
  private ArrayList<Movie> moviesArr;

  public boolean addMovie(Movie movie) {
    return false;
  }

  public boolean removeMovie(Movie movie){
    return false;
  }

  public boolean updateMovie(Movie movie){
    return false;
  }

  public ArrayList<Movie> sortMovies(SortCriteria sortCriteria){
    return moviesArr;
  }
  
}