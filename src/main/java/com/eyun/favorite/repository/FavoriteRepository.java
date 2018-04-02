package com.eyun.favorite.repository;

import com.eyun.favorite.domain.Favorite;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Favorite entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> 
									, JpaSpecificationExecutor<Favorite>{

}
