package com.eyun.favorite.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.codahale.metrics.annotation.Timed;
import com.eyun.favorite.client.AuthorizedFeignClient;
import com.eyun.favorite.domain.Mercury;
import com.eyun.favorite.web.rest.dto.UserDTO;

	@AuthorizedFeignClient(name="user")
	public interface UserService{

		@GetMapping("/api/mercuries/{id}")
		public UserDTO getAccount(@PathVariable("id") Long id);
		
	    @PostMapping("/api/mercuries/getFavMercuries")
	    public ResponseEntity<List<Map>> getFavMercuries(@RequestBody List<Long> ids);
		
	}

