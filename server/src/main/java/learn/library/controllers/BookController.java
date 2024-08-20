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

    @GetMapping("/{userId}")
    public List<Book> findByUser(@PathVariable int userId){
        return service.findByUserId(userId);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Book book){
        Result<Book> result = service.add(book);
        if(result.isSuccess()){
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }

        return ErrorResponse.build(result);

    }
    @PutMapping("/{bookId}")
    public ResponseEntity<Object> update(@PathVariable int bookId, @RequestBody Book book){
        if(bookId != book.getBookId()){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Book> result = service.update(book);

        if(result.isSuccess()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> deleteById(@PathVariable int bookId) {
        if(service.deleteById(bookId)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
