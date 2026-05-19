package com.example.demo.mapper;

import com.example.demo.dto.CarDto;
import com.example.demo.dto.CreateCarRequestDto;
import com.example.demo.model.Car;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-19T12:29:10+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.4 (Oracle Corporation)"
)
@Component
public class CarMapperImpl implements CarMapper {

    @Override
    public CarDto toDto(Car car) {
        if ( car == null ) {
            return null;
        }

        CarDto carDto = new CarDto();

        if ( car.getId() != null ) {
            carDto.setId( car.getId() );
        }
        if ( car.getModel() != null ) {
            carDto.setModel( car.getModel() );
        }
        if ( car.getBrand() != null ) {
            carDto.setBrand( car.getBrand() );
        }
        if ( car.getType() != null ) {
            carDto.setType( car.getType() );
        }
        carDto.setInventory( car.getInventory() );
        if ( car.getDailyFee() != null ) {
            carDto.setDailyFee( car.getDailyFee() );
        }

        return carDto;
    }

    @Override
    public Car toEntity(CreateCarRequestDto requestDto) {
        if ( requestDto == null ) {
            return null;
        }

        Car car = new Car();

        if ( requestDto.getModel() != null ) {
            car.setModel( requestDto.getModel() );
        }
        if ( requestDto.getBrand() != null ) {
            car.setBrand( requestDto.getBrand() );
        }
        if ( requestDto.getType() != null ) {
            car.setType( requestDto.getType() );
        }
        car.setInventory( requestDto.getInventory() );
        if ( requestDto.getDailyFee() != null ) {
            car.setDailyFee( requestDto.getDailyFee() );
        }

        return car;
    }

    @Override
    public void updateCarFromDto(CreateCarRequestDto requestDto, Car car) {
        if ( requestDto == null ) {
            return;
        }

        if ( requestDto.getModel() != null ) {
            car.setModel( requestDto.getModel() );
        }
        else {
            car.setModel( null );
        }
        if ( requestDto.getBrand() != null ) {
            car.setBrand( requestDto.getBrand() );
        }
        else {
            car.setBrand( null );
        }
        if ( requestDto.getType() != null ) {
            car.setType( requestDto.getType() );
        }
        else {
            car.setType( null );
        }
        car.setInventory( requestDto.getInventory() );
        if ( requestDto.getDailyFee() != null ) {
            car.setDailyFee( requestDto.getDailyFee() );
        }
        else {
            car.setDailyFee( null );
        }
    }
}
