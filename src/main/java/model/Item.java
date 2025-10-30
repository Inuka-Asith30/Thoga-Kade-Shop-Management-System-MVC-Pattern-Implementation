package model;

import lombok.*;

@Getter
@NoArgsConstructor
@Setter
@ToString
@AllArgsConstructor

public class Item {

    private String itemCode;
    private String description;
    private String packSize;
    private Double unitPrice;
    private Integer qtyOnHand;

}
