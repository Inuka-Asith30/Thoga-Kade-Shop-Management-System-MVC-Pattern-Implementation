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

        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO orders(OrderID, OrderDate, CustID) VALUES(?,?,?)");
            preparedStatement.setObject(1,order.getOrderId());
            preparedStatement.setObject(2,order.getOrderDate());
            preparedStatement.setObject(3,order.getCustomerId());

            int isAdded=preparedStatement.executeUpdate();

            if(isAdded==1){
                return true;
            }
            return false;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean updateOrder(Order order) {
        return false;
    }

    @Override
    public boolean deleteOrder(String orderId) {

        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from orders  where OrderId=?");
            preparedStatement.setObject(1,orderId);
            int isDeleted=preparedStatement.executeUpdate();

            if(isDeleted==1){
               return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    @Override
    public ObservableList<Order> getAllOrders() {

        ObservableList<Order> ordersList= FXCollections.observableArrayList();

        try {
            Connection connection= DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("select * from orders");
            ResultSet resultSet=preparedStatement.executeQuery();

            while(resultSet.next()){
                ordersList.add(
                        new Order(
                                resultSet.getString("OrderId"),
                                resultSet.getDate("orderDate").toLocalDate(),
                                resultSet.getString("CustID"))
                );

            }

            return ordersList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
