package controller.OrderDetailController;

import javafx.collections.ObservableList;
import model.OrderDetails;

public interface OrderDetailManagementService {
    boolean addOrderDetail(OrderDetails orderDetails);
    boolean updateOrderDetail(OrderDetails orderDetails);
    int deleteOrderDetail(String itemCode,String OrderId);
    ObservableList<OrderDetails> getAllOrderDetail();
}
