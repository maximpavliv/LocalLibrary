package ua.max.springcourse.LocalLibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.max.springcourse.LocalLibrary.models.Book;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    public List<Book> findByBorrowerId(int borrowerId);
    List<Book> findByBookNameStartingWithIgnoreCase(String searchString);
}
