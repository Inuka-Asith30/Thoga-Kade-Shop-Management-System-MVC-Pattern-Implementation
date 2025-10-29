package controller.PlaceOrderController;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Item;

public class PlaceOrderFormController {

    @FXML
    private JFXButton btnAddtoCart;

    @FXML
    private JFXButton btnPlaceOrder;

    @FXML
    private TableColumn<?, ?> colDescription1;

    @FXML
    private TableColumn<?, ?> colDiscount1;

    @FXML
    private TableColumn<?, ?> colItemCode1;

    @FXML
    private TableColumn<?, ?> colQuantity1;

    @FXML
    private TableColumn<?, ?> colTotal1;

    @FXML
    private TableColumn<?, ?> colUnitPrice1;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblDescripstion;

    @FXML
    private Label lblDiscount;

    @FXML
    private Label lblNetTotal;

    @FXML
    private Label lblPrice;

    @FXML
    private TableView<?> tblAddToCart;

    @FXML
    private JFXTextField txtCustomerId;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private JFXTextField txtQuantity;

    PlaceOrderService placeOrderService=new PlaceOrderController();

    @FXML
    void btnAddtoCartOnAction(ActionEvent event) {

    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {

    }

    @FXML
    void txtCustomerIdOnAction(ActionEvent event) {
        String customerId=txtCustomerId.getText();

    }

    @FXML
    void txtItemCodeOnAction(ActionEvent event) {
        String itemCode=txtItemCode.getText();
        Item item=placeOrderService.priceInitialize(itemCode);

        if(item==null){

        }
        else{
            lblDescripstion.setText(item.getDescription());
            lblPrice.setText(String.valueOf(item.getUnitPrice()));
            lblDiscount.setText("0");
        }
    }
    @FXML
    void txtQuantityOnAction(ActionEvent event) {
        Double unitPrice=Double.parseDouble(lblPrice.getText());
        Integer qty=Integer.parseInt(txtQuantity.getText());


        lblNetTotal.setText(String.valueOf(unitPrice*qty));

    }

}

