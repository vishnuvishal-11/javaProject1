package ipServices;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.util.Set;


@Component
public interface IPaddress {

    Boolean getClientIp(HttpServletRequest request);

    Set<String> getList();



}
