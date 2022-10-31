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

  public void addMovie(Movie movie) {
    return false;
  }

  public void deleteMovie(Movie movie){
    return false;
  }

  public void updateMovie(Movie movie){
    return false;
  }

  public void updateMovieShowingStatus(ShowStatus showStatus) {
    
  }

  public ArrayList<Movie> getMovies(SortCriteria sortCriteria){
    return moviesArr;
  }

  // TODO: Create more overloaded functions for getMovies to allow optional params
}