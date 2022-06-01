package Redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import security.AccessList;

import java.util.Map;


@Service
public class RedisService {
    Logger logger = LoggerFactory.getLogger(RedisService.class);

    AccessList accessList;
    @Autowired
    private RedisTemplate<String, AccessList> redisTemplate;
    private HashOperations hashOperations;

    public RedisService(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = this.redisTemplate.opsForHash();
    }

    public void save(AccessList accessList) {
        hashOperations.put("access", accessList.getIp(), accessList);
    }


    public boolean existsById(String s) {
        if (redisTemplate.opsForHash().hasKey("access", s))
            return true;
        else
            return false;
    }

    public AccessList findById(String s) {
        return (AccessList) redisTemplate.opsForHash().get("access", s);

    }

//    public Map<Object, Object> display(){
//        return   redisTemplate.opsForHash().entries("access");
//
//
//    }


}



