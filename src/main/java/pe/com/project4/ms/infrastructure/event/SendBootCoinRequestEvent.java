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
public class SendBootCoinRequestEvent {
    private String transactionNumber;
    private String walletAccountSenderId;
    private String walletAccountReceiverId;
    private BigDecimal amount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime occurredAt;

    public SendBootCoinRequestEvent(OrderBootcoin orderBootcoin, String transactionNumber) {
        this.transactionNumber = transactionNumber;
        this.walletAccountSenderId = orderBootcoin.getSellerId();
        this.walletAccountReceiverId = orderBootcoin.getBuyerId();
        this.amount = orderBootcoin.getAmount();
        this.occurredAt = LocalDateTime.now();
    }
}
