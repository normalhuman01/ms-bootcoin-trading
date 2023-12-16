package pe.com.project4.ms.infrastructure.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.com.project4.ms.domain.OrderBootcoin;
import pe.com.project4.ms.domain.OrderBootcoinState;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseRequisitionEvent {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime occurredAt;
    private OrderBootcoin orderBootcoin;

    public PurchaseOrderEvent toPurcharseOrderEvent() {
        PurchaseOrderEvent purchaseOrderEvent = new PurchaseOrderEvent();
        orderBootcoin.setOrderBootcoinState(OrderBootcoinState.ACCEPT);
        purchaseOrderEvent.setOrderBootcoin(orderBootcoin);
        purchaseOrderEvent.setOccurredAt(LocalDateTime.now());
        return purchaseOrderEvent;
    }

}
