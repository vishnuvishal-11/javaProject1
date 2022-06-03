package Redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import security.Counter;

import java.util.Date;
import java.util.concurrent.TimeUnit;


@Service
public class RedisService {
    Logger logger = LoggerFactory.getLogger(RedisService.class);
    Date date;


    @Value("${Ratelimit.penalty.minutes}")
    int penalty;

    @Autowired
    @Qualifier("redis1")
    private RedisTemplate<String, Integer> redis1redisTemplate;
    @Autowired
    @Qualifier("redis2")
    RedisTemplate<String, Date> redis2redisTemplate;

    public void save(String ip, int count, Date date) {
        if (this.existsById(ip)) {
            redis1redisTemplate.opsForHash().put("access", ip, count);
            redis2redisTemplate.opsForHash().put("access2", ip, date);
        } else {
            redis1redisTemplate.opsForHash().put("access", ip, count);
            redis2redisTemplate.opsForHash().put("access2", ip, date);
            redis1redisTemplate.expire("access",penalty,TimeUnit.MINUTES);
            redis1redisTemplate.expire("access2",penalty,TimeUnit.MINUTES);
        }
    }


    public boolean existsById(String Ip) {
        if (redis1redisTemplate.opsForHash().hasKey("access", Ip) && redis2redisTemplate.opsForHash().hasKey("access2", Ip))
            return true;
        else
            return false;
    }

    public int findById(String Ip) {
        return (int) redis1redisTemplate.opsForHash().get("access", Ip);

    }

    public Date findDateById(String Ip) {
        return (Date) redis2redisTemplate.opsForHash().get("access2", Ip);

    }


}



