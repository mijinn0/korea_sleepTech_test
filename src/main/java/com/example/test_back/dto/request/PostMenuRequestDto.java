package com.example.test_back.dto.request;

import com.example.test_back.dto.response.RestaurantResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostMenuRequestDto {
    private String name;
    private double price;
    private String description;
    private Long restaurantId;
}
