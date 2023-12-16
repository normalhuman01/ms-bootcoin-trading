package pe.com.project4.ms.application;

import pe.com.project4.ms.domain.OrderBootcoin;
import pe.com.project4.ms.infrastructure.event.PurchaseRequisitionEvent;
import reactor.core.publisher.Mono;

public interface AcceptPurchaseRequisitionUseCase {
    Mono<OrderBootcoin> acceptPurchaseRequisition(PurchaseRequisitionEvent purchaseRequisitionEvent);
}
