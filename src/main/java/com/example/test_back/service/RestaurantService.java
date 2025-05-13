package com.example.test_back.service;

import com.example.test_back.dto.request.PostRestaurantRequestDto;
import com.example.test_back.dto.response.PostRestaurantResponseDto;
import com.example.test_back.dto.response.ResponseDto;
import com.example.test_back.dto.response.RestaurantResponseDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public interface RestaurantService {
    ResponseDto<PostRestaurantResponseDto> createRestaurant(@Valid PostRestaurantRequestDto dto);
    ResponseDto<RestaurantResponseDto> getRestaurantById(Long id);
    ResponseDto<List<RestaurantResponseDto>> getAllRestaurants();
    ResponseDto<RestaurantResponseDto> updateRestaurant(Long id, @Valid PostRestaurantRequestDto dto);
    ResponseDto<Void> deleteRestaurant(Long id);
    ResponseDto<List<RestaurantResponseDto>> searchRestaurantByAddress(String keyword);
}
