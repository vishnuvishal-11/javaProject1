package ipServices;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Component
@EnableCaching
public class IPadressImpl implements IPaddress {
    static Set<String> set=new TreeSet<>();
    Logger logger = LoggerFactory.getLogger(IPadressImpl.class);


    @SneakyThrows
    @Override
 //   @Cacheable(value = "addresses", key = "ipAddress")
//    @ReadThroughSingleCache
    public Boolean getClientIp(HttpServletRequest request) {

        String ipAddress ="uri: "+ request.getRequestURI()+" url: "+request.getRequestURL()+" port: "+request.getRemotePort()
                +" name: "+request.getRemoteUser()+" host: "+request.getRemoteHost();
        set.add(ipAddress);

        return true;


    }

    @Override
    public Set<String> getList() {
        return set;
    }

}
