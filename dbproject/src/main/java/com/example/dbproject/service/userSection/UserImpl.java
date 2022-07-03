package com.example.dbproject.service.userSection;

import com.example.dbproject.dao.UserSection.UserDAO;
import com.example.dbproject.dto.userSection.UserDto;
import com.example.dbproject.model.userSection.Card_details;
import com.example.dbproject.model.userSection.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
@Slf4j
public class UserImpl<T> implements UserInterface<T> {
    @Autowired
    UserDAO userDAO;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    @Override
    public Long save(T obj) throws ParseException {
        UserDto userDto = (UserDto) obj;

        Users user = new Users(userDto.getName(), userDto.getPassword(), userDto.getEmail(),
                userDto.getInvite_code(), userDto.getReferred_code(), userDto.getStatus());

        int index = userDto.getCard_details().size();
        for (int i = 0; i < index; i++) {
            log.info("i" + i + " size" + userDto.getCard_details().get(i));
            LinkedHashMap linkedHashMap = (LinkedHashMap) userDto.getCard_details().get(i);

            ArrayList<Object> values = new ArrayList<Object>(linkedHashMap.values());
            String date = values.get(3) + " ";
            user.getCard_details().add(new Card_details(Long.parseLong(String.valueOf(values.get(0))), values.get(1).toString(),
                    Long.parseLong(String.valueOf(values.get(2))), formatter.parse(date)));
        }

        return userDAO.save(user);
    }


    @Override
    public T update(T obj, Long id) throws ParseException {
        UserDto userDto = (UserDto) obj;
        Users user = null;
        if (userDAO.existsById(id)) {
            user = (Users) userDAO.getById(id);
            log.info("update"+user);
            user.setName(userDto.getName());
            user.setPassword(userDto.getPassword());
            user.setEmail(userDto.getEmail());
            user.setStatus(userDto.getStatus());
            user.setReferred_code(userDto.getReferred_code());
            user.getCard_details().clear();
            for (int i = 0; i < userDto.getCard_details().size(); i++) {
                LinkedHashMap linkedHashMap = (LinkedHashMap) userDto.getCard_details().get(i);
                ArrayList<Object> values = new ArrayList<Object>(linkedHashMap.values());
                String date = values.get(3) + " ";
                user.getCard_details().add(new Card_details(Long.parseLong(String.valueOf(values.get(0))), values.get(1).toString(),
                        Long.parseLong(String.valueOf(values.get(2))), formatter.parse(date)));
            }
            log.info("update"+userDto);

            userDAO.update(user);
            return (T)get(id);
        } else return null;

    }

    @Override
    public T delete(Long id) {
        Users user = (Users) userDAO.getById(id);
        List<Object> list = new ArrayList<>();
        if (user != null) {
            Map<Integer, HashMap<String, Object>> map = new HashMap<>();
            for (int i = 0; i < user.getCard_details().size(); i++) {
                map.put(i, new HashMap<>());
                map.get(i).put("card_number", user.getCard_details().get(i).getCard_number());
                map.get(i).put("card_holder_name", user.getCard_details().get(i).getCard_holder_name());
                map.get(i).put("phone_number", user.getCard_details().get(i).getPhone_number());
                map.get(i).put("expiry_date", user.getCard_details().get(i).getExpiry_date());
            }
            list.addAll(map.values());
            log.info("plan: " + list);
            UserDto userDto = new UserDto(user.getId(), user.getName(), user.getPassword(), user.getEmail(), user.getInvite_code(),
                    user.getReferred_code(), user.getStatus(), list);
            userDAO.delete(id);
            return (T) userDto;
        } else return null;
    }

    @Override
    public T get(Long id) {
        Users user = (Users) userDAO.getById(id);
        List<Object> list = new ArrayList<>();
        if (user != null) {
            Map<Integer, HashMap<String, Object>> map = new HashMap<>();
            for (int i = 0; i < user.getCard_details().size(); i++) {
                map.put(i, new HashMap<>());
                map.get(i).put("card_number", user.getCard_details().get(i).getCard_number());
                map.get(i).put("card_holder_name", user.getCard_details().get(i).getCard_holder_name());
                map.get(i).put("phone_number", user.getCard_details().get(i).getPhone_number());
                map.get(i).put("expiry_date", user.getCard_details().get(i).getExpiry_date());
            }
            list.addAll(map.values());
            log.info("plan: " + list);
            UserDto userDto = new UserDto(user.getId(), user.getName(), user.getPassword(), user.getEmail(), user.getInvite_code(),
                    user.getReferred_code(), user.getStatus(), list);
            return (T) userDto;
        } else return null;
    }

    @Override
    public List<T> getAll() {
        return userDAO.getAll();
    }
}
