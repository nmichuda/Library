package learn.library.controllers;


import learn.library.domain.BookService;
import learn.library.models.Book;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/books")
public class BookController {

    private final BookService service;


    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public List<Book> findAll(){
        return service.findAll();
    }
}
