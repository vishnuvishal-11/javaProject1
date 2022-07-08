package com.example.dbproject.dto;

import com.example.dbproject.model.Celebrity;
import com.example.dbproject.model.Price;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class CelebrityConverter {
    ObjectMapper mapper = new ObjectMapper();

    public CelebrityDto convertToDto(Celebrity celebrity) {
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        mapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
        mapper.setVisibility(VisibilityChecker.Std.defaultInstance().withGetterVisibility(JsonAutoDetect.Visibility.ANY));
        mapper.setVisibility(VisibilityChecker.Std.defaultInstance().withSetterVisibility(JsonAutoDetect.Visibility.ANY));
        CelebrityDto celebrityDto = mapper.convertValue(celebrity, new TypeReference<CelebrityDto>() {
        });
        List<Object> list = new ArrayList<>();
        Map<Integer, HashMap<String, Object>> map = new HashMap<>();
        for (int i = 0; i < celebrity.getPrices().size(); i++) {
            map.put(i, new HashMap<>());
            map.get(i).put("type", celebrity.getPrices().get(i).getType());
            map.get(i).put("price", celebrity.getPrices().get(i).getPrice());
        }
        list.addAll(map.values());
        celebrityDto.setPlans(list);
        return celebrityDto;
    }

    public Celebrity convertToEntity(CelebrityDto celebrityDto) {
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
        mapper.setVisibility(VisibilityChecker.Std.defaultInstance().withGetterVisibility(JsonAutoDetect.Visibility.ANY));
        mapper.setVisibility(VisibilityChecker.Std.defaultInstance().withSetterVisibility(JsonAutoDetect.Visibility.ANY));
        Celebrity celebrity = mapper.convertValue(celebrityDto, new TypeReference<Celebrity>() {
        });

        int index = celebrityDto.getPlans().size();
        for (int i = 0; i < index; i++) {
            LinkedHashMap linkedHashMap = (LinkedHashMap) celebrityDto.getPlans().get(i);
            ArrayList<Object> Values = new ArrayList<Object>(linkedHashMap.values());
            celebrity.getPrices().add(new Price(Values.get(0).toString(), (Integer) Values.get(1)));
        }
        return celebrity;
    }

    public List<CelebrityDto> convertListToDto(List<?> celebrityList) {
        return celebrityList.stream().map(x -> convertToDto((Celebrity) x)).collect(Collectors.toList());

    }
    public Page<CelebrityDto> convertpageToDto(Page<Celebrity> celebrityList) {
        Page<CelebrityDto> list =  celebrityList.map(x -> convertToDto(x));
        return  list;

    }
}
