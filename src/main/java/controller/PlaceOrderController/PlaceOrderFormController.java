package controller.PlaceOrderController;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Item;
import model.Order;
import model.TableOrderDetail;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class PlaceOrderFormController extends Component implements Initializable{

    @FXML
    private JFXButton btnAddtoCart;

    @FXML
    private JFXButton btnPlaceOrder;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colDiscount;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

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
    private DatePicker dPOrderDate;

    @FXML
    private TableView<TableOrderDetail> tblAddToCart;

    @FXML
    private JFXTextField txtCustomerId;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private JFXTextField txtQuantity;

    @FXML
    private JFXTextField txtOrderId;

    PlaceOrderService placeOrderService=new PlaceOrderController();

    private ObservableList<TableOrderDetail> tableOrderDetail= FXCollections.observableArrayList();

    Double newTotal=0.0;


    @FXML
    void btnAddtoCartOnAction(ActionEvent event) {
        String itemCode=txtItemCode.getText();
        String description=lblDescripstion.getText();
        Double unitPrice=Double.parseDouble(lblPrice.getText());
        Integer orderQty=Integer.parseInt(txtQuantity.getText());
        Integer discount=Integer.parseInt(lblDiscount.getText());


        Integer qty=Integer.parseInt(txtQuantity.getText());
        Double total=unitPrice*qty;

        tableOrderDetail.add(new TableOrderDetail(itemCode,description,orderQty,unitPrice,discount,total));

        newTotal=newTotal+total;
        lblNetTotal.setText(String.valueOf(newTotal));
        tblAddToCart.setItems(tableOrderDetail);


    }


    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        String orderId=txtOrderId.getText();
        LocalDate orderDate=dPOrderDate.getValue();
        String custID=txtCustomerId.getText();

        String itemCode=txtItemCode.getText();
        String description=lblDescripstion.getText();
        Double unitPrice=Double.parseDouble(lblPrice.getText());
        Integer orderQty=Integer.parseInt(txtQuantity.getText());
        Integer discount=Integer.parseInt(lblDiscount.getText());


        boolean isAdded=placeOrderService.placeOrderDetails(new Order(orderId,orderDate,custID),tableOrderDetail);

        if(isAdded){
            JOptionPane.showConfirmDialog(this,"Order was Placed");
            getNewOrderId();
            tableOrderDetail.clear();
            tblAddToCart.setItems(tableOrderDetail);
        }
        else{
            JOptionPane.showConfirmDialog(this,"Order was not Placed");
        }


    }

    @FXML
    void txtCustomerIdOnAction(ActionEvent event) {
        String customerId=txtCustomerId.getText();
        String custName=placeOrderService.nameInitialize(customerId);

        if(custName==null){
            JOptionPane.showConfirmDialog(this,"customer not Found");
        }
        else{
            lblCustomerName.setText(custName);
        }
    }

    @FXML
    void txtItemCodeOnAction(ActionEvent event) {
        String itemCode=txtItemCode.getText();
        Item item=placeOrderService.priceInitialize(itemCode);

        if(item==null){
            JOptionPane.showConfirmDialog(this,"Item not Found");
        }
        else{
            lblDescripstion.setText(item.getDescription());
            lblPrice.setText(String.valueOf(item.getUnitPrice()));
            lblDiscount.setText("0");
        }
    }
    @FXML
    void txtQuantityOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("orderQty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        LocalDate currentDate = LocalDate.now();
        dPOrderDate.setValue(currentDate);

        getNewOrderId();



    }

    public void getNewOrderId(){
        String lastOrderId=placeOrderService.getOrderId();
        String newOrderId=null;

        if(lastOrderId!=null){
            lastOrderId=lastOrderId.split("[A-Z]")[1];//D060-->060
            newOrderId=String.format("D%03d",(Integer.parseInt(lastOrderId)+1));
            //System.out.println(newOrderId);

            txtOrderId.setText(newOrderId);
        }
        else{
            txtOrderId.setText("D001");
        }
    }

}

