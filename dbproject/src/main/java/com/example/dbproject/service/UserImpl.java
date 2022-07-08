package com.example.dbproject.service;

import com.example.dbproject.dto.UserConverter;
import com.example.dbproject.dto.UserDto;
import com.example.dbproject.model.CardDetails;
import com.example.dbproject.model.TringMembership;
import com.example.dbproject.model.Users;
import com.example.dbproject.repository.CardDetailsRepository;
import com.example.dbproject.repository.TringMembershipRepo;
import com.example.dbproject.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
@Slf4j
public class UserImpl implements User {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CardDetailsRepository card_details_repository;
    @Autowired
    TringMembershipRepo tringmembershiprepo;
    @Autowired
    UserConverter userConverter;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    @Override
    public Long save(UserDto userDto) throws ParseException, SQLIntegrityConstraintViolationException {

        List<?> email = userRepository.getAllEmail();
        List<?> invite_code = userRepository.getAllInviteCode();
        if (email.contains(userDto.getEmail()) && (invite_code.contains(userDto.getInvite_code())))
           throw new SQLIntegrityConstraintViolationException();
        else {
            if (userDto.getMembership_status() != null && userDto.getCreated_at() != null) {
                TringMembership tring_membership = userConverter.convertToTringEntity(userDto);
                tringmembershiprepo.save(tring_membership);
                return tring_membership.getUsers().getId();
            } else {
                Users user = userConverter.convertToUserEntity(userDto);
                userRepository.save(user);
                return user.getId();
            }
        }
    }


    @Override
    public UserDto update(UserDto userDto, Long id) {
        if (userRepository.existsById(id)) {
        Users user = userRepository.getReferenceById(id);
        if (userDto.getMembership_status() != null && userDto.getCreated_at() != null) {
            TringMembership tring_membership = user.getTring_membership();

            if (tring_membership != null) {
                tring_membership.setCreated_at(userDto.getCreated_at());
                tring_membership.setStatus(userDto.getMembership_status());
                tring_membership.setValid_till(userDto.getValid_till());
            }
        }
            user.setName(userDto.getName());
            user.setPassword(userDto.getPassword());
            user.setEmail(userDto.getEmail());
            user.setStatus(userDto.getStatus());
            user.setReferred_code(userDto.getReferred_code());
            if (userDto.getCard_details() != null) {
                user.getCard_details().clear();
                for (int i = 0; i < userDto.getCard_details().size(); i++) {
                    LinkedHashMap linkedHashMap = (LinkedHashMap) userDto.getCard_details().get(i);
                    ArrayList<Object> values = new ArrayList<Object>(linkedHashMap.values());
                    String date = values.get(3) + " ";
                    try {
                        user.getCard_details().add(new CardDetails(Long.parseLong(String.valueOf(values.get(0))), values.get(1).toString(),
                                Long.parseLong(String.valueOf(values.get(2))), formatter.parse(date)));
                    } catch (ParseException p) {
                        p.printStackTrace();
                    }
                }
            }
            userRepository.save(user);
            return userConverter.convertToDto(user);
        } else throw new EntityNotFoundException();

    }

    @Override
    public void delete(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else throw new EntityNotFoundException();
    }
    @Override
    public UserDto get(Long id) {
        if (userRepository.existsById(id)) {
            return userConverter.convertToDto(userRepository.getReferenceById(id));
        } else throw new EntityNotFoundException();
    }

    @Override
    public List<UserDto> getAll() {
        return userConverter.convertListToDto(userRepository.getAllList());
    }
}

