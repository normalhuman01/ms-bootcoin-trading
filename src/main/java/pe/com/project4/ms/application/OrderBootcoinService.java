package pe.com.project4.ms.application;

import pe.com.project4.ms.domain.OrderBootcoin;
import pe.com.project4.ms.domain.OrderBootcoinState;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderBootcoinService {
    Flux<OrderBootcoin> findAllBySellerId(String sellerId);

    Mono<OrderBootcoin> process(OrderBootcoin orderBootcoin);

    Mono<OrderBootcoin> save(OrderBootcoin orderBootcoin);

    Mono<OrderBootcoin> updateState(OrderBootcoinState orderBootcoinState, String id);
}
