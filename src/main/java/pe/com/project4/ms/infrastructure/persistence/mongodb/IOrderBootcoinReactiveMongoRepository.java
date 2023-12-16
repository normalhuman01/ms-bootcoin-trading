package pe.com.project4.ms.infrastructure.persistence.mongodb;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import pe.com.project4.ms.infrastructure.persistence.model.OrderBootcoinDao;
import reactor.core.publisher.Flux;

public interface IOrderBootcoinReactiveMongoRepository extends ReactiveMongoRepository<OrderBootcoinDao, String> {
    Flux<OrderBootcoinDao> findBySellerId(String sellerId);
}
