package controller;

import java.util.ArrayList;

import model.Review;
import model.Movie;
import model.Account.MovieGoerAccount;

/**
 * Review Manager
 * Responsible for handling all addition/retrieval of reviews
 *
 @author Roy Leong, Song Chen
 @version 1.1
 @since 2022-10-30
*/
public class ReviewManager {
  /**
   * Add new review to a movie
   * 
   * @param movie movie object to add review to
   * @param comments review comment
   * @param rating review rating
   * @param movieGoer user the review belongs to
   * @throws Exception if the specified user has already made a review on the movie designated
   */
  public void addReview(Movie movie, String comments, int rating, MovieGoerAccount movieGoer) throws Exception {
    if (hasPreviouslySubmittedReview(movieGoer, movie)) 
      throw new Exception("User has already submitted a review for this movie");

    Review review = new Review(movie, comments, rating, movieGoer);
    movie.addReview(review);
  }

  /**
   * Checks if user specified has previously submitted a review on a particular movie
   * 
   * @param movieGoer user
   * @param movie movie object
   * @return true if user has previously submitted a review for the movie, false otherwise
   */
  public boolean hasPreviouslySubmittedReview(MovieGoerAccount movieGoer, Movie movie) {
    ArrayList<Review> reviews = movie.getReviews();

    for(Review cur: reviews) {
      if(cur.getMovieGoer().equals(movieGoer) && cur.getMovie().equals(movie)) {
        return true;
      }
    }

    return false;
  }

  /**
   * Get an arraylist of all reviews for specfied movies
   * 
   * @param movies arraylist of movie object to retrieve reviews from
   * @return arraylist of all reviews for specified movies
   */
  public ArrayList<Review> getReviews(ArrayList<Movie> movies) {
    ArrayList<Review> reviews = new ArrayList<Review>();

    for (Movie movie : movies) {
      reviews.addAll(movie.getReviews());
    }

    return reviews;
  }
}
