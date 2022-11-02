package controller;
import java.util.ArrayList;

import model.Review;
import model.Movie;
import model.Account.MovieGoerAccount;

public class ReviewManager {
  private ArrayList<Review> reviews;

  // Not sure I get this but as every review needs a movie, e.g. no review should exist without a movie
  // So it will be the review manager interacting with the review array list in the movie objects instead of storing its owm
  public ReviewManager() {}

  public void addReview(Review review) throws Exception {
    Movie movie = review.getMovie();
    reviews = movie.getReviews();
    if (!hasPreviouslySubmittedReview(review.getMovieGoer(), movie)) {
      movie.addReview(review);
    }
    else {
      throw new Exception("User has already submitted a review for this movie");
    }
  }

  public boolean hasPreviouslySubmittedReview(MovieGoerAccount movieGoer, Movie movie) {
    Review review;

    for(int i = 0; i < this.reviews.size(); i++) {
      review = this.reviews.get(i);
      if(review.getMovieGoer().equals(movieGoer) && review.getMovie().equals(movie)) {
        return true;
      }
    }

    return false;
  }
}
