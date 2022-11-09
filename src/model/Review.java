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
  private final Movie movie;
  private String comments;
  private int rating;
  private final MovieGoerAccount movieGoer;

  public Review(Movie movie, String comments, int rating, MovieGoerAccount movieGoer) {
    this.movie = movie;
    this.comments = comments;
    this.rating = rating;
    this.movieGoer = movieGoer;
  }

  public void setRating(int rating) {
    this.rating = rating;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  public Movie getMovie() {
    return this.movie;
  }

  public String getComments() {
    return this.comments;
  }

  public float getRating() {
    return this.rating;
  }

  public MovieGoerAccount getMovieGoer() {
    return this.movieGoer;
  }
  
}
