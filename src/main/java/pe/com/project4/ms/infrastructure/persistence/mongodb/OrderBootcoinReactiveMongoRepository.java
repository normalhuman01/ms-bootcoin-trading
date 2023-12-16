package pe.com.project4.ms.infrastructure.persistence.mongodb;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import pe.com.project4.ms.application.repository.OrderBootcoinRepository;
import pe.com.project4.ms.domain.OrderBootcoin;
import pe.com.project4.ms.domain.OrderBootcoinState;
import pe.com.project4.ms.infrastructure.persistence.model.OrderBootcoinDao;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
@Slf4j
public class OrderBootcoinReactiveMongoRepository implements OrderBootcoinRepository {

    private final IOrderBootcoinReactiveMongoRepository orderBootcoinReactiveMongoRepository;

    @Override
    public Mono<OrderBootcoin> save(OrderBootcoin orderBootcoin) {
        log.info("==> Saving: {}", orderBootcoin);
        return orderBootcoinReactiveMongoRepository.save(new OrderBootcoinDao(orderBootcoin))
                .map(OrderBootcoinDao::toOrderBootcoin)
                .doOnNext(result -> log.info("==> Despues del map {}", result));
    }

    @Override
    public Mono<OrderBootcoin> updateState(OrderBootcoinState orderBootcoinState, String id) {
        log.info("==> Updating: id: {} - state: {}", id, orderBootcoinState);
        return orderBootcoinReactiveMongoRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Operacion existe!!!!")))
                .map(orderBootcoinDaoFound -> {
                    orderBootcoinDaoFound.setOrderBootcoinState(orderBootcoinState);
                    return orderBootcoinDaoFound;
                }).flatMap(orderBootcoinReactiveMongoRepository::save)
                .map(OrderBootcoinDao::toOrderBootcoin);
    }

    @Override
    public Flux<OrderBootcoin> findAllBySellerId(String sellerId) {
        log.info("==> FindAllBySellerId: {}", sellerId);
        return orderBootcoinReactiveMongoRepository.findBySellerId(sellerId)
                .map(OrderBootcoinDao::toOrderBootcoin);
    }

}
