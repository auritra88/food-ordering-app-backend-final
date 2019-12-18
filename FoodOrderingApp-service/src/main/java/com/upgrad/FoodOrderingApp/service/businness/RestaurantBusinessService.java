package com.upgrad.FoodOrderingApp.service.businness;


import com.upgrad.FoodOrderingApp.service.dao.RestaurantDao;
import com.upgrad.FoodOrderingApp.service.entity.RestaurantEntity;
import com.upgrad.FoodOrderingApp.service.entity.RestaurantCategoryEntity;
import com.upgrad.FoodOrderingApp.service.entity.CategoryEntity;
import com.upgrad.FoodOrderingApp.service.exception.AuthorizationFailedException;
import com.upgrad.FoodOrderingApp.service.exception.RestaurantNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class RestaurantBusinessService {

    @Autowired
    private RestaurantDao restaurantDAO;

    @Autowired
    private RestaurantCategoryBusinessService restaurantCategoryBusinessService;

    @Autowired
    private CategoryBusinessService categoryBusinessService;


    public List<RestaurantEntity> getAllRestaurants(){
        List<RestaurantEntity> allRestaurants = restaurantDAO.getAllRestaurants();

        return allRestaurants;
    }

    public String getCategoriesLinkedToRestaurant(Integer restaurantId){
        String categoryList = "";

        List<RestaurantCategoryEntity> allRestaurantCategories = restaurantCategoryBusinessService.getAllRestaurantsCategories(restaurantId);

        for(RestaurantCategoryEntity restaurantCategoryEntity : allRestaurantCategories){
            List<CategoryEntity> allCategories = categoryBusinessService.getAllCategoriesBasedCatId(restaurantCategoryEntity.getCategoryId());
            for(CategoryEntity categoryEntity : allCategories){
                if(categoryList.equals("")) {
                    categoryList = categoryEntity.getCategory_name();
                }else {
                    categoryList = categoryList + ", " + categoryEntity.getCategory_name();
                }
            }
        }

        return categoryList;
    }


}
