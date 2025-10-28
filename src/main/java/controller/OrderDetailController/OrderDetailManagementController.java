package controller.OrderDetailController;

import controller.DB.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Order;
import model.OrderDetails;

import java.sql.*;

public class OrderDetailManagementController implements OrderDetailManagementService{
    @Override
    public boolean addOrderDetail(OrderDetails orderDetails) {

        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO Order(OrderID, ItemCode, OrderQTY, Discount) VALUES(?,?,?,?);");
            preparedStatement.setObject(1,orderDetails.getOrderId());
            preparedStatement.setObject(2,orderDetails.getItemCode());
            preparedStatement.setObject(3,orderDetails.getOrderQty());
            preparedStatement.setObject(4,orderDetails.getDiscount());

            boolean isAdded=preparedStatement.execute();

            return isAdded;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean updateOrderDetail(OrderDetails orderDetails) {




        return false;
    }

    @Override
    public int deleteOrderDetail(String orderId) {
        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("delete from orderdetail where OrderId=?");
            preparedStatement.setObject(1,orderId);
            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<OrderDetails> getAllOrderDetail() {

        ObservableList<OrderDetails> orderDetailList= FXCollections.observableArrayList();

        try {
            Connection  connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("select * from orderdetail");
            ResultSet resultSet=preparedStatement.executeQuery();

            while(resultSet.next()){
                orderDetailList.add(
                        new OrderDetails(
                                resultSet.getString("OrderID"),
                                resultSet.getString("ItemCode"),
                                resultSet.getInt("OrderQTY"),
                                resultSet.getInt("Discount")
                        )
                );
            }

            return orderDetailList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
