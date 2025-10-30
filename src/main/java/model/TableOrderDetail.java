package model;

import lombok.*;

@ToString
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class TableOrderDetail {
    private String itemCode;
    private String description;
    private Integer orderQty;
    private Double unitPrice;
    private Integer discount;
    private Double total;
}
