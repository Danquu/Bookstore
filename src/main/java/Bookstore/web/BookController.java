package Bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import Bookstore.domain.Book;
import Bookstore.domain.BookRepository;
import Bookstore.domain.CategoryRepository;


@Controller
public class BookController {

	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository crepository;
	
	
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public String getBook() {
		return "index";
	}
	
	@RequestMapping(value="/booklist", method = RequestMethod.GET)
	public String listBook(Model model) {
		
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}
	
	//REST
	@RequestMapping(value="/books", method = RequestMethod.GET)
	public @ResponseBody List<Book> bookListRest() {
		return (List<Book>) repository.findAll();
	}
	
	//REST
	@RequestMapping(value="/books/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {
		return repository.findById(bookId);
	}
	//REST
	@RequestMapping(value="/books", method = RequestMethod.POST)
	public @ResponseBody Book saveBookRest(@RequestBody Book book) {
		return repository.save(book);
	}
		
	@RequestMapping(value ="/addbook")
    public String addBook(Model model){
    	model.addAttribute("book", new Book());
    	model.addAttribute("categories", crepository.findAll());
        return "addbook";
    }     
		
	@RequestMapping(value ="/save", method = RequestMethod.POST)
    public String saveBook(Book book){
		//jos id 0 tai null = SQL Insert, muuten SQL Update
        repository.save(book);
        return "redirect:booklist";
    }    
	
	@RequestMapping(value ="/delete/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ADMIN')")
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
    	repository.deleteById(bookId);
        return "redirect:../booklist";
    }     
	
	@RequestMapping(value="/updatebook/{id}", method = RequestMethod.GET)
	public String updateBook(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("book", repository.findById(bookId).get());
		return "updatebook";
	}
}
