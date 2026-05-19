package com.example.demo.mapper;

import com.example.demo.dto.UserRegistrationRequestDto;
import com.example.demo.dto.UserResponseDto;
import com.example.demo.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-19T12:29:10+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.4 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponseDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDto userResponseDto = new UserResponseDto();

        if ( user.getId() != null ) {
            userResponseDto.setId( user.getId() );
        }
        if ( user.getEmail() != null ) {
            userResponseDto.setEmail( user.getEmail() );
        }
        if ( user.getFirstName() != null ) {
            userResponseDto.setFirstName( user.getFirstName() );
        }
        if ( user.getLastName() != null ) {
            userResponseDto.setLastName( user.getLastName() );
        }
        if ( user.getRole() != null ) {
            userResponseDto.setRole( user.getRole() );
        }

        return userResponseDto;
    }

    @Override
    public User toEntity(UserRegistrationRequestDto requestDto) {
        if ( requestDto == null ) {
            return null;
        }

        User user = new User();

        if ( requestDto.getEmail() != null ) {
            user.setEmail( requestDto.getEmail() );
        }
        if ( requestDto.getFirstName() != null ) {
            user.setFirstName( requestDto.getFirstName() );
        }
        if ( requestDto.getLastName() != null ) {
            user.setLastName( requestDto.getLastName() );
        }
        if ( requestDto.getPassword() != null ) {
            user.setPassword( requestDto.getPassword() );
        }

        return user;
    }
}
