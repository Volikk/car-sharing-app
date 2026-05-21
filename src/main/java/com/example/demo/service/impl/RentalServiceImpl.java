package com.example.demo.service.impl;

import com.example.demo.dto.RentalRequestDto;
import com.example.demo.dto.RentalResponseDto;
import com.example.demo.mapper.RentalMapper;
import com.example.demo.model.Car;
import com.example.demo.model.Rental;
import com.example.demo.model.User;
import com.example.demo.repository.CarRepository;
import com.example.demo.repository.RentalRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.RentalService;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {
    private final RentalRepository rentalRepository;
    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final RentalMapper rentalMapper;

    @Override
    @Transactional
    public RentalResponseDto add(RentalRequestDto requestDto, String email) {
        Car car = carRepository.findById(requestDto.getCarId())
                .orElseThrow(() -> new EntityNotFoundException("Can't find car by id: "
                        + requestDto.getCarId()));

        if (car.getInventory() <= 0) {
            throw new IllegalArgumentException("This car is currently unavailable for rent.");
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Can't find user by email: " + email));

        car.setInventory(car.getInventory() - 1);
        carRepository.save(car);

        Rental rental = new Rental();
        rental.setRentalDate(LocalDateTime.now());
        rental.setReturnDate(requestDto.getReturnDate());
        rental.setCar(car);
        rental.setUser(user);

        return rentalMapper.toDto(rentalRepository.save(rental));
    }

    @Override
    @Transactional
    public RentalResponseDto returnCar(Long id) {
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find rental by id: " + id));

        if (rental.getActualReturnDate() != null) {
            throw new IllegalArgumentException("This rental has already been returned.");
        }

        rental.setActualReturnDate(LocalDateTime.now());

        Car car = rental.getCar();
        car.setInventory(car.getInventory() + 1);
        carRepository.save(car);

        return rentalMapper.toDto(rentalRepository.save(rental));
    }
}
