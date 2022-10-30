package model;

import model.Account.MovieGoerAccount;

/**
 * Account for a staff member.
 * Contains the staff id
 *
 @author Roy Leong
 @version 1.0
 @since 2022-10-30
*/
public class Review {
  private Movie movie;
  private String comments;
  private float rating;
  private MovieGoerAccount movieGoer;

  public Review(Movie movie, String comments, float rating, MovieGoerAccount movieGoer) {
    this.movie = movie;
    this.comments = comments;
    this.rating = rating;
    this.movieGoer = movieGoer;
  }
  
}
