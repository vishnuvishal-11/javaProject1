package com.example.ipServices;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Component
@Slf4j
public class IPadressImpl implements IPaddress {
    static Set<String> set=new TreeSet<>();



    @SneakyThrows
    @Override
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
