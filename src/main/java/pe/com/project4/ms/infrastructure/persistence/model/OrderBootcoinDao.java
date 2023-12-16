package pe.com.project4.ms.infrastructure.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pe.com.project4.ms.domain.OrderBootcoin;
import pe.com.project4.ms.domain.OrderBootcoinState;
import pe.com.project4.ms.domain.PaymentMethod;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("orderBootcoins")
public class OrderBootcoinDao {
    @Id
    private String id;
    private String buyerId;
    private String sellerId;
    private BigDecimal amount;
    private BigDecimal offeringPrice;
    private PaymentMethod paymentMethod;
    private OrderBootcoinState orderBootcoinState;

    public OrderBootcoinDao(OrderBootcoin orderBootcoin) {
        id = orderBootcoin.getId();
        buyerId = orderBootcoin.getBuyerId();
        sellerId = orderBootcoin.getSellerId();
        amount = orderBootcoin.getAmount();
        offeringPrice = orderBootcoin.getOfferingPrice();
        paymentMethod = orderBootcoin.getPaymentMethod();
        orderBootcoinState = orderBootcoin.getOrderBootcoinState();
    }

    public OrderBootcoin toOrderBootcoin() {
        OrderBootcoin orderBootcoin = new OrderBootcoin();
        orderBootcoin.setId(id);
        orderBootcoin.setBuyerId(buyerId);
        orderBootcoin.setSellerId(sellerId);
        orderBootcoin.setAmount(amount);
        orderBootcoin.setOfferingPrice(offeringPrice);
        orderBootcoin.setPaymentMethod(paymentMethod);
        orderBootcoin.setOrderBootcoinState(orderBootcoinState);
        return orderBootcoin;
    }
}
