package com.example.covidfight;

//Class ReviewItem: class for a review;

public class ReviewItem {
    private int starNumbers;
    private int id;
    private String comment;

    public ReviewItem(int starNumbers, int id, String comment) {
        this.starNumbers = starNumbers;
        this.id = id;
        this.comment = comment;
    }

    public int getStarNumbers() {
        return starNumbers;
    }

    public int getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }
}
