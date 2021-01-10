package com.mydata.platform.api.esb.process;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Project : IntelliJ IDEA
 * Create by: IntelliJ IDEA
 * Developer : joyunjae
 * Date: 2021/01/09
 * Time: 10:42 오후
 * Extra :
 */
@Slf4j
@Component
public class MultiCaseAggregateProcess implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        List<Map> oBody = new ArrayList<Map>();
        List<String> iBody = (List<String>) exchange.getMessage().getBody();

        ObjectMapper mapper = new ObjectMapper();
        for (String data: iBody) {
            Map map = mapper.readValue(data, Map.class);
            oBody.add(map);
        }
        exchange.getOut().setBody(oBody);
    }
}
