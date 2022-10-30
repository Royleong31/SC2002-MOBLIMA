package model;
import enums.*;
import java.util.ArrayList;

/**
 * Account for a staff member.
 * Contains the staff id
 *
 @author Roy Leong
 @version 1.0
 @since 2022-10-30
*/
public class Movie {
  private String title;
  private ShowStatus showingStatus;
  private String synopsis;
  private String director;
  private ArrayList<String> cast;
  private ArrayList<Review> reviews = new ArrayList<Review>();
  private Advisory advisoryRating;
  private Genre genre;

  public Movie(String title, ShowStatus showingStatus, String synopsis, String director, ArrayList<String> cast, Advisory advisoryRating, Genre genre) {
    setMovieDetails(title, showingStatus, synopsis, director, cast, advisoryRating, genre);
  }

  public void addReview(Review review) {
    // TODO: Check if the user has alr reviewed this movie, if so, throw an error
    // allow the user to add a review if the user hasn't alr done so
    reviews.add(review);
  }

  // TODO: Update only parts of the movie attributes (allow the user to change only 1 thing without having to retype in everything)
  // Need to have optional params for each field, and for empty fields, the current one will be used
  public void setMovieDetails(String title, ShowStatus showingStatus, String synopsis, String director, ArrayList<String> cast, Advisory advisoryRating, Genre genre) {
    this.title = title;
    this.showingStatus = showingStatus;
    this.synopsis = synopsis;
    this.director = director;
    this.cast = cast;
    this.advisoryRating = advisoryRating;
    this.genre = genre;
  }

  /**
   * Allows the admins to change the status of the movie
   * @param showStatus
   */
  public void updateShowingStatus(ShowStatus showStatus) {
    this.showingStatus = showStatus;
  }
  
  /*
   * Gets the overall rating from all the reviews of this movie
   */
  public float getOverallRating() {
    // loop through the list of reviews and get the average rating
    return 0.0f;
  }
}
