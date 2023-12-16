package pe.com.project4.ms.infrastructure.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class OrderBootcoinRouter {
    private static final String TRADING_BOOTCOINS = "/bootcoin-trading";
    private static final String PURCHARSE_REQUISITIONS = TRADING_BOOTCOINS + "/purcharse-requistion";

    @Bean
    public RouterFunction<ServerResponse> routes(OrderBootcoinHandler orderBootcoinHandler) {
        return route(POST(TRADING_BOOTCOINS).and(accept(APPLICATION_JSON)), orderBootcoinHandler::sendPurcharseRequisition)
                .andRoute(POST(PURCHARSE_REQUISITIONS).and(accept(APPLICATION_JSON)), orderBootcoinHandler::acceptPurcharseRequisition)
                .andRoute(GET(TRADING_BOOTCOINS).and(queryParam("phoneNumber", t -> true)), orderBootcoinHandler::getPurcharseRequisitionsByHolderPhoneNumber);
    }
}
