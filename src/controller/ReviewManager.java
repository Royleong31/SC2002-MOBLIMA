package controller;
import java.util.ArrayList;

import model.Review;
import model.Movie;
import model.Account.MovieGoerAccount;

public class ReviewManager {

  public void addReview(Review review) throws Exception {
    Movie movie = review.getMovie();
    
    if (hasPreviouslySubmittedReview(review.getMovieGoer(), movie)) {
      throw new Exception("User has already submitted a review for this movie");
    }
    movie.addReview(review);
  }

  public boolean hasPreviouslySubmittedReview(MovieGoerAccount movieGoer, Movie movie) {
    ArrayList<Review> reviews = movie.getReviews();
    Review review;

    for(int i = 0; i < reviews.size(); i++) {
      review = reviews.get(i);
      if(review.getMovieGoer().equals(movieGoer) && review.getMovie().equals(movie)) {
        return true;
      }
    }

    return false;
  }

  public ArrayList<Review> getReviews(ArrayList<Movie> movies) {
    ArrayList<Review> reviews = new ArrayList<Review>();

    for (Movie movie : movies) {
      reviews.addAll(movie.getReviews());
    }

    return reviews;
  }
}
