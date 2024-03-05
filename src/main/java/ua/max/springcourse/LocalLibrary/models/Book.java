package ua.max.springcourse.LocalLibrary.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 2, max = 50, message = "Book name must be between 2 and 50 characters")
    @Column(name = "book_name")
    private String bookName;

    @Size(min = 2, max = 50, message = "Author name must be between 2 and 50 characters")
    @Column(name = "author_name")
    private String authorName;

    @Column(name = "year_of_publication")
    private int yearOfPublication;

    @ManyToOne
    @JoinColumn(name = "borrowers_id", referencedColumnName = "id")
    private Person borrower;

    @Column(name = "borrowed_date")
    private LocalDateTime borrowedDate;

    public Book() {}

    public Book(String bookName, String authorName, int yearOfPublication) {
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

    public Person getBorrower() {
        return borrower;
    }

    public void setBorrower(Person borrower) {
        this.borrower = borrower;
    }

    public LocalDateTime getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(LocalDateTime borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    @Transient
    public boolean isBorrowingPeriodExceeded() {
        if (borrowedDate == null) {
            return false;
        }
        LocalDateTime dueDate = borrowedDate.plusDays(10);
        return LocalDateTime.now().isAfter(dueDate);
    }
}