package com.example.test_back.controller;

import com.example.test_back.common.ApiMappingPattern;
import com.example.test_back.dto.request.PostRestaurantRequestDto;
import com.example.test_back.dto.response.PostRestaurantResponseDto;
import com.example.test_back.dto.response.ResponseDto;
import com.example.test_back.dto.response.RestaurantResponseDto;
import com.example.test_back.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.RESTAURANT_API)
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    // 레스토랑 생성
    @PostMapping
    public ResponseEntity<ResponseDto<PostRestaurantResponseDto>> createRestaurant(@Valid @RequestBody PostRestaurantRequestDto dto) {
        ResponseDto<PostRestaurantResponseDto> restaurant = restaurantService.createRestaurant(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurant);
    }

    // 단일 레스토랑 조회
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<RestaurantResponseDto>> getRestaurantById(@PathVariable Long id) {
        ResponseDto<RestaurantResponseDto> restaurant = restaurantService.getRestaurantById(id);
        return ResponseEntity.status(HttpStatus.OK).body(restaurant);
    }

    // 목록 조회 (페이징...??)
    @GetMapping
    public ResponseEntity<ResponseDto<List<RestaurantResponseDto>>> getAllRestaurants() {


        ResponseDto<List<RestaurantResponseDto>> restaurants = restaurantService.getAllRestaurants();
        return ResponseEntity.status(HttpStatus.OK).body(restaurants);
    }

    // 정보 수정
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<RestaurantResponseDto>> updateRestaurant(
            @PathVariable Long id,
            @Valid @RequestBody PostRestaurantRequestDto dto
    ) {
        ResponseDto<RestaurantResponseDto> response = restaurantService.updateRestaurant(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 삭제 (하위 메뉴 포함)
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<Void>> deleteRestaurant(@PathVariable Long id) {
        ResponseDto<Void> response = restaurantService.deleteRestaurant(id);
        return ResponseEntity.noContent().build();
    }

    // 특정 지역에 위치한 레스토랑 조회
    @GetMapping("/search")
    public ResponseEntity<ResponseDto<List<RestaurantResponseDto>>> searchRestaurantByAddress(@RequestParam String keyword) {
        ResponseDto<List<RestaurantResponseDto>> response = restaurantService.searchRestaurantByAddress(keyword);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
