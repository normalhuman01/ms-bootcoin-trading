package pe.com.project4.ms.infrastructure.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import pe.com.project4.ms.infrastructure.event.PurchaseOrderEvent;

@Component
@RequiredArgsConstructor
@Slf4j
public class PurcharseOrderProducer {
    private final KafkaTemplate<String, PurchaseOrderEvent> kafkaTemplate;

    public void sendPurcharseOrderEvent(PurchaseOrderEvent purchaseOrderEvent) {
        log.debug("==> Producing message {}", purchaseOrderEvent.toString());
        kafkaTemplate.send("PURCHARSE-ORDER-EVENT", purchaseOrderEvent);
    }

}
