package security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;



public class FilterSelector {
    @Value("${dynamic.filter}")
    String filter;

    Logger logger = LoggerFactory.getLogger(FilterSelector.class);

   private Factory factory;


    public FilterSelector() {
        String filt="filter";

        this.funtion(filt);
    }


      void funtion(String fil) { System.out.println(fil);
         if(fil.equalsIgnoreCase("cache"))
             factory=new RedisFilter();
        //   else  factory= new AccessFilter();
     }


}
