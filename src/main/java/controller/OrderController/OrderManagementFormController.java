package controller.OrderController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Order;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class OrderManagementFormController extends Component implements Initializable {

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btndelete;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colOrderDate;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private JFXTextField txtCustomerId;

    @FXML
    private JFXTextField txtOrderDate;

    @FXML
    private JFXTextField txtOrderId;

    @FXML
    private TableView<Order> tblOrder;

    Stage placeOrderFormStage=new Stage();

    OrderManagementService orderManagementService=new OrderManagementController();
    ObservableList<Order> ordersList= FXCollections.observableArrayList();

    @FXML
    void btnAddOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String orderId=txtOrderId.getText();

        boolean isDeleted=orderManagementService.deleteOrder(orderId);

        if(isDeleted){
            JOptionPane.showConfirmDialog(this,"Order was Deleted");
            loadOrders();
        }
        else{
            JOptionPane.showConfirmDialog(this,"Order was not Deleted");
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));

        loadOrders();

        tblOrder.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) ->{
            if(newValue!=null){
                setSelectedValue(newValue);
            }
        }));

    }

    public void loadOrders(){

        ordersList.clear();
        ordersList=orderManagementService.getAllOrders();
        tblOrder.setItems(ordersList);

    }
    @FXML
    void btnPlaceOrderFormPerviewOnAction(ActionEvent event) throws IOException {
        placeOrderFormStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/PlaceOrder.fxml"))));
        placeOrderFormStage.show();
    }

    public void setSelectedValue(Order order){
        txtOrderId.setText(order.getOrderId());
        txtOrderDate.setText(order.getOrderDate().toString());
        txtCustomerId.setText(order.getCustomerId());
    }

}
