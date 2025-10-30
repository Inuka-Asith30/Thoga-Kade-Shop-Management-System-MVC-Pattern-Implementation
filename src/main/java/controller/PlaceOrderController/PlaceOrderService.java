package controller.PlaceOrderController;

import model.Item;
import model.Order;

public interface PlaceOrderService {
    public Item priceInitialize(String itemCode);
    public String nameInitialize(String customerId);
    public String getOrderId();
    public boolean placeOrderDetails(Order order);
}
