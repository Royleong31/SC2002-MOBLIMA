package model;

import model.Account.MovieGoerAccount;

/**
 * Review class
 * Contains details of the review and get/set functions
 *
 @author Roy Leong, Song Chen
 @version 1.1
 @since 2022-10-30
*/
public class Review {
  /**
   * Movie the review is for
   */
  private final Movie movie;

  /**
   * Comments in the review
   */
  private String comments;

  /**
   * Rating given in the review
   */
  private int rating;

  /**
   * Moviegoer/user who made the review
   */
  private final MovieGoerAccount movieGoer;

  /**
   * Constructor for review
   * 
   * @param movie movie the review is for
   * @param comments comments in the review
   * @param rating rating given in the review
   * @param movieGoer moviegoer/user who made the review
   */
  public Review(Movie movie, String comments, int rating, MovieGoerAccount movieGoer) {
    this.movie = movie;
    this.comments = comments;
    this.rating = rating;
    this.movieGoer = movieGoer;
  }

  /**
   * Setter method to set the rating given to the review
   * 
   * @param rating rating for the movie
   */
  public void setRating(int rating) {
    this.rating = rating;
  }

  /**
   * Setter method to set the comments of the review
   * 
   * @param comments comments in the review
   */
  public void setComments(String comments) {
    this.comments = comments;
  }

  /**
   * Getter method to get the movie the review is for
   * 
   * @return movie object the review is for
   */
  public Movie getMovie() {
    return this.movie;
  }

  /**
   * Getter method to get the comments in the review
   * 
   * @return comments in the review
   */
  public String getComments() {
    return this.comments;
  }

  /**
   * Getter method to get the rating of the review
   * 
   * @return rating of the review
   */
  public float getRating() {
    return this.rating;
  }

  /**
   * Getter method to get the moviegoer/user who made the review
   * 
   * @return moviegoer object who made the review
   */
  public MovieGoerAccount getMovieGoer() {
    return this.movieGoer;
  }
  
}
