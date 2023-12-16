package pe.com.project4.ms.application.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.com.project4.ms.application.AcceptPurchaseRequisitionUseCase;
import pe.com.project4.ms.application.OrderBootcoinService;
import pe.com.project4.ms.domain.OrderBootcoin;
import pe.com.project4.ms.domain.OrderBootcoinState;
import pe.com.project4.ms.infrastructure.event.PurchaseOrderEvent;
import pe.com.project4.ms.infrastructure.event.PurchaseRequisitionEvent;
import pe.com.project4.ms.infrastructure.producer.PurcharseOrderProducer;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class AcceptPurchaseRequisitionService implements AcceptPurchaseRequisitionUseCase {

    private final PurcharseOrderProducer purcharseOrderProducer;
    private final OrderBootcoinService orderBootcoinService;

    @Override
    public Mono<OrderBootcoin> acceptPurchaseRequisition(PurchaseRequisitionEvent purcharseRequistionEvent) {
        PurchaseOrderEvent purchaseOrderEvent = purcharseRequistionEvent.toPurcharseOrderEvent();
        purcharseOrderProducer.sendPurcharseOrderEvent(purchaseOrderEvent);
        return orderBootcoinService.updateState(OrderBootcoinState.ACCEPT, purcharseRequistionEvent.getOrderBootcoin().getId());
    }

}
