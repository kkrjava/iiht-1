package com.example.demo.iiht.controller;

import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.iiht.dto.Book;
import com.example.demo.iiht.dto.ResultDTO;
import com.example.demo.iiht.dto.Subject;
import com.example.demo.iiht.service.AssignmentService;

@RestController
@RequestMapping("/assignment")
public class AssignmentController {

	public static final Logger logger = LoggerFactory.getLogger(AssignmentController.class);

	@Autowired
	private AssignmentService assignmentService;
	
	@RequestMapping(value = "/addBook", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultDTO addBook(@RequestBody Book book){
		ResultDTO resultDTO = new ResultDTO();
		if(null != book.getTitle()){
		String status = assignmentService.addBook(book);
		resultDTO.setStatus(status);
		resultDTO.setMessage("Book added succesfully");
		}else{
			resultDTO.setStatus("Failure");
			resultDTO.setMessage("Book can not be added");	
		}
		return resultDTO;
	}
	
	@RequestMapping(value = "/updateBook", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultDTO updateBook(@RequestBody Book book){
		ResultDTO resultDTO = new ResultDTO();
		String status = assignmentService.updateBook(book);
		resultDTO.setStatus(status);
		resultDTO.setMessage("Book updated succesfully");
		return resultDTO;
	}
	
	@RequestMapping(value = "/addSubject", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultDTO addSubject(@RequestBody Subject subject){
		ResultDTO resultDTO = new ResultDTO();
		String status = assignmentService.addSubject(subject);
		resultDTO.setStatus(status);
		resultDTO.setMessage("Subject added succesfully");
		return resultDTO;
	}
	

	@RequestMapping(value = "/deleteBook", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultDTO deleteBook(@RequestParam String title){
		ResultDTO resultDTO = new ResultDTO();
		String status = assignmentService.deleteBook(title);
		resultDTO.setStatus(status);
		resultDTO.setMessage("Book "+title+"deleted  added succesfully");
		return resultDTO;
	}
	

	@RequestMapping(value = "/deleteBookById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultDTO deleteBookById(@RequestParam long bookId){
		ResultDTO resultDTO = new ResultDTO();
		String status = assignmentService.deleteBookById(bookId);
		resultDTO.setStatus(status);
		if(status.equalsIgnoreCase("Sucess")){
		resultDTO.setMessage("Book "+bookId+"deleted  added succesfully");
		}else{
			resultDTO.setMessage("Book Id "+bookId+" doesnt exist socan not be deleted");
		}
		return resultDTO;
	}
	
	
	@RequestMapping(value = "/deleteSubject", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultDTO deleteSubject(@RequestParam String title){
		ResultDTO resultDTO = new ResultDTO();
		String status = assignmentService.deleteSubject(title);
		resultDTO.setStatus(status);
		resultDTO.setMessage("Subject "+title+"deleted  added succesfully");
		return resultDTO;
	}
	

	@RequestMapping(value = "/searchBook", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Book> searchBook(@RequestParam String title) throws ParseException{
		List<Book> books = assignmentService.searchBook(title);
		return books;
	}
	
	@RequestMapping(value = "/searchBookById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Book> searchBookById(@RequestParam long bookId) throws ParseException{
		List<Book> books = assignmentService.searchBookById(bookId);
		return books;
	}
	
	@RequestMapping(value = "/searchSubject", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Subject searchSubject(@RequestParam String title){
		Subject subject = assignmentService.searchSubject(title);
		return subject;
	}
	

	@RequestMapping(value = "/searchSubjectByDuration", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Subject searchSubjectByDuration(@RequestParam int duration){
		Subject subject = assignmentService.searchSubjectByDuration(duration);
		return subject;
	}

}
