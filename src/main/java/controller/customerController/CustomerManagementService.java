package controller.customerController;

import javafx.collections.ObservableList;
import model.Customer;

public interface CustomerManagementService {
    boolean addCustomerDetails(Customer customer);
    boolean updateCustomerDetails(Customer customer);
    boolean deleteCustomerDetails(String CustomerID);
    ObservableList<Customer> getAllCustomerDetails();

}
