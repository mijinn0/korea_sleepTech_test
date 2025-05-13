package com.example.test_back.service;

import com.example.test_back.dto.request.PostMenuRequestDto;
import com.example.test_back.dto.response.MenuResponseDto;
import com.example.test_back.dto.response.PostMenuResponseDto;
import com.example.test_back.dto.response.ResponseDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface MenuService {
    ResponseDto<PostMenuResponseDto> createMenu(@Valid PostMenuRequestDto dto);
    ResponseDto<List<MenuResponseDto>> getMenuByRestaurantId(Long restaurantId);
    ResponseDto<MenuResponseDto> getMenuById(Long id);
    ResponseDto<MenuResponseDto> updateMenu(Long menuId, @Valid PostMenuRequestDto dto);
    ResponseDto<Void> deleteComment(Long menuId);
}
