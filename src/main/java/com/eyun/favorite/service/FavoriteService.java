package com.eyun.favorite.service;

import com.eyun.favorite.domain.Favorite;
import com.eyun.favorite.repository.FavoriteRepository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Favorite.
 */
@Service
@Transactional
public class FavoriteService {

    private final Logger log = LoggerFactory.getLogger(FavoriteService.class);

    private final FavoriteRepository favoriteRepository;
    
    public FavoriteService(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    /**
     * Save a favorite.
     *
     * @param favorite the entity to save
     * @return the persisted entity
     */
    public Favorite save(Favorite favorite) {
        log.debug("Request to save Favorite : {}", favorite);
        return favoriteRepository.save(favorite);
    }

    /**
     * Get all the favorites.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Favorite> findAll(Pageable pageable) {
        log.debug("Request to get all Favorites");
        return favoriteRepository.findAll(pageable);
    }

    /**
     * Get one favorite by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Favorite findOne(Long id) {
        log.debug("Request to get Favorite : {}", id);
        return favoriteRepository.findOne(id);
    }

    /**
     * Delete the favorite by id.
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Favorite : {}", id);
        /**
         * 重写删除方法(取消关注)
         */
        Favorite favorite = favoriteRepository.findOne(id);
        favorite.setDeleted(true);
        favorite.setModify_time(Instant.now());
        favoriteRepository.save(favorite);
        return ;
    }
    


	public Favorite findByPidAndType(String skuid, String type, Long long1) {
		Favorite favorite = favoriteRepository.findOne(new Specification<Favorite>(){
			@Override
			public Predicate toPredicate(Root<Favorite> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate p1 = cb.equal(root.get("target_type"), type);
				Predicate p2 = cb.equal(root.get("target_id"),skuid);
				Predicate p3 = cb.equal(root.get("userid"), long1);
				Predicate p4 = cb.equal(root.get("deleted"),false);
				query.where(cb.and(p1,p2,p3));
				return null;
			}
			
		});
		
		return favorite;
	} 
   
	/**
	 * 根据type得到产品或者店铺的所有id集合
	 * @param type
	 * @param long1
	 * @return
	 */
	public List<String> findByType(String type,Long long1){
		  List<Favorite> findAll = favoriteRepository.findAll(new Specification<Favorite>(){

			@Override
			public Predicate toPredicate(Root<Favorite> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate p1 = cb.equal(root.get("target_type"),type);
				Predicate p2 = cb.equal(root.get("userid"), long1);
				Predicate p3 = cb.equal(root.get("deleted"), false);
				query.where(cb.and(p1,p2,p3));
				return null;
			}
		});
		  
		List<String> idList = new ArrayList<String>();
		for (Favorite favorite : findAll) {
			idList.add(favorite.getTarget_id());
		}
		return idList;
		
	}
    
    
}
