package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.RestaurantEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class RestaurantDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<RestaurantEntity> getAllRestaurants(){
        try{
            return entityManager.createNamedQuery("getAllRestaurants", RestaurantEntity.class).getResultList();
        } catch (NoResultException nre){
            return null;
        }
    }

    public List<RestaurantEntity> getAllRestaurantsByName(String resNameKey){
        try{
            return entityManager.createNamedQuery("getAllRestaurantsByName", RestaurantEntity.class).setParameter("resNameKey", resNameKey).getResultList();
        } catch (NoResultException nre){
            return null;
        }
    }

}
