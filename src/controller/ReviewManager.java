package controller;
import java.util.ArrayList;

import model.Review;
import model.Account.MovieGoerAccount;

public class ReviewManager {
  private ArrayList<Review> reviews = new ArrayList<Review>();

  public void addReview(Review review) {
    reviews.add(review);
  }

  public boolean hasPreviouslySubmittedReview(MovieGoerAccount movieGoer) {
    return false;
  }
}
