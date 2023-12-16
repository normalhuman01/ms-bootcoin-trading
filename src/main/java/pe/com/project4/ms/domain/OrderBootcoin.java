package pe.com.project4.ms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderBootcoin {
    private String id;
    private String buyerId;
    private String sellerId;
    private BigDecimal amount;
    private BigDecimal offeringPrice;
    private PaymentMethod paymentMethod;
    private OrderBootcoinState orderBootcoinState;
}
