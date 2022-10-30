package model;

import java.util.ArrayList;

enum ShowStatus {
  COMING_SOON, PREVIEW, NOW_SHOWING, END_OF_SHOWING
}

enum Advisory {
  P13, P16, P18, NC16, M18
}

enum Genre {
  ACTION, ADVENTURE, COMEDY, DRAMA, FANTASY, HORROR, ROMANCE, THRILLER, WESTERN
}

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
    this.title = title;
    this.showingStatus = showingStatus;
    this.synopsis = synopsis;
    this.director = director;
    this.cast = cast;
    this.advisoryRating = advisoryRating;
    this.genre = genre;
  }

  public void addReview(Review review) {
    // todo: check if the user has alr
    reviews.add(review);
  }

  public float getOverallRating() {
    // loop through the list of reviews and get the average rating
    return 0.0;
  }
}
