package ua.max.springcourse.LocalLibrary.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.max.springcourse.LocalLibrary.dao.BookDAO;
import ua.max.springcourse.LocalLibrary.dao.PersonDAO;
import ua.max.springcourse.LocalLibrary.models.Book;
import ua.max.springcourse.LocalLibrary.models.Person;
import ua.max.springcourse.LocalLibrary.services.BooksService;
import ua.max.springcourse.LocalLibrary.services.PeopleService;
import ua.max.springcourse.LocalLibrary.util.BookValidator;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BooksController {

    private BookDAO bookDAO;
    private PersonDAO personDAO;
    private BooksService booksService;
    private PeopleService peopleService;
    private BookValidator bookValidator;

    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO, BooksService booksService, PeopleService peopleService,
                           BookValidator bookValidator) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
        this.booksService = booksService;
        this.peopleService = peopleService;
        this.bookValidator = bookValidator;
    }

    @GetMapping()
    public String index(@RequestParam(value = "page", required = false) Integer page,
                        @RequestParam(value = "books_per_page", required = false) Integer booksPerPage,
                        @RequestParam(value = "sort_by_year", defaultValue = "false") boolean sortByYear, Model model) {
        System.out.println("Hello from index of books");
        if (page==null || booksPerPage == null)
            model.addAttribute("books", booksService.findAll(sortByYear));
        else
            model.addAttribute("books", booksService.findAll(page, booksPerPage, sortByYear));
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int bookId, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", booksService.findById(bookId));
        model.addAttribute("people", peopleService.findAll());
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @GetMapping("/search")
    public String searchBook(@RequestParam(value = "searchString", required = false) String searchString, Model model) {
        List<Book> foundBooks = booksService.searchBooks(searchString);
        model.addAttribute("searchString", searchString);
        model.addAttribute("foundBooks", foundBooks);
        return "books/search";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        bookValidator.validate(book, bindingResult);

        if (bindingResult.hasErrors())
            return "books/new";

        booksService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", booksService.findById(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        // we don't receive this field in the attributes, so we have to reset it.
        book.setBorrower(booksService.findById(id).getBorrower());

        bookValidator.validate(book, bindingResult);

        if (bindingResult.hasErrors())
            return "books/edit";

        booksService.update(id, book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id) {
        booksService.release(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/borrow")
    public String borrow(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        booksService.borrow(id, person.getId());
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/books";
    }
}