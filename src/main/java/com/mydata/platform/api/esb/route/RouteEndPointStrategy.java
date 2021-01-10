package com.mydata.platform.api.esb.route;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RouteEndPointStrategy {
    public String getEndPointUrl(String body) {
        log.info("  body : " + body);
        return "http://localhost:8080/v1/bank/accounts?bridgeEndpoint=true";
    }
}
