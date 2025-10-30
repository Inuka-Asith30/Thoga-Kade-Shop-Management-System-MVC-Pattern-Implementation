package controller.PlaceOrderController;

import javafx.collections.ObservableList;
import model.Item;
import model.Order;
import model.OrderDetails;
import model.TableOrderDetail;

public interface PlaceOrderService {
    public Item priceInitialize(String itemCode);
    public String nameInitialize(String customerId);
    public String getOrderId();
    public boolean placeOrderDetails(Order order, ObservableList<TableOrderDetail> tableOrderDetail);
    public boolean updateItemTable(OrderDetails orderDetails);
}
