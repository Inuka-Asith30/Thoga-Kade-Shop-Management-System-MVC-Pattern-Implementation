package model;

import lombok.*;

@ToString
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor

public class OrderDetails {
    private String orderId;
    private String itemCode;
    private Integer orderQty;
    private Integer discount;

}
