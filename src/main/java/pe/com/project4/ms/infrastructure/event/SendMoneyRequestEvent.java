package pe.com.project4.ms.infrastructure.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.com.project4.ms.domain.OrderBootcoin;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendMoneyRequestEvent {
    private String transactionNumber;
    private String walletAccountSenderId;
    private String walletAccountReceiverId;
    private BigDecimal amount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime occurredAt;

    public SendMoneyRequestEvent(OrderBootcoin orderBootcoin, String transactionNumber) {
        this.transactionNumber = transactionNumber;
        this.walletAccountSenderId = orderBootcoin.getBuyerId();
        this.walletAccountReceiverId = orderBootcoin.getSellerId();
        this.amount = orderBootcoin.getOfferingPrice();
        this.occurredAt = LocalDateTime.now();
    }
}
