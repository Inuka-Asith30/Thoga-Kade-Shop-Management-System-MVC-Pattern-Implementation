package model;

import javafx.scene.control.DatePicker;
import lombok.*;

import java.time.LocalDate;

@ToString
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor

public class Order {
    private String orderId;
    private LocalDate orderDate;
    private String customerId;
}
