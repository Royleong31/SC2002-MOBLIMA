package controller;

import java.util.ArrayList;
import java.util.Comparator;

import model.Movie;
import enums.ShowStatus;
import enums.SortCriteria;
import enums.Advisory;
import enums.Genre;

/**
 * Account for a staff member.
 * Contains the staff id
 *
 @author Roy Leong
 @version 1.0
 @since 2022-10-30
*/
public class MovieManager {
  private ArrayList<Movie> moviesArr = new ArrayList<Movie>();

  public MovieManager() {}

  public void addMovie(Movie movie) {
    this.moviesArr.add(movie);
  }

  public void deleteMovie(Movie movie){
    this.moviesArr.remove(movie);
  }

  /*public void updateMovie(Movie old_movie, Movie updated_movie){
    // Can be changed to modifying the movie object instead of method below
    this.moviesArr.remove(old_movie);
    this.moviesArr.add(updated_movie);
  }*/

  // Not sure if I should handle the logic or leave it up to the console and I create multiple update method for this method :/
  public void updateMovie(Movie movie, int choice, String input){
    switch(choice) {
      case 1:
        movie.setTitle(input);
      case 2:
        movie.setSynopsis(input);
      case 3:
        movie.setDirector(input);
    }
  }

  public void updateMovie(Movie movie, ArrayList<String> cast) {
    movie.setCast(cast);
  }
  
  public void updateMovie(Movie movie, Advisory advisoryRating) {
    movie.setAdvisoryRating(advisoryRating);
  }

  public void updateMovie(Movie movie, Genre genre) {
    movie.setGenre(genre);
  }

  // Idk if should change this method to overload the updateMovie method above... Please advise T^T
  public void updateMovieShowingStatus(Movie movie, ShowStatus showStatus) {
    movie.setShowingStatus(showStatus);
  }

  public ArrayList<Movie> getMovies(){
    return moviesArr;
  }

  public ArrayList<Movie> getMovies(ShowStatus showStatus){
    ArrayList<Movie> filteredArr = new ArrayList<Movie>();

    for (Movie movie : moviesArr) {
      if (movie.getShowingStatus() == showStatus) {
        filteredArr.add(movie);
      }
    }

    return filteredArr;
  }

  public ArrayList<Movie> getMovies(SortCriteria sortCriteria){
    ArrayList<Movie> sortedArr = moviesArr;

    if (sortCriteria == SortCriteria.TITLE) {
      sortedArr.sort((m1, m2) -> m1.getTitle().compareTo(m2.getTitle()));
    }
    // Sorts movies by overall rating in descending order
    else if (sortCriteria == SortCriteria.RATING) {
      Comparator<Movie> comparator = (m1, m2) -> ((Float) m1.getOverallRating()).compareTo((Float) m2.getOverallRating());
      sortedArr.sort(comparator.reversed());
    }
    else if (sortCriteria == SortCriteria.SALES) {
      /* NOT TESTED! Test when integrating with SalesManager */
      BookingManager bManager = new BookingManager();
      Comparator<Movie> comparator = (m1, m2) -> ((Float) SalesManager.getSalesByMovie(bManager.getBookings(), m1.getTitle())).compareTo(
                                                  (Float) SalesManager.getSalesByMovie(bManager.getBookings(), m2.getTitle()));
      sortedArr.sort(comparator.reversed());
    }

    return sortedArr;
  }
  
}