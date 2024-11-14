package com.dev.service;

import java.util.List;

import com.dev.model.Receipe;
import com.dev.model.User;

public interface ReceipeService {
	
	public Receipe createReceipe(Receipe receipe, User user);
	public Receipe findReceipeById(Long id) throws Exception;
	public void deleteReceipe(Long id) throws Exception;
	public Receipe updateReceipe(Receipe receipe, Long id) throws Exception;
	public List<Receipe> findAllReceipe();
	public Receipe likeReceipe(Long receipeId, User user) throws Exception;
	

}
