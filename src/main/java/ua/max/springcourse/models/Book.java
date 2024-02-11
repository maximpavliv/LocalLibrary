package ua.max.springcourse.models;

import jakarta.validation.constraints.Size;

public class Book {
    private int id;

    @Size(min = 2, max = 50, message = "Book name must be between 2 and 50 characters")
    private String bookName;

    @Size(min = 2, max = 50, message = "Author name must be between 2 and 50 characters")
    private String authorName;

    private int yearOfPublication;

    public Book() {}

    public Book(int id, String bookName, String authorName, int yearOfPublication) {
        this.id = id;
        this.bookName = bookName;
        this.authorName = authorName;
        this.yearOfPublication = yearOfPublication;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }
}