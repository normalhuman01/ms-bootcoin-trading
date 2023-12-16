package pe.com.project4.ms.infrastructure.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import pe.com.project4.ms.application.AcceptPurchaseRequisitionUseCase;
import pe.com.project4.ms.application.OrderBootcoinService;
import pe.com.project4.ms.application.SendPurcharseRequisitionUseCase;
import pe.com.project4.ms.domain.OrderBootcoin;
import pe.com.project4.ms.infrastructure.event.PurchaseOrderEvent;
import pe.com.project4.ms.infrastructure.event.PurchaseRequisitionEvent;
import reactor.core.CorePublisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderBootcoinHandler {

    private final AcceptPurchaseRequisitionUseCase acceptPurchaseRequisitionUseCase;
    private final SendPurcharseRequisitionUseCase sendPurcharseRequisitionUseCase;
    private final OrderBootcoinService orderBootcoinService;

    public Mono<ServerResponse> sendPurcharseRequisition(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(PurchaseRequisitionEvent.class)
                .map(sendPurcharseRequisitionUseCase::sendPurcharseRequisition)
                .flatMap(orderBootcoin -> this.toServerResponse(orderBootcoin, HttpStatus.CREATED));
    }

    public Mono<ServerResponse> acceptPurcharseRequisition(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(PurchaseRequisitionEvent.class)
                .map(acceptPurchaseRequisitionUseCase::acceptPurchaseRequisition)
                .flatMap(orderBootcoin -> this.toServerResponse(orderBootcoin, HttpStatus.CREATED));
    }

    public Mono<ServerResponse> getPurcharseRequisitionsByHolderPhoneNumber(ServerRequest serverRequest) {
        Flux<OrderBootcoin> bankAccountFlux = serverRequest.queryParam("phoneNumber")
                .map(orderBootcoinService::findAllBySellerId)
                .orElseGet(Flux::empty);
        return this.toServerResponse(bankAccountFlux, HttpStatus.OK);
    }

    private Mono<ServerResponse> toServerResponse(CorePublisher<OrderBootcoin> purcharseOrderEvent, HttpStatus status) {
        log.info("==> Antes de responder {} " + purcharseOrderEvent.toString());
        return ServerResponse
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(purcharseOrderEvent, PurchaseOrderEvent.class);
    }


}
