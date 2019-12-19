package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.CustomerAuthTokenEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class CustomerDao {

    @PersistenceContext
    EntityManager entityManager;

    public CustomerAuthTokenEntity checkAuthToken(String accessToken){
        try {
            return entityManager.createNamedQuery("getToken", CustomerAuthTokenEntity.class).setParameter("accessToken", accessToken).
                    getSingleResult();
        }catch(NoResultException nre){
            return null;
        }
    }
}
