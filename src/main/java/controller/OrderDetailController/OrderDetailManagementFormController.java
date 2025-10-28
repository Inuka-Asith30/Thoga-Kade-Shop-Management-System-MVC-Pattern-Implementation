package controller.OrderDetailController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.OrderDetails;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderDetailManagementFormController implements Initializable {

    ObservableList<OrderDetails> orderDetailList= FXCollections.observableArrayList();

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btndelete;

    @FXML
    private TableColumn<?, ?> colDiscount;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private TableColumn<?, ?> colOrderQty;

    @FXML
    private TableView<OrderDetails> tblOrderDetail;


    @FXML
    private JFXTextField txtDiscount;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private JFXTextField txtOrderId;

    @FXML
    private JFXTextField txtOrderQty;

    OrderDetailManagementService orderDetailManagementService=new OrderDetailManagementController();


    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        Integer orderQty=Integer.getInteger(txtOrderQty.getText());
        String orderId=txtOrderId.getText();
        Integer discount=Integer.getInteger(txtDiscount.getText());
        String itemCode=txtItemCode.getText();

        OrderDetails orderDetails=new OrderDetails(orderId,itemCode,orderQty,discount);
        orderDetailManagementService.updateOrderDetail(orderDetails);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colOrderQty.setCellValueFactory(new PropertyValueFactory<>("orderQty"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));

        loadOrderDetails();

        tblOrderDetail.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) ->{
            if(newValue!=null){
                setSelectedValue(newValue);
            }
        }));

    }
    public void loadOrderDetails(){
        orderDetailList.clear();
        orderDetailList=orderDetailManagementService.getAllOrderDetail();
        tblOrderDetail.setItems(orderDetailList);
    }
    public void setSelectedValue(OrderDetails orderDetails){
        txtOrderId.setText(orderDetails.getOrderId());
        txtDiscount.setText(String.valueOf(orderDetails.getDiscount()));
        txtItemCode.setText(orderDetails.getItemCode());
        txtOrderQty.setText(String.valueOf(orderDetails.getOrderQty()));
    }





}
