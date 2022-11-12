package controller;

import java.util.ArrayList;

import enums.ShowStatus;
import enums.SortCriteria;
import enums.Advisory;
import enums.Genre;
import enums.MovieType;
import model.Movie;
import utils.SalesUtils;
import utils.Utils;

/**
 * Movie Manager
 * Responsible for handling all addition/deletion/update/retrieval of movies
 *
 @author Roy Leong, Song Chen
 @version 1.1
 @since 2022-10-30
*/
public class MovieManager {
  /**
   * Collection of all movies the company can show
   */
  private ArrayList<Movie> moviesArr = new ArrayList<Movie>();

  /**
   * Constructor for MovieManager object
   */
  public MovieManager() {
    System.out.println("Movie manager created");
  }

  /**
   * Add new movie
   * 
   * @param movie the movie object to be added
   * @throws Exception if there already exists a movie with the same name
   */
  public void addMovie(Movie movie) throws Exception {
    for (Movie cur: this.moviesArr) {
      if (cur.getTitle().equals(movie.getTitle())) {
        throw new Exception("Movie already exists");
      }
    }

    this.moviesArr.add(movie);
  }

  /**
   * Remove a specified movie by setting its show status as end of showing
   * 
   * @param movieTitle title of the movie to be removed
   * @throws Exception if movie does not exist
   */
  public void removeMovie(String movieTitle) throws Exception{
    Movie movie = this.getMovieByName(movieTitle);
    this.updateMovieShowingStatus(movie, ShowStatus.END_OF_SHOWING);
  }

  /**
   * Updates a movie's showing status
   * 
   * @param movie movie object to update
   * @param showStatus show status (enum)
   */
  public void updateMovieShowingStatus(Movie movie, ShowStatus showStatus) {
    movie.setShowingStatus(showStatus);
  }

  /**
   * Updates a movie's attribute based on provided parameters, if value of parameter given is null do not update that specific attribute
   * 
   * @param movie movie object to update
   * @param title movie title
   * @param synopsis movie synopsis
   * @param director movie director
   * @param cast movie cast
   * @param advisoryRating movie advisory rating (enum)
   * @param genre movie genre (enum)
   * @param showStatus movie show status (enum)
   * @param type movie type (enum)
   */
  public void updateMovie(Movie movie, String title, String synopsis, String director, ArrayList<String> cast, Advisory advisoryRating, Genre genre, ShowStatus showStatus, MovieType type) {
    if (title != null) {
      movie.setTitle(title);
    }
    if (synopsis != null) {
      movie.setSynopsis(synopsis);
    }
    if (director != null) {
      movie.setDirector(director);
    }
    if (cast != null) {
      movie.setCast(cast);
    }
    if (advisoryRating != null) {
      movie.setAdvisoryRating(advisoryRating);
    }
    if (genre != null) {
      movie.setGenre(genre);
    }
    if(showStatus != null) {
      movie.setShowingStatus(showStatus);
    }
    if (type != null) {
      movie.setMovieType(type);
    }
  }

  /**
   * Get an arraylist of movies filtered based on their showing statuses and sorted according a a specified sorting criteria
   * Movies with less than 2 reviews are moved to the end of the sorted list
   * 
   * @param sortingCriteria sort criteria (enum)
   * @param showStatus arraylist of showing statuses (enum) to filter movies by
   * @return the filtered and sorted movie arraylist
   */
  public ArrayList<Movie> getMovies(SortCriteria sortingCriteria, ArrayList<ShowStatus> showStatuses) {
    ArrayList<Movie> movieLst = new ArrayList<Movie>();

    // First attempt to filter movies by show statuses if applicable else copies the full exsisting movie list
    if (showStatuses.isEmpty()) {
      for (Movie movie : this.moviesArr) {
        movieLst.add(movie);
      }
    }
    else {
      for (Movie movie : this.moviesArr) {
        for (ShowStatus status : showStatuses) {
          if (movie.getShowingStatus().equals(status)) {
            movieLst.add(movie);
          }
        }
      }
    }

    if (movieLst.size() == 0) return movieLst;

    // Then sorts the list according to a specified sort criteria if applicable
    if (sortingCriteria.equals(SortCriteria.TITLE)) {
      movieLst.sort((m1, m2) -> m1.getTitle().compareTo(m2.getTitle()));
    }
    // Sorts movies by overall rating in descending order
    else if (sortingCriteria.equals(SortCriteria.RATING)) {
      movieLst.sort((m1, m2) -> ((Float) m2.getOverallRating()).compareTo((Float) m1.getOverallRating()));
    }
    else if (sortingCriteria.equals(SortCriteria.SALES)) {
      // Sorts movies by overall sales in descending order
      BookingManager bManager = new BookingManager();
      movieLst.sort((m1, m2) -> ((Float) SalesUtils.getSalesByMovie(bManager.getBookings(), m1.getTitle())).compareTo(
                                (Float) SalesUtils.getSalesByMovie(bManager.getBookings(), m2.getTitle())));
    }

    // Move movies with less than 2 reviews to the end of the list
    ArrayList<Movie> delList = new ArrayList<Movie>();

    // Add movies with less than 2 reviews to the delList
    for (Movie movie : movieLst) {
      if (movie.getReviews().size() < 2) {
        delList.add(movie);
      }
    }
    
    // Removes movies with less than 2 reviews from the list
    movieLst.removeIf((Movie movie) -> delList.contains(movie));

    // Adds movies with less than 2 reviews to the end of the list
    for (Movie movie : delList) {
      movieLst.add(movie);
    }

    return movieLst;
  }

  /**
   * Get an arraylist of all movies if no sort criteria and show statuses were provided
   * 
   * @return arraylist containing all movies
   * @see MovieManager#getMovies(SortCriteria, ArrayList<ShowStatus>)
   */
  public ArrayList<Movie> getMovies() {
    return moviesArr;
  }

  /**
   * Works just like {@link MovieManager#getMovies(SortCriteria, ArrayList<ShowStatus>)} except that the default sort criteria (title)
   * is used if no sort criteria is provided
   * 
   * @param showStatus arraylist of showing statuses (enum) to filter movies by
   * @return filtered movie arraylist sorted by the default sort criteria (title)
   * @see MovieManager#getMovies(SortCriteria, ArrayList<ShowStatus>)
   */
  public ArrayList<Movie> getMovies(ArrayList<ShowStatus> showStatus){
    return getMovies(SortCriteria.TITLE, showStatus);
  }

  /**
   * Works just like {@link MovieManager#getMovies(SortCriteria, ArrayList<ShowStatus>)} except that all show statuses will be used
   * as a filter condition i.e list is only sorted by specified sort criteria not filtered
   * 
   * @param sortingCriteria sort criteria (enum)
   * @return sorted movie arraylist
   * @see MovieManager#getMovies(SortCriteria, ArrayList<ShowStatus>)
   */
  public ArrayList<Movie> getMovies(SortCriteria sortCriteria){
    return getMovies(sortCriteria, Utils.asArrayList(ShowStatus.values()));
  }

  /**
   * Get movie object with the specified title
   * 
   * @param title title of movie
   * @return movie object with the specified title
   * @throws Exception if no movie with the specified title exists
   */
  public Movie getMovieByName(String title) throws Exception {
    for (Movie movie : moviesArr) {
      if (movie.getTitle().equals(title)) {
        return movie;
      }
    }
    
    throw new Exception("No movie with that name found");
  }
  
}