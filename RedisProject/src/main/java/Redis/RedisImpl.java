package Redis;

import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;
@Component("cache")
@NoArgsConstructor
public class RedisImpl implements FactoryInterface {
    Logger logger = LoggerFactory.getLogger(RedisImpl.class);

    @Value("${Ratelimit.count}")
    int count;

    @Value("${Ratelimit.penalty.minutes}")
    int penalty;

    @Autowired
   @Qualifier("redis1")
     RedisTemplate<String, Integer> redis1redisTemplate;
    @Autowired
    @Qualifier("redis2")
     RedisTemplate<String, Integer> redis2redisTemplate;

    @Override
    public void filter(String ip) throws Exception {
        logger.info("redisFilter is Used....");
        if (!(redis1redisTemplate.opsForHash().hasKey("AllowedList", ip)) && (!(redis2redisTemplate.opsForHash().hasKey("DeniedList", ip)))) {
            redis1redisTemplate.opsForHash().put("AllowedList", ip, 1);
            redis1redisTemplate.expire("AllowedList", penalty, TimeUnit.MINUTES);
            logger.info("in else..newly inserted");

        } else {
            if ((!(redis2redisTemplate.opsForHash().hasKey("DeniedList", ip))) && (redis1redisTemplate.opsForHash().hasKey("AllowedList", ip))) {
                int check = (int) redis1redisTemplate.opsForHash().get("AllowedList", ip);
                check = check + 1;
                if (check <= 10) {redis1redisTemplate.opsForHash().put("AllowedList", ip, check);

                }
                if (check == 11) {
                    redis1redisTemplate.opsForHash().delete("AllowedList", ip);
                    redis2redisTemplate.opsForHash().put("DeniedList", ip, 12);
                    redis2redisTemplate.expire("DeniedList", penalty, TimeUnit.MINUTES);
                    throw new RuntimeException("too much of  input in one minute...");
                }
            }
            if (redis2redisTemplate.opsForHash().hasKey("DeniedList", ip))
                 throw new RuntimeException("too much of  input in one minute...");
        }

    }

}
