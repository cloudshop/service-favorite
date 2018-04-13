package com.eyun.favorite.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.eyun.favorite.client.AuthorizedFeignClient;

@AuthorizedFeignClient(name="product")
public interface ProductSkuService {
	
	@PostMapping("/api/product/follow")
	public List<Map> follow(@RequestBody List pros);
}
