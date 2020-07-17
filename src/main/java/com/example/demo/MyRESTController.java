package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRESTController {

	@Autowired
	ContactRepository repository;

	@GetMapping("/contacts")
	@CrossOrigin(origins = "http://localhost:8081")
	public Iterable<Contact> getContacts() {
		return repository.findAll();
	}
	
	@PostMapping("/contact")
	@CrossOrigin(origins = "http://localhost:8081")
	public String saveItem(@RequestBody Contact contact) {
		Contact newItem = repository.save(contact);
		
		if(newItem == null) {
		  return "Contact IS not SAVED";
		}
		return "Contact Saved Successfully";
	}
	
	@GetMapping("/search")
	@CrossOrigin(origins = "http://localhost:8081")
	public ResponseEntity getItem(@RequestParam("query") String query) {
		if(query != null) {
			List<Contact> list = repository.findByNameOrEmail(query, query);
			return new ResponseEntity<>(list, HttpStatus.OK);
		}
		return new ResponseEntity<>("Contact Not Found",HttpStatus.OK);
	}
	

}
