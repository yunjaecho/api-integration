package com.mydata.platform.api.esb.process;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import static org.apache.camel.language.constant.ConstantLanguage.constant;

@Slf4j
@Component("beforeRouteProcess")
public class BeforeRouteProcess implements Processor {

    private ObjectMapper objectMapper;

    public BeforeRouteProcess() {
        objectMapper = new ObjectMapper();
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        // endpoint 주소 변경처리
        exchange.getIn().setHeader("foo", "accounts");

        // Header 설정
//        exchange.getMessage().setHeader(Exchange.HTTP_METHOD, constant("GET"));
        exchange.getMessage().setHeader("Authorization",constant("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1ODgxMDg1MDksImlhdCI6MTU4ODEwODIwOSwibmJmIjoxNTg4MTA4MjA5LCJpZGVudGl0eSI6MX0.mtQXeyOutmeq5DHEq_IsuJrL5DllrR7Gbf4V_afiRoE"));

    }
}
