package com.eyun.favorite.service;

import com.eyun.favorite.domain.Favorite;
import com.eyun.favorite.repository.FavoriteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Favorite : {}", id);
        favoriteRepository.delete(id);
    }
}
