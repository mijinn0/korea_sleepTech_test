package com.example.test_back.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantResponseDto {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
}
