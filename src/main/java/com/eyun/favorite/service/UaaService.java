package com.eyun.favorite.service;

import org.springframework.web.bind.annotation.GetMapping;

import com.eyun.favorite.client.AuthorizedFeignClient;
import com.eyun.favorite.web.rest.dto.UserDTO;

@AuthorizedFeignClient(name="uaa")
public interface UaaService {

	@GetMapping("/api/account")
	public UserDTO getAccount();
	
}
