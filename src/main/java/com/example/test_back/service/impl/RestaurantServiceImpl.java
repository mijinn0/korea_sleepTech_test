package com.example.test_back.service.impl;

import com.example.test_back.common.ResponseMessage;
import com.example.test_back.dto.request.PostRestaurantRequestDto;
import com.example.test_back.dto.response.PostRestaurantResponseDto;
import com.example.test_back.dto.response.ResponseDto;
import com.example.test_back.dto.response.RestaurantResponseDto;
import com.example.test_back.entity.Restaurant;
import com.example.test_back.repository.RestaurantRepository;
import com.example.test_back.service.RestaurantService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Override
    @Transactional
    public ResponseDto<PostRestaurantResponseDto> createRestaurant(PostRestaurantRequestDto dto) {
        PostRestaurantResponseDto responseDto = null;

        Restaurant newRestaurant = Restaurant.builder()
                .name(dto.getName())
                .address(dto.getAddress())
                .phoneNumber(dto.getPhoneNumber())
                .build();

        Restaurant saved = restaurantRepository.save(newRestaurant);

        responseDto = PostRestaurantResponseDto.builder()
                .id(saved.getId())
                .name(saved.getName())
                .address(saved.getAddress())
                .phoneNumber(saved.getPhoneNumber())
                .build();

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<RestaurantResponseDto> getRestaurantById(Long id) {
        RestaurantResponseDto responseDto = null;

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_RESTAURANT + id));

        responseDto = RestaurantResponseDto.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .phoneNumber(restaurant.getPhoneNumber())
                .build();

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<List<RestaurantResponseDto>> getAllRestaurants() {
        List<RestaurantResponseDto> responseDtos = null;

        List<Restaurant> restaurants = restaurantRepository.findAll();

        responseDtos = restaurants.stream()
                .map(restaurant -> RestaurantResponseDto.builder()
                        .id(restaurant.getId())
                        .name(restaurant.getName())
                        .address(restaurant.getAddress())
                        .phoneNumber(restaurant.getPhoneNumber())
                        .build())
                .collect(Collectors.toList());

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDtos);
    }

    @Override
    @Transactional
    public ResponseDto<RestaurantResponseDto> updateRestaurant(Long id, PostRestaurantRequestDto dto) {
        RestaurantResponseDto responseDto = null;

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_RESTAURANT + id));

        restaurant.setName(dto.getName());
        restaurant.setAddress(dto.getAddress());
        restaurant.setPhoneNumber(dto.getPhoneNumber());

        Restaurant updatedRestaurant = restaurantRepository.save(restaurant);

        responseDto = RestaurantResponseDto.builder()
                .id(updatedRestaurant.getId())
                .name(updatedRestaurant.getName())
                .address(updatedRestaurant.getAddress())
                .phoneNumber(updatedRestaurant.getPhoneNumber())
                .build();

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
    }

    @Override
    @Transactional
    public ResponseDto<Void> deleteRestaurant(Long id) {

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_RESTAURANT + id));

        restaurant.getMenus().forEach(restaurant::removeMenu);

        restaurantRepository.deleteById(id);

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<List<RestaurantResponseDto>> searchRestaurantByAddress(String keyword) {
        List<RestaurantResponseDto> responseDtos = null;

        List<Restaurant> restaurants = restaurantRepository.findByAddressIgnoreCaseContaining(keyword);

        responseDtos = restaurants.stream()
                .map(restaurant -> RestaurantResponseDto.builder()
                        .id(restaurant.getId())
                        .name(restaurant.getName())
                        .address(restaurant.getAddress())
                        .phoneNumber(restaurant.getPhoneNumber())
                        .build())
                .collect(Collectors.toList());

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDtos);
    }


}
