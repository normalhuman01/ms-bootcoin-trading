package pe.com.project4.ms.infrastructure.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pe.com.project4.ms.application.OrderBootcoinService;
import pe.com.project4.ms.domain.OrderBootcoin;
import pe.com.project4.ms.domain.OrderBootcoinState;
import pe.com.project4.ms.infrastructure.event.PurchaseOrderEvent;

@Component
@Slf4j
@RequiredArgsConstructor
public class PurchaseOrderConsumer {

    private final OrderBootcoinService orderBootcoinService;

    @KafkaListener(topics = "PURCHARSE-ORDER-EVENT", groupId = "BOOTCOIN")
    public void consume(PurchaseOrderEvent purchaseOrderEvent) {
        log.info("Consuming Message {}", purchaseOrderEvent);
        OrderBootcoin orderBootcoin = purchaseOrderEvent.getOrderBootcoin();
        orderBootcoin.setOrderBootcoinState(OrderBootcoinState.PROCESSING);
        orderBootcoinService.process(orderBootcoin)
                .subscribe(result -> log.debug(result.toString()));
    }

}
