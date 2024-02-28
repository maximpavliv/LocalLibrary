package ua.max.springcourse.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.max.springcourse.models.Book;
import ua.max.springcourse.models.Person;
import ua.max.springcourse.repositories.BooksRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BooksService {

    private final BooksRepository booksRepository;

    private final PeopleService peopleService;

    public BooksService(BooksRepository booksRepository, PeopleService peopleService) {
        this.booksRepository = booksRepository;
        this.peopleService = peopleService;
    }

    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    public Book findById(int id) {
        return booksRepository.findById(id).orElse(null);
    }

    public List<Book> findByBorrowerId(int borrowerId) {
        return booksRepository.findByBorrowerId(borrowerId);
    }

    @Transactional
    public void release(int id) {
        Book book = findById(id);
        Person returner = book.getBorrower();
        book.setBorrower(null);
        if (returner != null && returner.getBorrowedBooks() != null)
            returner.getBorrowedBooks().remove(book);
    }

    @Transactional
    public void borrow(int bookId, int personId) {
        Book book = findById(bookId);
        Person borrower = peopleService.findById(personId);
        book.setBorrower(borrower);
        if (borrower.getBorrowedBooks() == null)
            borrower.setBorrowedBooks(new ArrayList<>());
        borrower.getBorrowedBooks().add(book);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        updatedBook.setId(id);
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }
}
