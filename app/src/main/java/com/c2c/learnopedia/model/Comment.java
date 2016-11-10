package com.c2c.learnopedia.model;

/**
 * Created by mprak on 10-10-2016.
 */

public class Comment {

    public String getResponseId() {
        return ResponseId;
    }

    public void setResponseId(String responseId) {
        ResponseId = responseId;
    }

    String ResponseId;
    public Comment(String review) {
        this.review = review;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    String review;
}
