package pe.com.project4.ms.application.repository;

import pe.com.project4.ms.domain.OrderBootcoin;
import pe.com.project4.ms.domain.OrderBootcoinState;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderBootcoinRepository {
    Mono<OrderBootcoin> save(OrderBootcoin orderBootcoin);

    Mono<OrderBootcoin> updateState(OrderBootcoinState orderBootcoinState, String id);

    Flux<OrderBootcoin> findAllBySellerId(String sellerId);
}
