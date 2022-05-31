package security;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;
@Getter
@Setter
@Component
public class AccessList {
    private Map<String,Counter> accessHistory= new HashMap<>();
}
