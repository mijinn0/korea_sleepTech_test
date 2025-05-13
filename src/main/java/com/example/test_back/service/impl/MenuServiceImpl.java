package com.example.test_back.service.impl;

import com.example.test_back.common.ResponseMessage;
import com.example.test_back.dto.request.PostMenuRequestDto;
import com.example.test_back.dto.response.MenuResponseDto;
import com.example.test_back.dto.response.PostMenuResponseDto;
import com.example.test_back.dto.response.ResponseDto;
import com.example.test_back.entity.Menu;
import com.example.test_back.entity.Restaurant;
import com.example.test_back.repository.MenuRepository;
import com.example.test_back.repository.RestaurantRepository;
import com.example.test_back.service.MenuService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;


    @Override
    @Transactional
    public ResponseDto<PostMenuResponseDto> createMenu(PostMenuRequestDto dto) {
        PostMenuResponseDto responseDto = null;

        Restaurant restaurant = restaurantRepository.findById(dto.getRestaurantId())
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_RESTAURANT + dto.getRestaurantId()));

        Menu newMenu = Menu.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .description(dto.getDescription())
                .build();

        restaurant.addMenu(newMenu);

        Menu savedMenu = menuRepository.save(newMenu);

        responseDto = PostMenuResponseDto.builder()
                .id(savedMenu.getId())
                .name(savedMenu.getName())
                .price(savedMenu.getPrice())
                .description(savedMenu.getDescription())
                .build();

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<List<MenuResponseDto>> getMenuByRestaurantId(Long restaurantId) {
        List<MenuResponseDto> responseDtos = null;

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_RESTAURANT + restaurantId));

        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<MenuResponseDto> getMenuById(Long id) {
        MenuResponseDto responseDto = null;

        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_MENU + id));

        responseDto = MenuResponseDto.builder()
                .id(menu.getId())
                .name(menu.getName())
                .price(menu.getPrice())
                .description(menu.getDescription())
                .build();

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
    }

    @Override
    @Transactional
    public ResponseDto<MenuResponseDto> updateMenu(Long menuId, PostMenuRequestDto dto) {
        MenuResponseDto responseDto = null;

        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_MENU + menuId));

        menu.setName(dto.getName());
        menu.setPrice(dto.getPrice());
        menu.setDescription(dto.getDescription());

        Menu updatedMenu = menuRepository.save(menu);

        responseDto = MenuResponseDto.builder()
                .id(updatedMenu.getId())
                .name(updatedMenu.getName())
                .price(updatedMenu.getPrice())
                .description(updatedMenu.getDescription())
                .build();

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
    }

    @Override
    @Transactional
    public ResponseDto<Void> deleteComment(Long menuId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_MENU + menuId));

        // == 연관 관계를 해제 == //
        menu.getRestaurant().removeMenu(menu);

        // == DB에서 삭제 == //
        menuRepository.delete(menu);

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }

}
