package controller.PlaceOrderController;

import model.Item;

public interface PlaceOrderService {
    public Item priceInitialize(String itemCode);
    public String nameInitialize(String customerId);
}
