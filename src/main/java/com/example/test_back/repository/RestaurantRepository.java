package com.example.test_back.repository;

import com.example.test_back.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findByAddressIgnoreCaseContaining(String keyword);
}
