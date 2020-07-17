package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MyRESTController {

	@Autowired
	ContactRepository repository;

	@GetMapping("/contacts")
	public Iterable<Contact> getContacts() {
		return repository.findAll();
	}
	
	@PostMapping("/contact")
	public String saveItem(@RequestBody Contact contact) {
		Contact newItem = repository.save(contact);
		
		if(newItem == null) {
		  return "Contact IS not SAVED";
		}
		return "Contact Saved Successfully";
	}

}
