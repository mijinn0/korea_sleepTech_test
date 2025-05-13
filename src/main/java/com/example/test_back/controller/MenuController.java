package com.example.test_back.controller;

import com.example.test_back.common.ApiMappingPattern;
import com.example.test_back.dto.request.PostMenuRequestDto;
import com.example.test_back.dto.request.PostRestaurantRequestDto;
import com.example.test_back.dto.response.MenuResponseDto;
import com.example.test_back.dto.response.PostMenuResponseDto;
import com.example.test_back.dto.response.ResponseDto;
import com.example.test_back.service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.MENU_API)
@RequiredArgsConstructor
public class MenuController {

    public final MenuService menuService;

    // 메뉴 추가 (특정 레스토랑)
    @PostMapping
    public ResponseEntity<ResponseDto<PostMenuResponseDto>> createMenu(@Valid @RequestBody PostMenuRequestDto dto
    ) {
        ResponseDto<PostMenuResponseDto> response = menuService.createMenu(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 모든 메뉴 조회 (특정 레스토랑)
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<ResponseDto<List<MenuResponseDto>>> getMenuByRestaurantId(@PathVariable Long restaurantId) {
        ResponseDto<List<MenuResponseDto>> response = menuService.getMenuByRestaurantId(restaurantId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 메뉴 단건 조회 (ID)
    @GetMapping("/{menuId}")
    public ResponseEntity<ResponseDto<MenuResponseDto>> getMenuById(@PathVariable Long menuId) {
        ResponseDto<MenuResponseDto> response = menuService.getMenuById(menuId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 메뉴 수정
    @PutMapping("/{menuId}")
    public ResponseEntity<ResponseDto<MenuResponseDto>> updateMenu(
            @PathVariable Long menuId,
            @Valid @RequestBody PostMenuRequestDto dto
    ) {
        ResponseDto<MenuResponseDto> response = menuService.updateMenu(menuId, dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 메뉴 개별 삭제
    @DeleteMapping("/{menuId}")
    public ResponseEntity<ResponseDto<Void>> deleteMenu(
            @PathVariable Long menuId
    ) {
        ResponseDto<Void> response = menuService.deleteComment(menuId);
        return ResponseEntity.noContent().build();
    }

    // 특정 가격 범위의 메뉴 조회
}
