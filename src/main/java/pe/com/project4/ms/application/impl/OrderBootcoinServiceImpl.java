package pe.com.project4.ms.application.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.com.project4.ms.application.OrderBootcoinService;
import pe.com.project4.ms.application.repository.OrderBootcoinRepository;
import pe.com.project4.ms.domain.OrderBootcoin;
import pe.com.project4.ms.domain.OrderBootcoinState;
import pe.com.project4.ms.infrastructure.event.SendBootCoinRequestEvent;
import pe.com.project4.ms.infrastructure.event.SendMoneyRequestEvent;
import pe.com.project4.ms.infrastructure.producer.SendBootCoinProducer;
import pe.com.project4.ms.infrastructure.producer.SendMoneyProducer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderBootcoinServiceImpl implements OrderBootcoinService {

    private final OrderBootcoinRepository orderBootcoinRepository;
    private final SendBootCoinProducer sendBootCoinProducer;
    private final SendMoneyProducer sendMoneyProducer;

    @Override
    public Flux<OrderBootcoin> findAllBySellerId(String sellerId) {
        return orderBootcoinRepository.findAllBySellerId(sellerId);
    }

    @Override
    public Mono<OrderBootcoin> process(OrderBootcoin orderBootcoin) {
        String transactionNumber = UUID.randomUUID().toString();
        SendBootCoinRequestEvent sendBootCoinRequestEvent = new SendBootCoinRequestEvent(orderBootcoin, transactionNumber);
        SendMoneyRequestEvent sendMoneyRequestEvent = new SendMoneyRequestEvent(orderBootcoin, transactionNumber);
        sendBootCoinProducer.sendBootCoinEvent(sendBootCoinRequestEvent);
        sendMoneyProducer.sendMoneyEvent(sendMoneyRequestEvent);
        return Mono.just(orderBootcoin);
    }

    @Override
    public Mono<OrderBootcoin> save(OrderBootcoin orderBootcoin) {
        return orderBootcoinRepository.save(orderBootcoin);
    }

    @Override
    public Mono<OrderBootcoin> updateState(OrderBootcoinState orderBootcoinState, String id) {
        return orderBootcoinRepository.updateState(orderBootcoinState, id);
    }

}
