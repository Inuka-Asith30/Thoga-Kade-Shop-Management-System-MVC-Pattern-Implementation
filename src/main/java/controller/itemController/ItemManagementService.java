package controller.itemController;

import javafx.collections.ObservableList;
import model.Item;

public interface ItemManagementService {
    boolean addItemDetails(Item item);
    boolean updateItemDetails(Item item);
    boolean deleteItemDetails(String ItemCode);
    ObservableList<Item> getAllItemDetails();
}
