package com.example.myvideo.models;

public class BookModel {

    private  String book_title;
    private  String book_author;
    private  String book_overview;
    private  String link;
    private  String imglink;


    public BookModel() {
    }

    public BookModel(String book_title, String book_author, String book_overview, String link, String imglink) {
        this.book_title = book_title;
        this.book_author = book_author;
        this.book_overview = book_overview;
        this.link = link;
        this.imglink = imglink;
    }

    public String getImglink() {
        return imglink;
    }

    public void setImglink(String imglink) {
        this.imglink = imglink;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public String getBook_overview() {
        return book_overview;
    }

    public void setBook_overview(String book_overview) {
        this.book_overview = book_overview;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
