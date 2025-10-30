package controller.itemController;

import controller.DB.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemManagementController implements ItemManagementService{

    ObservableList<Item> itemDetailList= FXCollections.observableArrayList();

    @Override
    public boolean addItemDetails(Item item) {
        String SQL="INSERT INTO item(ItemCode, Description, PackSize, UnitPrice, QtyOnHand) VALUES(?,?,?,?,?);";

        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(SQL);
            preparedStatement.setObject(1,item.getItemCode());
            preparedStatement.setObject(2,item.getDescription());
            preparedStatement.setObject(3,item.getPackSize());
            preparedStatement.setObject(4,item.getUnitPrice());
            preparedStatement.setObject(5,item.getQtyOnHand());

            int isAdded=preparedStatement.executeUpdate();

            if(isAdded==1){
                return true;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return false;
    }

    @Override
    public boolean updateItemDetails(Item item) {

        String SQL="UPDATE item SET Description=?,PackSize=?,QtyOnHand=?,UnitPrice=? WHERE ItemCode=?";

        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(SQL);

            preparedStatement.setObject(1,item.getDescription());
            preparedStatement.setObject(2,item.getPackSize());
            preparedStatement.setObject(3,item.getQtyOnHand());
            preparedStatement.setObject(4,item.getUnitPrice());
            preparedStatement.setObject(5,item.getItemCode());

            int isUpdated=preparedStatement.executeUpdate();

            if(isUpdated==1){
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    @Override
    public boolean deleteItemDetails(String ItemCode) {

        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("DELETE FROM item where ItemCode=?");
            preparedStatement.setObject(1,ItemCode);
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
    public ObservableList<Item> getAllItemDetails() {

        String SQL="select * from item;";
        try {
            Connection connection= DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(SQL);
            ResultSet resultSet=preparedStatement.executeQuery();

            while(resultSet.next()){
                itemDetailList.add(
                        new Item(
                                resultSet.getString("ItemCode"),
                                resultSet.getString("Description"),
                                resultSet.getString("PackSize"),
                                resultSet.getDouble("UnitPrice"),
                                resultSet.getInt("QtyOnHand")
                        )
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return itemDetailList;
    }
}
