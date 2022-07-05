package com.example.dbproject.service.user;

import com.example.dbproject.dto.user.UserDto;
import com.example.dbproject.model.user.CardDetails;
import com.example.dbproject.model.user.TringMembership;
import com.example.dbproject.model.user.Users;
import com.example.dbproject.repository.user.CardDetailsRepository;
import com.example.dbproject.repository.user.TringMembershipRepo;
import com.example.dbproject.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
@Slf4j
public class UserImpl<T> implements UserInterface<T> {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CardDetailsRepository card_details_repository;
    @Autowired
    TringMembershipRepo tringmembershiprepo;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    @Override
    public Long save(T obj) throws ParseException {
        UserDto userDto = (UserDto) obj;
        List<?> email = userRepository.getAllEmail();
        List<?> invite_code = userRepository.getAllInviteCode();
        if(email.contains(userDto.getEmail())&&(invite_code.contains(userDto.getInvite_code())))
            return null;
        Users user = new Users(userDto.getName(), userDto.getPassword(), userDto.getEmail(),
                userDto.getInvite_code(), userDto.getReferred_code(), userDto.getStatus());
        //try{
        if (userDto.getMembership_status() != null && userDto.getCreated_at() != null) {
            TringMembership tring_membership = new TringMembership(userDto.getValid_till(), userDto.getCreated_at(),
                    userDto.getMembership_status());
            tring_membership.setUsers(new Users(userDto.getName(), userDto.getPassword(), userDto.getEmail(),
                    userDto.getInvite_code(), userDto.getReferred_code(), userDto.getStatus()));
            user.setTring_membership(tring_membership);
            if (userDto.getCard_details() != null) {
                int index = userDto.getCard_details().size();
                for (int i = 0; i < index; i++) {
                    LinkedHashMap linkedHashMap = (LinkedHashMap) userDto.getCard_details().get(i);
                    ArrayList<Object> values = new ArrayList<Object>(linkedHashMap.values());
                    String date = values.get(3) + " ";

                    tring_membership.getUsers().getCard_details().add(new CardDetails(Long.parseLong(String.valueOf(values.get(0))), values.get(1).toString(),
                            Long.parseLong(String.valueOf(values.get(2))), formatter.parse(date)));
                }
                tringmembershiprepo.save(tring_membership);
                return tring_membership.getUsers().getId();
            }
        }
        if (userDto.getCard_details() != null) {
            int index = userDto.getCard_details().size();
            for (int i = 0; i < index; i++) {
                LinkedHashMap linkedHashMap = (LinkedHashMap) userDto.getCard_details().get(i);
                ArrayList<Object> values = new ArrayList<Object>(linkedHashMap.values());
                String date = values.get(3) + " ";
                user.getCard_details().add(new CardDetails(Long.parseLong(String.valueOf(values.get(0))), values.get(1).toString(),
                        Long.parseLong(String.valueOf(values.get(2))), formatter.parse(date)));
            }
        }
        userRepository.save(user);
        return user.getId();
  //  }catch(){}
    }


    @Override
    public T update(T obj, Long id) {
        UserDto userDto = (UserDto) obj;
        Users user = (Users) userRepository.getReferenceById(id);
        if (userDto.getMembership_status() != null && userDto.getCreated_at() != null) {
            TringMembership tring_membership = user.getTring_membership();

            if (tring_membership != null) {
                tring_membership.setCreated_at(userDto.getCreated_at());
                tring_membership.setStatus(userDto.getMembership_status());
                tring_membership.setValid_till(userDto.getValid_till());
            }
        }
        if (userRepository.existsById(id)) {
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
            return (T) get(id);
        } else return null;

    }

    @Override
    public T delete(Long id) {
        Users user = (Users) userRepository.getReferenceById(id);
        TringMembership tring_membership = user.getTring_membership();
        UserDto userDto;
        List<Object> list = new ArrayList<>();

        if (user != null) {
            if (user.getCard_details() != null) {
                Map<Integer, HashMap<String, Object>> map = new HashMap<>();
                for (int i = 0; i < user.getCard_details().size(); i++) {
                    map.put(i, new HashMap<>());
                    map.get(i).put("card_number", user.getCard_details().get(i).getCard_number());
                    map.get(i).put("card_holder_name", user.getCard_details().get(i).getCard_holder_name());
                    map.get(i).put("phone_number", user.getCard_details().get(i).getPhone_number());
                    map.get(i).put("expiry_date", user.getCard_details().get(i).getExpiry_date());
                }
                list.addAll(map.values());
            }
            if (tring_membership != null)
                userDto = new UserDto(user.getId(), user.getName(), user.getPassword(), user.getEmail(), user.getInvite_code(),
                        user.getReferred_code(), user.getStatus(), list,
                        tring_membership.getValid_till(), tring_membership.getCreated_at(), tring_membership.getStatus());
            else
                userDto = new UserDto(user.getId(), user.getName(), user.getPassword(), user.getEmail(), user.getInvite_code(),
                        user.getReferred_code(), user.getStatus(), list, null, null, null);

            userRepository.deleteById(id);
            return (T) userDto;
        } else return null;
    }

    @Override
    public T get(Long id) {
        Users user = (Users) userRepository.getReferenceById(id);
        TringMembership tring_membership = user.getTring_membership();
        UserDto userDto;
        List<Object> list = new ArrayList<>();
        if (user != null) {
            if (user.getCard_details() != null) {
                Map<Integer, HashMap<String, Object>> map = new HashMap<>();
                for (int i = 0; i < user.getCard_details().size(); i++) {
                    map.put(i, new HashMap<>());
                    map.get(i).put("card_number", user.getCard_details().get(i).getCard_number());
                    map.get(i).put("card_holder_name", user.getCard_details().get(i).getCard_holder_name());
                    map.get(i).put("phone_number", user.getCard_details().get(i).getPhone_number());
                    map.get(i).put("expiry_date", user.getCard_details().get(i).getExpiry_date());
                }
                list.addAll(map.values());
            }
            if (tring_membership != null)
                userDto = new UserDto(user.getId(), user.getName(), user.getPassword(), user.getEmail(), user.getInvite_code(),
                        user.getReferred_code(), user.getStatus(), list,
                        tring_membership.getValid_till(), tring_membership.getCreated_at(), tring_membership.getStatus());
            else
                userDto = new UserDto(user.getId(), user.getName(), user.getPassword(), user.getEmail(), user.getInvite_code(),
                        user.getReferred_code(), user.getStatus(), list, null, null, null);

            return (T) userDto;
        } else return null;
    }

    @Override
    public List<?> getAll() {

        List<?> ids = userRepository.getAllList();
        int size = ids.size();
        int count = 0;
        List<T> list = new ArrayList<>();
        while (count < size) {
            if (size > 0)
                list.add(get(((BigInteger) ids.get(count)).longValue()));
            count++;

        }
        return list;
    }
}


//,
//    "valid_till": "2018-04-28T14:45:15",
//    "created_at":"2016-04-28T14:45:15",
//    "membership_status":true