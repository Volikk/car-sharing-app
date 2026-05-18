package com.example.demo.service;

import com.example.demo.dto.CarDto;
import com.example.demo.dto.CreateCarRequestDto;
import java.util.List;

public interface CarService {
    CarDto save(CreateCarRequestDto requestDto);

    List<CarDto> findAll();

    CarDto findById(Long id);

    CarDto update(Long id, CreateCarRequestDto requestDto);

    void deleteById(Long id);
}
