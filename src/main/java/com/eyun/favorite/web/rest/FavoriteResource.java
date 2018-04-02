package com.eyun.favorite.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.eyun.favorite.config.Constants;
import com.eyun.favorite.domain.Favorite;
import com.eyun.favorite.security.SecurityUtils;
import com.eyun.favorite.service.FavoriteService;
import com.eyun.favorite.web.rest.errors.BadRequestAlertException;
import com.eyun.favorite.web.rest.util.HeaderUtil;
import com.eyun.favorite.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.math.stat.descriptive.summary.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * REST controller for managing Favorite.
 */
@RestController
@RequestMapping("/api")
public class FavoriteResource {

    private final Logger log = LoggerFactory.getLogger(FavoriteResource.class);

    private static final String ENTITY_NAME = "favorite";

    private final FavoriteService favoriteService;
 
    public FavoriteResource(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    /**
     * POST  /favorites : Create a new favorite.
     *
     * @param favorite the favorite to create
     * @return the ResponseEntity with status 201 (Created) and with body the new favorite, or with status 400 (Bad Request) if the favorite has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/favorites")
    @Timed
    public ResponseEntity<Favorite> createFavorite(@RequestBody Favorite favorite) throws URISyntaxException {
        log.debug("REST request to save Favorite : {}", favorite);
        if (favorite.getId() != null) {
            throw new BadRequestAlertException("A new favorite cannot already have an ID", ENTITY_NAME, "idexists");
        }
        /**
         * 设置创建时间
         * 设置删除默认值
         */
        favorite.create_time(Instant.now());
        favorite.setDeleted(true);
        Favorite result = favoriteService.save(favorite);
        return ResponseEntity.created(new URI("/api/favorites/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /favorites : Updates an existing favorite.
     *
     * @param favorite the favorite to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated favorite,
     * or with status 400 (Bad Request) if the favorite is not valid,
     * or with status 500 (Internal Server Error) if the favorite couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/favorites")
    @Timed
    public ResponseEntity<Favorite> updateFavorite(@RequestBody Favorite favorite) throws URISyntaxException {
        log.debug("REST request to update Favorite : {}", favorite);
        if (favorite.getId() == null) {
            return createFavorite(favorite);
        }
        /**
         * 设置修改时间
         */
        favorite.setModify_time(Instant.now());
        Favorite result = favoriteService.save(favorite);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, favorite.getId().toString()))
            .body(result);
    }

    /**
     * GET  /favorites : get all the favorites.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of favorites in body
     */
    @GetMapping("/favorites")
    @Timed
    public ResponseEntity<List<Favorite>> getAllFavorites(Pageable pageable) {
        log.debug("REST request to get a page of Favorites");
        Page<Favorite> page = favoriteService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/favorites");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /favorites/:id : get the "id" favorite.
     *
     * @param id the id of the favorite to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the favorite, or with status 404 (Not Found)
     */
    @GetMapping("/favorites/{id}")
    @Timed
    public ResponseEntity<Favorite> getFavorite(@PathVariable Long id) {
        log.debug("REST request to get Favorite : {}", id);
        Favorite favorite = favoriteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(favorite));
    }

    /**
     * DELETE  /favorites/:id : delete the "id" favorite.
     * 重写物理删除       
     * @param id the id of the favorite to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/favorites/{id}")
    @Timed
    public ResponseEntity<Void> deleteFavorite(@PathVariable Long id) {
        log.debug("REST request to delete Favorite : {}", id);
        favoriteService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    /**
     * 收藏商品
     */
    @GetMapping("/favProduct/{proid}/{type}")
    @Timed
    public ResponseEntity<Boolean> creFavorite(@PathVariable String proid,@PathVariable String type){
    	/**
    	 * 查询数据，如果没有就更新，
    	 * 如果有就直接更新 设置修改时间，和取相反值
    	 * 如果没有就直接添加
    	 */
    	String userid = SecurityUtils.getCurrentUserLogin().orElse(Constants.SYSTEM_ACCOUNT);
    	Favorite findByPidAndType = favoriteService.findByPidAndType(proid,type,"1");
    	if(findByPidAndType==null){
    		
    		Favorite favorite = new Favorite();
    		favorite.setCreate_time(Instant.now());
    		favorite.setDeleted(false);
    		favorite.setTarget_id(proid);
    		favorite.setTarget_type(type);
    		favorite.setUserid("1");
    		favoriteService.save(favorite);
    		return ResponseEntity.ok().body(true);
    	}else{
    		findByPidAndType.setModify_time(Instant.now());;
    		Boolean flag =findByPidAndType.isDeleted();
    		flag = new Boolean(!flag.booleanValue());
    		findByPidAndType.setDeleted(flag);
    		favoriteService.save(findByPidAndType);
    		//返回true 页面显示取消关注，返回false关注
    		return ResponseEntity.ok().body(flag);
    	}
    }
    @GetMapping("/findFavorite/{type}")
    @Timed
    public ResponseEntity findProFavorite(@PathVariable String type) throws Exception{
    	String userid = SecurityUtils.getCurrentUserLogin().orElse(Constants.SYSTEM_ACCOUNT);
    	List<String> findByType = favoriteService.findByType(type,"1");
    	System.out.println(findByType);
    	String join = StringUtils.join(findByType,",");
    	System.out.println(join);
    	RestTemplate restTemplate = new RestTemplate();
    	ResponseEntity<List> responseEntity = restTemplate.getForEntity("http://192.168.1.96:8116/api/products?id.in="+join, List.class);
    	return responseEntity;
//    	return new ResponseEntity<>(pro,HttpStatus.OK);
    	//List pro = feignProductClient.getPro(findByType);
    	/*JSONArray jsonArray = JSONArray.fromObject(findByType);   	
    	List pro = feignProductClient.getPro(jsonArray);*/
//    	List body = responseEntity.getBody();
    }  
}  

