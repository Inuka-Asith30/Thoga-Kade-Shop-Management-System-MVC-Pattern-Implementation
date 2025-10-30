package model;

import javafx.scene.control.DatePicker;
import lombok.*;

import java.time.LocalDate;

@ToString
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor

public class Customer {

    private String customerID;
    private String name;
    private String title;
    private LocalDate dob;
    private Double salary;
    private String address;
    private String city;
    private String province;
    private String postalCode;

}
