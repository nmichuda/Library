package learn.library.controllers;


import learn.library.domain.BookService;
import learn.library.domain.Result;
import learn.library.models.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Book book){
        Result<Book> result = service.add(book);
        if(result.isSuccess()){
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }

        return ErrorResponse.build(result);

    }
}
