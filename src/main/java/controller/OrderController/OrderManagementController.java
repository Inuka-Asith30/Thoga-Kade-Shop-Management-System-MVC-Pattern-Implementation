package controller.OrderController;

import controller.DB.DBConnection;
import controller.OrderDetailController.OrderDetailManagementService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

        ObservableList<Order> ordersList= FXCollections.observableArrayList();

        try {
            Connection connection= DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("select * from order");
            ResultSet resultSet=preparedStatement.executeQuery();

            while(resultSet.next()){
                ordersList.add(
                        new Order(
                                resultSet.getString("OrderId"),
                                resultSet.getDate("orderDate").toLocalDate(),
                                resultSet.getString("CustomerId"))
                );

            }

            return ordersList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
