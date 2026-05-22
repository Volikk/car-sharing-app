package com.example.demo.mapper;

import com.example.demo.dto.PaymentResponseDto;
import com.example.demo.model.Payment;
import com.example.demo.model.Rental;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-22T21:07:33+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.4 (Oracle Corporation)"
)
@Component
public class PaymentMapperImpl implements PaymentMapper {

    @Override
    public PaymentResponseDto toDto(Payment payment) {
        if ( payment == null ) {
            return null;
        }

        PaymentResponseDto paymentResponseDto = new PaymentResponseDto();

        paymentResponseDto.setRentalId( paymentRentalId( payment ) );
        paymentResponseDto.setId( payment.getId() );
        if ( payment.getStatus() != null ) {
            paymentResponseDto.setStatus( payment.getStatus().name() );
        }
        if ( payment.getType() != null ) {
            paymentResponseDto.setType( payment.getType().name() );
        }
        paymentResponseDto.setSessionUrl( payment.getSessionUrl() );
        paymentResponseDto.setSessionId( payment.getSessionId() );
        paymentResponseDto.setAmount( payment.getAmount() );

        return paymentResponseDto;
    }

    private Long paymentRentalId(Payment payment) {
        Rental rental = payment.getRental();
        if ( rental == null ) {
            return null;
        }
        return rental.getId();
    }
}
