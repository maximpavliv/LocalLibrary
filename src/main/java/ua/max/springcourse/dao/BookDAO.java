package ua.max.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.max.springcourse.models.Book;

import java.util.List;

@Component
public class BookDAO {
    /*private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public List<Book> getBorrowersBooks(int borrowersId) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE borrowers_id=?",
                new BeanPropertyRowMapper<>(Book.class), borrowersId);
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new BeanPropertyRowMapper<>(Book.class), id)
                .stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update(
                "INSERT INTO Book(book_name, author_name, year_of_publication, borrowers_id) VALUES(?, ?, ?, ?)",
                book.getBookName(), book.getAuthorName(), book.getYearOfPublication(), book.getBorrowersId());
    }

    public void update(int id, Book updatedBook) {
        jdbcTemplate.update(
                "UPDATE Book SET book_name=?, author_name=?, year_of_publication=?, borrowers_id=? WHERE id=?",
                updatedBook.getBookName(), updatedBook.getAuthorName(), updatedBook.getYearOfPublication(),
                updatedBook.getBorrowersId(), id);
    }

    public void release(int id) {
        jdbcTemplate.update("UPDATE Book SET borrowers_id=NULL WHERE id=?", id);
    }

    public void borrow(int id, int borrowersId) {
        jdbcTemplate.update("UPDATE Book SET borrowers_id=? WHERE id=?", borrowersId, id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
    }*/
}