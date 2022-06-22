package com.example.model;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Component("convert")
public class Converter {
    public com.example.model.UserRequestDto entityToDto(UserRequest userRequest) {
        if (!(userRequest.getUserName().isEmpty())) {
            ModelMapper mapper = new ModelMapper();
            String[] arrOfStr = userRequest.getUserName().split("_");
            UserRequestDto userRequestDto = new UserRequestDto(arrOfStr[0], arrOfStr[1],
                    userRequest.getAge(), userRequest.getDob(), userRequest.location,
                    userRequest.getDob().getYear(), userRequest.getDob().getMonthValue(), userRequest.getDob().getDayOfMonth());
            // return mapper.map(userRequest, UserRequestDto.class);
            return userRequestDto;
        }
        return null;
    }

    public List<UserRequestDto> entityToDto(List<UserRequest> userRequest) {

        return userRequest.stream().map(this::entityToDto)
                .collect(Collectors.toList());
        //.map(this::entityToDto)
    }

    public UserRequest dtoToEntity(com.example.model.UserRequestDto userRequestDto) {
        LocalDate dob = LocalDate.of(userRequestDto.getYear(), userRequestDto.getMonth(), userRequestDto.getDay());
        UserRequestDto userRequestDto1 = new UserRequestDto(userRequestDto.getFirstName(), userRequestDto.getLastName(),
                Converter.calculateAge(userRequestDto.getDob()), userRequestDto.getDob(), userRequestDto.getLocation(),
                userRequestDto.getYear(), userRequestDto.getMonth(), userRequestDto.getDay());
        String name = userRequestDto.getFirstName().replaceAll("_", "-") + "_" + userRequestDto.getLastName().replaceAll("_", "-");
        ModelMapper mapper = new ModelMapper();
        return new UserRequest(name, Converter.calculateAge(userRequestDto.getDob()), userRequestDto.getDob(), userRequestDto.getLocation(),
                userRequestDto.getYear(), userRequestDto.getMonth(), userRequestDto.getDay());

    }

//    public List<UserRequest> dtoToEntity(List<com.example.model.UserRequestDto> dto) {
//        return dto.stream().map(this::dtoToEntity).collect(Collectors.toList());
//    }

    public static int calculateAge(LocalDate dob) {
        LocalDate curDate = LocalDate.now();
        return Period.between(dob, curDate).getYears();

    }
}
