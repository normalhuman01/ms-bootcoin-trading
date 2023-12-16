package pe.com.project4.ms.infrastructure.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import pe.com.project4.ms.infrastructure.event.SendBootCoinRequestEvent;

@Component
@RequiredArgsConstructor
@Slf4j
public class SendBootCoinProducer {
    private final KafkaTemplate<String, SendBootCoinRequestEvent> kafkaTemplate;

    public void sendBootCoinEvent(SendBootCoinRequestEvent sendBootCoinRequestEvent) {
        log.debug("==> Producing message {}", sendBootCoinRequestEvent.toString());
        kafkaTemplate.send("SEND-BOOT-COIN-EVENT", sendBootCoinRequestEvent);
    }

}
