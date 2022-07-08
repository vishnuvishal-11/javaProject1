package com.example.dbproject.dto;

import com.example.dbproject.model.TringMembership;
import com.example.dbproject.model.Users;
import com.example.dbproject.repository.TringMembershipRepo;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
public class UserConverter {
    @Autowired
    TringMembershipRepo tringmembershiprepo;
    ObjectMapper mapper = new ObjectMapper();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    public UserDto convertToDto(Users users) {
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.disable(DeserializationFeature.EAGER_DESERIALIZER_FETCH);
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        mapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
        mapper.setVisibility(VisibilityChecker.Std.defaultInstance().withGetterVisibility(JsonAutoDetect.Visibility.ANY));
        mapper.setVisibility(VisibilityChecker.Std.defaultInstance().withSetterVisibility(JsonAutoDetect.Visibility.ANY));
        UserDto userDto = mapper.convertValue(users, new TypeReference<UserDto>() {});
        if(users.getTring_membership()!=null){
        TringMembership tringMembership=users.getTring_membership();
        userDto.setValid_till(tringMembership.getValid_till());
        userDto.setMembership_status(tringMembership.getStatus());
        userDto.setCreated_at(tringMembership.getCreated_at());}
        return userDto;
    }

    public Users convertToUserEntity(UserDto userDto) {
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
        mapper.setVisibility(VisibilityChecker.Std.defaultInstance().withGetterVisibility(JsonAutoDetect.Visibility.ANY));
        mapper.setVisibility(VisibilityChecker.Std.defaultInstance().withSetterVisibility(JsonAutoDetect.Visibility.ANY));
        Users user = mapper.convertValue(userDto, new TypeReference<Users>() {
        });
        return user;
    }

    public TringMembership convertToTringEntity(UserDto userDto) {
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
        mapper.setVisibility(VisibilityChecker.Std.defaultInstance().withGetterVisibility(JsonAutoDetect.Visibility.ANY));
        mapper.setVisibility(VisibilityChecker.Std.defaultInstance().withSetterVisibility(JsonAutoDetect.Visibility.ANY));
        TringMembership tringMembership = mapper.convertValue(userDto, new TypeReference<TringMembership>() {
        });
        Users user = convertToUserEntity(userDto);
        tringMembership.setUsers(user);
        return tringMembership;
    }

    public List<UserDto> convertListToDto(List<Users> usersList) {
        return usersList.stream().map(x -> convertToDto(x)).collect(Collectors.toList());

    }
}
