package pe.com.project4.ms.application.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.com.project4.ms.application.SendPurcharseRequisitionUseCase;
import pe.com.project4.ms.domain.OrderBootcoin;
import pe.com.project4.ms.domain.OrderBootcoinState;
import pe.com.project4.ms.infrastructure.event.PurchaseRequisitionEvent;
import pe.com.project4.ms.infrastructure.producer.PurchaseRequisitionProducer;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class SendPurcharseRequisitionService implements SendPurcharseRequisitionUseCase {

    private final PurchaseRequisitionProducer purchaseRequisitionProducer;

    @Override
    public Mono<OrderBootcoin> sendPurcharseRequisition(PurchaseRequisitionEvent purchaseRequisitionEvent) {
        OrderBootcoin orderBootcoin = purchaseRequisitionEvent.getOrderBootcoin();
        orderBootcoin.setOrderBootcoinState(OrderBootcoinState.PENDING);
        purchaseRequisitionProducer.sendPurchaseRequisitionEvent(purchaseRequisitionEvent);
        return Mono.just(orderBootcoin);
    }
}
