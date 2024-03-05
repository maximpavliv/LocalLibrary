package ua.max.springcourse.LocalLibrary.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.max.springcourse.LocalLibrary.dao.BookDAO;
import ua.max.springcourse.LocalLibrary.models.Book;

import java.time.Year;

@Component
public class BookValidator implements Validator {

    private final BookDAO bookDAO;

    @Autowired
    public BookValidator(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book book = (Book) target;

        int currentYear = Year.now().getValue();
        if(book.getYearOfPublication() > currentYear) {
            errors.rejectValue("yearOfPublication", "",
                    "Year of publication cannot be greater than " + currentYear);
        }
    }
}
