package com.mydata.platform.api.esb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mydata.platform.api.esb.aggregation.ArrayListAggregationStrategy;
import com.mydata.platform.api.esb.process.BeforeRouteProcess;
import com.mydata.platform.api.esb.process.MultiCaseAggregateProcess;
import com.mydata.platform.api.esb.process.AfterRouterProcess;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RouteResourceBuilder extends RouteBuilder {

    private final BeforeRouteProcess beforeRouteProcess;
    private final AfterRouterProcess afterRouterProcess;
    private final ArrayListAggregationStrategy arrayListAggregationStrategy;
    private final MultiCaseAggregateProcess multiCaseAggregateProcess;

    public RouteResourceBuilder(BeforeRouteProcess beforeRouteProcess, AfterRouterProcess afterRouterProcess, ArrayListAggregationStrategy arrayListAggregationStrategy, MultiCaseAggregateProcess multiCaseAggregateProcess) {
        this.beforeRouteProcess = beforeRouteProcess;
        this.afterRouterProcess = afterRouterProcess;
        this.arrayListAggregationStrategy = arrayListAggregationStrategy;
        this.multiCaseAggregateProcess = multiCaseAggregateProcess;
    }


    @Override
    public void configure() throws Exception {

        restConfiguration().component("servlet").port(8080).host("localhost").bindingMode(RestBindingMode.json);


        /**
         * Service ID : SVR_00001
         */
        rest("/bank/accounts")
                .get()
                .to("direct:SVR_00001")
                ;

        from("direct:SVR_00001")
                .routeId("SVR_00001")
                .process(beforeRouteProcess)
                .recipientList(simple("http://localhost:8080/v1/bank/${header.foo}?bridgeEndpoint=true"))
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .unmarshal().json(JsonLibrary.Jackson)
                .process(afterRouterProcess)
        .end()
                ;



        rest("/hello")
                .post()
                .route()
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        exchange.setProperty("ORG_ADDRESS", "locatlhost:8080");
                        log.info((String)exchange.getMessage().getHeader("orgCode"));

                        final String userId = (String) exchange.getMessage().getHeader("userid");
                        log.info("user_id : " + userId);
                        String iBodyMap = exchange.getIn().getBody(String.class);
                        log.info("  ============= In Exchange ============");
                        log.info(iBodyMap);
                    }
                })
                .marshal().json(JsonLibrary.Jackson)
                .to("http://localhost:8080/hello1?bridgeEndpoint=true")
                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                .setHeader("Authorization", constant("asfdlasdjfl234233454353543543"))
                .unmarshal().json(JsonLibrary.Jackson)
                .end()
                .process(afterRouterProcess)
                .endRest()
        ;


            rest("/hell02")
                    .post("{id}/order").to("direct:postSend");

            from("direct:postSend")
                    .marshal().json(JsonLibrary.Jackson)
                    .toD("http://localhost:8080/hello1?bridgeEndpoint=true")
                    .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                    .unmarshal().json(JsonLibrary.Jackson)
                    .end()
            ;



        rest("/multicast").enableCORS(true)
            .post()
            .route()
                .marshal().json(JsonLibrary.Jackson)
                .multicast(arrayListAggregationStrategy)
                .parallelProcessing()
                    .to("http://localhost:8080/hello2/apple?bridgeEndpoint=true")
                    .to("http://localhost:8080/hello2/orange?bridgeEndpoint=true")
                    .to("http://localhost:8080/hello2/mango?bridgeEndpoint=true")
                .end()
               .process(multiCaseAggregateProcess)
        .endRest();

    }

}