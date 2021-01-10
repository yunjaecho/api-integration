package com.mydata.platform.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class SampleController {

    @PostMapping("hello1")
    @ResponseBody
    public Map<String, Object> hello1(@RequestBody Map params) {
        return params;
    }

    @PostMapping("hello2/{fruit}")
    @ResponseBody
    public Map<String, Object> hello2(@RequestBody Map params, @PathVariable("fruit") String fruit) {
        params.put("fruit", fruit);
        log.info(params.toString());
        return params;
    }


    @GetMapping("/{version}/bank/accounts")
    @ResponseBody
    public Map<String, Object> accounts(HttpServletRequest request,
                                        @RequestParam(name = "org_code", required = true) String org_code,
                                        @RequestParam(name ="search_timestamp", required = true) String search_timestamp,
                                        @RequestParam(name ="next_page", required = false) String next_page,
                                        @RequestParam(name ="limit", required = true) String limit) throws IOException {

        String authorization = request.getHeader("Authorization");

        log.info("===========   accounts  Request Variable =================");
        log.info("  org_code         : " + org_code);
        log.info("  search_timestamp : " + search_timestamp);
        log.info("  next_page        : " + next_page);
        log.info("  limit            : " + limit);
        log.info("  authorization    : " + authorization);

        String response = "{\n" +
                "  \"rsp_code\": \"string\",\n" +
                "  \"rsp_msg\": \"string\",\n" +
                "  \"search_timestamp\": \"string\",\n" +
                "  \"reg_date\": \"string\",\n" +
                "  \"next_page\": \"string\",\n" +
                "  \"account_cnt\": 0,\n" +
                "  \"account_list\": [\n" +
                "    {\n" +
                "      \"account_num\": \"string\",\n" +
                "      \"is_consent\": true,\n" +
                "      \"seqno\": 0,\n" +
                "      \"currency_code\": \"string\",\n" +
                "      \"prod_name\": \"string\",\n" +
                "      \"account_type\": \"string\",\n" +
                "      \"account_status\": \"01\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> responseMap = objectMapper.readValue(response, Map.class);
        return responseMap;
    }

}
