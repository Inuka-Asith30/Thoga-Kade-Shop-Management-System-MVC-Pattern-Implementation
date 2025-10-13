package controller.OrderController;

import controller.OrderDetailController.OrderDetailManagementService;
import javafx.collections.ObservableList;
import model.Order;

public class OrderManagementController implements OrderManagementService {
    @Override
    public boolean addOrder(Order order) {
        return false;
    }

    @Override
    public boolean updateOrder(Order order) {
        return false;
    }

    @Override
    public int deleteOrder(String orderId) {
        return 0;
    }

    @Override
    public ObservableList<Order> getAllOrders() {
        return null;
    }
}
