package security;

import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Component
public class AccessList implements Serializable {

    private String ip;
    Counter counter;
    private Map<String, Counter> accessHistory = new HashMap<>();

    public void setAccessHistory(String ip, Counter counter) {
        accessHistory.put(ip, counter);
    }

    public AccessList(String ip, Counter counter) {
        this.ip = ip;
        this.counter = counter;
    }
}
