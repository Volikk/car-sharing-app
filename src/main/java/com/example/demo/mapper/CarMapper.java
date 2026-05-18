package com.example.demo.mapper;

import com.example.demo.dto.CarDto;
import com.example.demo.dto.CreateCarRequestDto;
import com.example.demo.model.Car;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.InjectionStrategy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = org.mapstruct.NullValueCheckStrategy.ALWAYS
)
public interface CarMapper {
    CarDto toDto(Car car);

    Car toEntity(CreateCarRequestDto requestDto);

    void updateCarFromDto(CreateCarRequestDto requestDto, @MappingTarget Car car);
}
