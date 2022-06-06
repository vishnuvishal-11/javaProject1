package Redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import security.AccessList;
import security.Counter;

import java.util.*;


@Component("custom")
public class CustomImpl implements FactoryInterface {
    Logger logger = LoggerFactory.getLogger(CustomImpl.class);

@Value("${Ratelimit.count}")
    int count;

@Value("${Ratelimit.penalty.minutes}")
    int penalty;

 final  AccessList accessList;

    public CustomImpl(AccessList accessList) {
        this.accessList = accessList;
    }


     @Override
    public void filter(String ip) throws Exception{

        logger.info("CustomFilter is Used...." );

            String finalIp = ip;

            accessList.getAccessHistory().computeIfPresent(ip, (CounterNumber, targetDate) -> {
                Counter counter = accessList.getAccessHistory().get(finalIp);

                if ((Calendar.getInstance().getTime().compareTo(counter.getTargetDate()) >= 0 && counter.getNumber() != count)){
                    counter.setCounter(1, new Date(Calendar.getInstance().getTimeInMillis() + (penalty * 60 * 1000)));
                    logger.info("value" +counter.getNumber());
                }
                else if (counter.getNumber() <= count - 1)
                    counter.setNumber(counter.getNumber() + 1);
                else if (counter.getNumber() == count) {
                    counter.setCounter(count + 1, new Date(Calendar.getInstance().getTimeInMillis() + (penalty * 60 * 1000)));
                    logger.info("Too many inputs..retry after" + counter.getTargetDate());
                    throw new RuntimeException("too much of  input in one minute...");
                } else if (counter.getNumber() > count)
                    throw new RuntimeException("too much of  input in one minute...");

                return counter;
            });

            accessList.getAccessHistory().computeIfAbsent(ip, (counterObj) -> {
                Counter counter = new Counter();
                counter.setCounter(1, new Date(Calendar.getInstance().getTimeInMillis() + (penalty * 60 * 1000)));
                logger.info("value" +counter.getNumber());
                return counter;
            });


        }
    }







