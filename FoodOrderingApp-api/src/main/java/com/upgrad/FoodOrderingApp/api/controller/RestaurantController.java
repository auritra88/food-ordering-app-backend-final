package com.upgrad.FoodOrderingApp.api.controller;


import com.upgrad.FoodOrderingApp.api.model.*;
import com.upgrad.FoodOrderingApp.service.businness.CategoryBusinessService;
import com.upgrad.FoodOrderingApp.service.businness.RestaurantBusinessService;

import com.upgrad.FoodOrderingApp.service.entity.RestaurantEntity;
import com.upgrad.FoodOrderingApp.service.entity.CategoryEntity;
import com.upgrad.FoodOrderingApp.service.exception.RestaurantNotFoundException;
import com.upgrad.FoodOrderingApp.service.exception.CategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;




@RestController
@RequestMapping("/")
@CrossOrigin
public class RestaurantController {

    @Autowired
    private RestaurantBusinessService restaurantBusinessService;

    @Autowired
    private CategoryBusinessService categoryBusinessService;



    @RequestMapping(method = RequestMethod.GET,path = "/restaurant",produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
    public ResponseEntity<List<RestaurantListResponse>> getAllRestaurants()  {

        List<RestaurantListResponse> restList = new ArrayList<>();
        final List<RestaurantEntity> allRestaurants = restaurantBusinessService.getAllRestaurants();

        for(RestaurantEntity restaurantEntity : allRestaurants){
            String allCategoriesLinkedToRestaurant = restaurantBusinessService.getCategoriesLinkedToRestaurant(restaurantEntity.getId());
            RestaurantList restaurantList = new RestaurantList().id(UUID.fromString(restaurantEntity.getUuid())).restaurantName(restaurantEntity.getRestaurantName()).photoURL(restaurantEntity.getPhotoURL())
                    .customerRating(restaurantEntity.getCustomeRating()).averagePrice(restaurantEntity.getAvgPriceForTwo()).numberCustomersRated(restaurantEntity.getNumbrOfCustomersRated())
                    .address(
                            new RestaurantDetailsResponseAddress().id(UUID.fromString(restaurantEntity.getAddressEntity().getUuid())).city(restaurantEntity.getAddressEntity().getCity()).flatBuildingName(restaurantEntity.getAddressEntity().getFlat_buil_number())
                                    .locality(restaurantEntity.getAddressEntity().getLocality()).pincode(restaurantEntity.getAddressEntity().getPincode())
                                    .state(new RestaurantDetailsResponseAddressState().id(UUID.fromString(restaurantEntity.getAddressEntity().getStateEntity().uuid)).stateName(restaurantEntity.getAddressEntity().getStateEntity().getState_name()))

                    )
                    .categories(allCategoriesLinkedToRestaurant);
            RestaurantListResponse restaurantListResponse = new RestaurantListResponse().addRestaurantsItem(restaurantList);
            restList.add(restaurantListResponse);
        }

        return new ResponseEntity<List<RestaurantListResponse>>(restList,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET,path = "/restaurant/name/{reastaurant_name}",produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
    public ResponseEntity<List<RestaurantListResponse>> getAllRestaurantsByName(@PathVariable("reastaurant_name") String restaurantName) throws RestaurantNotFoundException {

        String resNameKey = "%"+restaurantName+"%";
        List<RestaurantListResponse> restList = new ArrayList<>();
        final List<RestaurantEntity> allRestaurantsByName = restaurantBusinessService.getAllRestaurantsByName(resNameKey);

        for(RestaurantEntity restaurantEntity : allRestaurantsByName){
            String allCategoriesLinkedToRestaurant = restaurantBusinessService.getCategoriesLinkedToRestaurant(restaurantEntity.getId());
            RestaurantList restaurantList = new RestaurantList().id(UUID.fromString(restaurantEntity.getUuid())).restaurantName(restaurantEntity.getRestaurantName()).photoURL(restaurantEntity.getPhotoURL())
                    .customerRating(restaurantEntity.getCustomeRating()).averagePrice(restaurantEntity.getAvgPriceForTwo()).numberCustomersRated(restaurantEntity.getNumbrOfCustomersRated())
                    .address(
                            new RestaurantDetailsResponseAddress().id(UUID.fromString(restaurantEntity.getAddressEntity().getUuid())).city(restaurantEntity.getAddressEntity().getCity()).flatBuildingName(restaurantEntity.getAddressEntity().getFlat_buil_number())
                                    .locality(restaurantEntity.getAddressEntity().getLocality()).pincode(restaurantEntity.getAddressEntity().getPincode())
                                    .state(new RestaurantDetailsResponseAddressState().id(UUID.fromString(restaurantEntity.getAddressEntity().getStateEntity().uuid)).stateName(restaurantEntity.getAddressEntity().getStateEntity().getState_name()))

                    )
                    .categories(allCategoriesLinkedToRestaurant);
            RestaurantListResponse restaurantListResponse = new RestaurantListResponse().addRestaurantsItem(restaurantList);
            restList.add(restaurantListResponse);
        }

        return new ResponseEntity<List<RestaurantListResponse>>(restList,HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.GET,path = "/restaurant/category/{category_id}",produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
    public ResponseEntity<List<RestaurantListResponse>> getAllRestaurantsByCategory(@PathVariable("category_id") String categoryUUID) throws CategoryNotFoundException {

        List<RestaurantListResponse> restList = new ArrayList<>();
        CategoryEntity categoryByUUID = categoryBusinessService.getCategoryByUUID(categoryUUID);
        List<RestaurantEntity> allRestaurantsByCategory = restaurantBusinessService.getAllRestaurantsByCategory(categoryByUUID.getId());

        for(RestaurantEntity restaurantEntity : allRestaurantsByCategory){
            String allCategoriesLinkedToRestaurant = restaurantBusinessService.getCategoriesLinkedToRestaurant(restaurantEntity.getId());
            RestaurantList restaurantList = new RestaurantList()
                    .id(UUID.fromString(restaurantEntity.getUuid()))
                    .restaurantName(restaurantEntity.getRestaurantName())
                    .photoURL(restaurantEntity.getPhotoURL())
                    .customerRating(restaurantEntity.getCustomeRating())
                    .averagePrice(restaurantEntity.getAvgPriceForTwo())
                    .numberCustomersRated(restaurantEntity.getNumbrOfCustomersRated())
                    .address(
                            new RestaurantDetailsResponseAddress().id(UUID.fromString(restaurantEntity.getAddressEntity().getUuid())).city(restaurantEntity.getAddressEntity().getCity()).flatBuildingName(restaurantEntity.getAddressEntity().getFlat_buil_number())
                                    .locality(restaurantEntity.getAddressEntity().getLocality()).pincode(restaurantEntity.getAddressEntity().getPincode())
                                    .state(new RestaurantDetailsResponseAddressState().id(UUID.fromString(restaurantEntity.getAddressEntity().getStateEntity().uuid))
                                            .stateName(restaurantEntity.getAddressEntity().getStateEntity().getState_name()))

                    )
                    .categories(allCategoriesLinkedToRestaurant);
            RestaurantListResponse restaurantListResponse = new RestaurantListResponse().addRestaurantsItem(restaurantList);
            restList.add(restaurantListResponse);
        }
        return new ResponseEntity<List<RestaurantListResponse>>(restList,HttpStatus.OK);

    }

}
