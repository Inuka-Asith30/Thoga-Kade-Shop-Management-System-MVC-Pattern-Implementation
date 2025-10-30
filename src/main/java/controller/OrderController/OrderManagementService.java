package controller.OrderController;

import javafx.collections.ObservableList;
import model.Order;

public interface OrderManagementService {
    boolean addOrder(Order order);
    boolean updateOrder(Order order);
    boolean deleteOrder(String orderId);
    ObservableList<Order> getAllOrders();
}
