package com.dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.model.Receipe;
import com.dev.model.User;
import com.dev.repository.UserRepository;
import com.dev.service.ReceipeService;
import com.dev.service.UserService;

@RestController
@RequestMapping("/api/receipes")
public class ReceipeController {
	@Autowired
	private ReceipeService receipeService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/{userId}")
	public Receipe createReceipe(@RequestBody Receipe receipe,  @PathVariable Long userId) throws Exception {
		User user = userService.findUserById(userId);
		Receipe createdReceipe = receipeService.createReceipe(receipe, user);
		return createdReceipe;
		
	}
	
	@PutMapping("/{id}")
	public Receipe updateReceipe(@RequestBody Receipe receipe, @PathVariable Long id) throws Exception {
		Receipe updatedReceipe = receipeService.updateReceipe(receipe, id);
		return updatedReceipe;
	}
	
	@GetMapping()
	public List<Receipe> getReceipe() throws Exception {
		List<Receipe> receips = receipeService.findAllReceipe();
		return receips;
	}
	
	@DeleteMapping("/{receipeId}")
	public String deleteReceipe(@PathVariable Long receipeId) throws Exception {
		receipeService.deleteReceipe(receipeId);
		return "Recipe deleted Successfully";		
	}
	
	@PutMapping("/{id}/like/{userId}")
	public Receipe likeReceipe(@PathVariable Long userId, @PathVariable Long id) throws Exception {
		User user = userService.findUserById(userId);
		Receipe updatedReceipe = receipeService.likeReceipe(id, user);
		return updatedReceipe;
	}
	

}
