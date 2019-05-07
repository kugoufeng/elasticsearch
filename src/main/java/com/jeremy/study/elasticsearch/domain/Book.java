package com.jeremy.study.elasticsearch.domain;

import java.io.Serializable;

public class Book implements Serializable {
    private static final long serialVersionUID = -759629151992954827L;

    private String bookName;

    private String bookCover;

    private String bookAuthor;

    private String shortDesc;

    private String readUrl;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookCover() {
        return bookCover;
    }

    public void setBookCover(String bookCover) {
        this.bookCover = bookCover;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getReadUrl() {
        return readUrl;
    }

    public void setReadUrl(String readUrl) {
        this.readUrl = readUrl;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                ", bookCover='" + bookCover + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", shortDesc='" + shortDesc + '\'' +
                ", readUrl='" + readUrl + '\'' +
                '}';
    }
}
