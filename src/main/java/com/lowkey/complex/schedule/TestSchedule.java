package com.lowkey.complex.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author lowkey
 * @description
 * @date 2023年01月09日 17:01
 */
@Component
public class TestSchedule {
    private final Logger logger = LoggerFactory.getLogger(TestSchedule.class);

    //@Scheduled(cron = "0 */1 * * * ?")
    public void test() {
        Thread.currentThread().setName("THREAD-TEST-SCHEDULE");
        logger.warn("test schedule run.......");
    }
}
