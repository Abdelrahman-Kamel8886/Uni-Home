package com.example.myvideo.models;

public class CommentModel {

    private String comment;
    private String img;
    private String name;

    public CommentModel() {
    }

    public CommentModel(String comment, String img, String name) {
        this.comment = comment;
        this.img = img;
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
