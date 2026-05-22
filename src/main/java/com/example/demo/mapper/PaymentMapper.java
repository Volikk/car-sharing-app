package com.example.demo.mapper;

import com.example.demo.dto.PaymentResponseDto;
import com.example.demo.model.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaymentMapper {
    @Mapping(source = "rental.id", target = "rentalId")
    PaymentResponseDto toDto(Payment payment);
}
