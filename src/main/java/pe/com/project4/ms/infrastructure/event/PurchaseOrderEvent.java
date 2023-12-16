package pe.com.project4.ms.infrastructure.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.com.project4.ms.domain.OrderBootcoin;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrderEvent {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime occurredAt;
    private OrderBootcoin orderBootcoin;
}
