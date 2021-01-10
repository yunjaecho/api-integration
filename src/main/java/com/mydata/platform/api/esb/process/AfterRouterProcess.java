package com.mydata.platform.api.esb.process;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Slf4j
@Component("postRouterPorcess")
public class AfterRouterProcess implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        log.info(" After 처리...");
        log.info(" DB 후 처리...");
    }
}
