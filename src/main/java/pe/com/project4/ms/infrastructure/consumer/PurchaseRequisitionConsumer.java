package pe.com.project4.ms.infrastructure.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pe.com.project4.ms.application.OrderBootcoinService;
import pe.com.project4.ms.domain.OrderBootcoin;
import pe.com.project4.ms.domain.OrderBootcoinState;
import pe.com.project4.ms.infrastructure.event.PurchaseRequisitionEvent;

@Component
@Slf4j
@RequiredArgsConstructor
public class PurchaseRequisitionConsumer {

    private final OrderBootcoinService orderBootcoinService;

    @KafkaListener(topics = "PURCHARSE-REQUISITION-EVENT", groupId = "BOOTCOIN")
    public void consume(PurchaseRequisitionEvent purchaseRequisitionEvent) {
        log.info("Consuming Message {}", purchaseRequisitionEvent);
        OrderBootcoin orderBootcoin = purchaseRequisitionEvent.getOrderBootcoin();
        orderBootcoin.setOrderBootcoinState(OrderBootcoinState.PENDING);
        orderBootcoinService.save(orderBootcoin)
                .subscribe(result -> log.debug(result.toString()));
    }

}
