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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Order;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OrderManagementFormController implements Initializable {

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

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));

        loadOrders();
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
}
