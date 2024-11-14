package com.dev.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.model.Receipe;
import com.dev.model.User;
import com.dev.repository.ReceipeRepository;

@Service
public class ReceipeServiceImplementation implements ReceipeService{
	@Autowired
	private ReceipeRepository receipeRepository;

	@Override
	public Receipe createReceipe(Receipe receipe, User user) {
		Receipe createdReceipe = new Receipe();
		createdReceipe.setTitle(receipe.getTitle());
		createdReceipe.setImage(receipe.getImage());
		createdReceipe.setDescription(receipe.getDescription());
		createdReceipe.setUser(user);
		createdReceipe.setCreatedAt(LocalDateTime.now());		
		return receipeRepository.save(createdReceipe);
	}

	@Override
	public Receipe findReceipeById(Long id) throws Exception {
		Optional<Receipe> opt = receipeRepository.findById(id);
		if(opt.isPresent())
		{
			return opt.get();
		}
		throw new Exception("Receipe not found with the provided id: " + id);
	}

	@Override
	public void deleteReceipe(Long id) throws Exception {
		findReceipeById(id);
		receipeRepository.deleteById(id);
	}

	@Override
	public Receipe updateReceipe(Receipe receipe, Long id) throws Exception {
		Receipe oldReceipe =  findReceipeById(id);
		if(receipe.getTitle()!=null) {
			oldReceipe.setTitle(receipe.getTitle());
		}
		if(receipe.getImage()!=null) {
			oldReceipe.setImage(receipe.getImage());
		}
		if(receipe.getDescription()!=null) {
			oldReceipe.setDescription(receipe.getDescription());
		}
		return receipeRepository.save(oldReceipe);
	}

	@Override
	public List<Receipe> findAllReceipe() {
		return receipeRepository.findAll();
	}

	@Override
	public Receipe likeReceipe(Long receipeId, User user) throws Exception {
		Receipe receipe = findReceipeById(receipeId);
		if(receipe.getLikes().contains(user.getId())) {
			receipe.getLikes().remove(user.getId());
		}
		else {
			receipe.getLikes().add(user.getId());
		}
		return receipeRepository.save(receipe);
	}

}
