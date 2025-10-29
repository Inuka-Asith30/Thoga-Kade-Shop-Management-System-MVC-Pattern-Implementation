package controller.PlaceOrderController;

import controller.DB.DBConnection;
import model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaceOrderController implements PlaceOrderService{
    @Override
    public Item priceInitialize(String itemCode) {
        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("select * from item where ItemCode=?");
            preparedStatement.setObject(1,itemCode);
            ResultSet resultSet=preparedStatement.executeQuery();

            Item item = null;
            while(resultSet.next()){
                item=new Item(
                        resultSet.getString("ItemCode"),
                        resultSet.getString("Description"),
                        resultSet.getString("PackSize"),
                        resultSet.getDouble("UnitPrice"),
                        resultSet.getInt("QtyOnHand")
                );
            }
            return item;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String nameInitialize(String customerId) {
        return "";
    }
}
