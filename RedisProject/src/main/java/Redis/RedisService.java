package Redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import security.AccessList;
import security.Counter;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Service
public class RedisService {
    Logger logger = LoggerFactory.getLogger(RedisService.class);
    Date date;



    @Value("${Ratelimit.penalty.minutes}")
    int penalty;

    @Autowired
    private RedisTemplate<String, AccessList> redisTemplate;

    public void save(AccessList accessList) {
        if(this.existsById(accessList.getIp())){
        redisTemplate.opsForHash().put("access", accessList.getIp(), accessList.getCounter());
        }
        else{  redisTemplate.opsForHash().put("access", accessList.getIp(), accessList.getCounter());
            date =new Date(Calendar.getInstance().getTimeInMillis() + (penalty * 60 * 1000));
        redisTemplate.expire("access",penalty, TimeUnit.MINUTES);
        }
    }


    public boolean existsById(String Ip) {
        if (redisTemplate.opsForHash().hasKey("access", Ip))
            return true;
        else
            return false;
    }

    public Counter findById(String Ip) {
        return (Counter) redisTemplate.opsForHash().get("access", Ip);

    }



}



