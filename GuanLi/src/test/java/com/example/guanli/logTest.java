package com.example.guanli;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class logTest {
    private static final Logger log= LoggerFactory.getLogger(logTest.class);
    @Test
    public void testLog() {
        log.debug("This is a debug log message.");
        log.info("This is an info log message.");
//        log.warn("This is a warn log message.");
//        log.error("This is an error log message.");
    }
}
