package com.example.demo.mapper;

import com.example.demo.dto.RentalResponseDto;
import com.example.demo.model.Car;
import com.example.demo.model.Rental;
import com.example.demo.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-22T21:07:33+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.4 (Oracle Corporation)"
)
@Component
public class RentalMapperImpl implements RentalMapper {

    @Override
    public RentalResponseDto toDto(Rental rental) {
        if ( rental == null ) {
            return null;
        }

        RentalResponseDto rentalResponseDto = new RentalResponseDto();

        rentalResponseDto.setCarId( rentalCarId( rental ) );
        rentalResponseDto.setUserId( rentalUserId( rental ) );
        rentalResponseDto.setId( rental.getId() );
        rentalResponseDto.setRentalDate( rental.getRentalDate() );
        rentalResponseDto.setReturnDate( rental.getReturnDate() );
        rentalResponseDto.setActualReturnDate( rental.getActualReturnDate() );

        return rentalResponseDto;
    }

    private Long rentalCarId(Rental rental) {
        Car car = rental.getCar();
        if ( car == null ) {
            return null;
        }
        return car.getId();
    }

    private Long rentalUserId(Rental rental) {
        User user = rental.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getId();
    }
}
