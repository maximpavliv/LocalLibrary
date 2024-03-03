package ua.max.springcourse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.max.springcourse.models.Book;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    public List<Book> findByBorrowerId(int borrowerId);
    List<Book> findByBookNameStartingWithIgnoreCase(String searchString);
}
