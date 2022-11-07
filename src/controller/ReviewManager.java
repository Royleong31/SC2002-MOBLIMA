package controller;
import java.util.ArrayList;

import model.Review;
import model.Movie;
import model.Account.MovieGoerAccount;

public class ReviewManager {

  public void addReview(Movie movie, String comments, Float rating, MovieGoerAccount movieGoer) throws Exception {
    if (hasPreviouslySubmittedReview(movieGoer, movie)) 
      throw new Exception("User has already submitted a review for this movie");

    Review review = new Review(movie, comments, rating, movieGoer);
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
