package com.eyun.favorite.web.rest;

import com.eyun.favorite.FavoriteApp;

import com.eyun.favorite.config.SecurityBeanOverrideConfiguration;

import com.eyun.favorite.domain.Favorite;
import com.eyun.favorite.repository.FavoriteRepository;
import com.eyun.favorite.service.FavoriteService;
import com.eyun.favorite.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.eyun.favorite.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the FavoriteResource REST controller.
 *
 * @see FavoriteResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FavoriteApp.class, SecurityBeanOverrideConfiguration.class})
public class FavoriteResourceIntTest {

    private static final String DEFAULT_USERID = "AAAAAAAAAA";
    private static final String UPDATED_USERID = "BBBBBBBBBB";

    private static final String DEFAULT_TARGET_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TARGET_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_TARGET_ID = "AAAAAAAAAA";
    private static final String UPDATED_TARGET_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TARGET_EXTEND = "AAAAAAAAAA";
    private static final String UPDATED_TARGET_EXTEND = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_MODIFY_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MODIFY_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_DELETED = false;
    private static final Boolean UPDATED_DELETED = true;

    private static final String DEFAULT_DESC = "AAAAAAAAAA";
    private static final String UPDATED_DESC = "BBBBBBBBBB";

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFavoriteMockMvc;

    private Favorite favorite;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FavoriteResource favoriteResource = new FavoriteResource(favoriteService);
        this.restFavoriteMockMvc = MockMvcBuilders.standaloneSetup(favoriteResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Favorite createEntity(EntityManager em) {
        Favorite favorite = new Favorite()
            .userid(DEFAULT_USERID)
            .target_type(DEFAULT_TARGET_TYPE)
            .target_id(DEFAULT_TARGET_ID)
            .target_extend(DEFAULT_TARGET_EXTEND)
            .create_time(DEFAULT_CREATE_TIME)
            .modify_time(DEFAULT_MODIFY_TIME)
            .deleted(DEFAULT_DELETED)
            .desc(DEFAULT_DESC);
        return favorite;
    }

    @Before
    public void initTest() {
        favorite = createEntity(em);
    }

    @Test
    @Transactional
    public void createFavorite() throws Exception {
        int databaseSizeBeforeCreate = favoriteRepository.findAll().size();

        // Create the Favorite
        restFavoriteMockMvc.perform(post("/api/favorites")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(favorite)))
            .andExpect(status().isCreated());

        // Validate the Favorite in the database
        List<Favorite> favoriteList = favoriteRepository.findAll();
        assertThat(favoriteList).hasSize(databaseSizeBeforeCreate + 1);
        Favorite testFavorite = favoriteList.get(favoriteList.size() - 1);
        assertThat(testFavorite.getUserid()).isEqualTo(DEFAULT_USERID);
        assertThat(testFavorite.getTarget_type()).isEqualTo(DEFAULT_TARGET_TYPE);
        assertThat(testFavorite.getTarget_id()).isEqualTo(DEFAULT_TARGET_ID);
        assertThat(testFavorite.getTarget_extend()).isEqualTo(DEFAULT_TARGET_EXTEND);
        assertThat(testFavorite.getCreate_time()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testFavorite.getModify_time()).isEqualTo(DEFAULT_MODIFY_TIME);
        assertThat(testFavorite.isDeleted()).isEqualTo(DEFAULT_DELETED);
        assertThat(testFavorite.getDesc()).isEqualTo(DEFAULT_DESC);
    }

    @Test
    @Transactional
    public void createFavoriteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = favoriteRepository.findAll().size();

        // Create the Favorite with an existing ID
        favorite.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFavoriteMockMvc.perform(post("/api/favorites")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(favorite)))
            .andExpect(status().isBadRequest());

        // Validate the Favorite in the database
        List<Favorite> favoriteList = favoriteRepository.findAll();
        assertThat(favoriteList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllFavorites() throws Exception {
        // Initialize the database
        favoriteRepository.saveAndFlush(favorite);

        // Get all the favoriteList
        restFavoriteMockMvc.perform(get("/api/favorites?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(favorite.getId().intValue())))
            .andExpect(jsonPath("$.[*].userid").value(hasItem(DEFAULT_USERID.toString())))
            .andExpect(jsonPath("$.[*].target_type").value(hasItem(DEFAULT_TARGET_TYPE.toString())))
            .andExpect(jsonPath("$.[*].target_id").value(hasItem(DEFAULT_TARGET_ID.toString())))
            .andExpect(jsonPath("$.[*].target_extend").value(hasItem(DEFAULT_TARGET_EXTEND.toString())))
            .andExpect(jsonPath("$.[*].create_time").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].modify_time").value(hasItem(DEFAULT_MODIFY_TIME.toString())))
            .andExpect(jsonPath("$.[*].deleted").value(hasItem(DEFAULT_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].desc").value(hasItem(DEFAULT_DESC.toString())));
    }

    @Test
    @Transactional
    public void getFavorite() throws Exception {
        // Initialize the database
        favoriteRepository.saveAndFlush(favorite);

        // Get the favorite
        restFavoriteMockMvc.perform(get("/api/favorites/{id}", favorite.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(favorite.getId().intValue()))
            .andExpect(jsonPath("$.userid").value(DEFAULT_USERID.toString()))
            .andExpect(jsonPath("$.target_type").value(DEFAULT_TARGET_TYPE.toString()))
            .andExpect(jsonPath("$.target_id").value(DEFAULT_TARGET_ID.toString()))
            .andExpect(jsonPath("$.target_extend").value(DEFAULT_TARGET_EXTEND.toString()))
            .andExpect(jsonPath("$.create_time").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.modify_time").value(DEFAULT_MODIFY_TIME.toString()))
            .andExpect(jsonPath("$.deleted").value(DEFAULT_DELETED.booleanValue()))
            .andExpect(jsonPath("$.desc").value(DEFAULT_DESC.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFavorite() throws Exception {
        // Get the favorite
        restFavoriteMockMvc.perform(get("/api/favorites/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFavorite() throws Exception {
        // Initialize the database
        favoriteService.save(favorite);

        int databaseSizeBeforeUpdate = favoriteRepository.findAll().size();

        // Update the favorite
        Favorite updatedFavorite = favoriteRepository.findOne(favorite.getId());
        // Disconnect from session so that the updates on updatedFavorite are not directly saved in db
        em.detach(updatedFavorite);
        updatedFavorite
            .userid(UPDATED_USERID)
            .target_type(UPDATED_TARGET_TYPE)
            .target_id(UPDATED_TARGET_ID)
            .target_extend(UPDATED_TARGET_EXTEND)
            .create_time(UPDATED_CREATE_TIME)
            .modify_time(UPDATED_MODIFY_TIME)
            .deleted(UPDATED_DELETED)
            .desc(UPDATED_DESC);

        restFavoriteMockMvc.perform(put("/api/favorites")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedFavorite)))
            .andExpect(status().isOk());

        // Validate the Favorite in the database
        List<Favorite> favoriteList = favoriteRepository.findAll();
        assertThat(favoriteList).hasSize(databaseSizeBeforeUpdate);
        Favorite testFavorite = favoriteList.get(favoriteList.size() - 1);
        assertThat(testFavorite.getUserid()).isEqualTo(UPDATED_USERID);
        assertThat(testFavorite.getTarget_type()).isEqualTo(UPDATED_TARGET_TYPE);
        assertThat(testFavorite.getTarget_id()).isEqualTo(UPDATED_TARGET_ID);
        assertThat(testFavorite.getTarget_extend()).isEqualTo(UPDATED_TARGET_EXTEND);
        assertThat(testFavorite.getCreate_time()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testFavorite.getModify_time()).isEqualTo(UPDATED_MODIFY_TIME);
        assertThat(testFavorite.isDeleted()).isEqualTo(UPDATED_DELETED);
        assertThat(testFavorite.getDesc()).isEqualTo(UPDATED_DESC);
    }

    @Test
    @Transactional
    public void updateNonExistingFavorite() throws Exception {
        int databaseSizeBeforeUpdate = favoriteRepository.findAll().size();

        // Create the Favorite

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restFavoriteMockMvc.perform(put("/api/favorites")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(favorite)))
            .andExpect(status().isCreated());

        // Validate the Favorite in the database
        List<Favorite> favoriteList = favoriteRepository.findAll();
        assertThat(favoriteList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteFavorite() throws Exception {
        // Initialize the database
        favoriteService.save(favorite);

        int databaseSizeBeforeDelete = favoriteRepository.findAll().size();

        // Get the favorite
        restFavoriteMockMvc.perform(delete("/api/favorites/{id}", favorite.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Favorite> favoriteList = favoriteRepository.findAll();
        assertThat(favoriteList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Favorite.class);
        Favorite favorite1 = new Favorite();
        favorite1.setId(1L);
        Favorite favorite2 = new Favorite();
        favorite2.setId(favorite1.getId());
        assertThat(favorite1).isEqualTo(favorite2);
        favorite2.setId(2L);
        assertThat(favorite1).isNotEqualTo(favorite2);
        favorite1.setId(null);
        assertThat(favorite1).isNotEqualTo(favorite2);
    }
}
