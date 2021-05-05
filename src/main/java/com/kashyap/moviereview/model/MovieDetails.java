package com.kashyap.moviereview.model;

public class MovieDetails {

    private long id;
    private String name;
    private Long[] genreIds;
    private short rating;
    private long commentId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long[] getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(Long[] genreIds) {
        this.genreIds = genreIds;
    }

    public short getRating() {
        return rating;
    }

    public void setRating(short rating) {
        this.rating = rating;
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }
}
