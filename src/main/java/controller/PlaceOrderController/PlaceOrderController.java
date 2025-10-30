package controller.PlaceOrderController;

import controller.DB.DBConnection;
import controller.OrderController.OrderManagementController;
import controller.OrderController.OrderManagementService;
import javafx.collections.ObservableList;
import model.Item;
import model.Order;
import model.OrderDetails;
import model.TableOrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaceOrderController implements PlaceOrderService{

    OrderManagementService orderManagementService=new OrderManagementController();

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

        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("select CustName from customer where CustID=?");
            preparedStatement.setObject(1,customerId);
            ResultSet resultSet=preparedStatement.executeQuery();

            String custName=null;
            while(resultSet.next()){
                custName= resultSet.getString("CustName");
            }
            return custName;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String getOrderId() {

        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("SELECT OrderID FROM orders ORDER BY OrderID DESC LIMIT 1");
            ResultSet resultSet=preparedStatement.executeQuery();

            String orderId=null;

            while(resultSet.next()){
                orderId=resultSet.getString("OrderID");
            }

            return orderId;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean placeOrderDetails(Order order, ObservableList<TableOrderDetail> tableOrderDetails) {

        boolean isAddedOrderTable=orderManagementService.addOrder(order);
        boolean isAddedOrderdetailsTable=true;
        OrderDetails setOrderTable=null;

        for(TableOrderDetail tableOrderDetail:tableOrderDetails){

            setOrderTable=new OrderDetails(order.getOrderId(),
                    tableOrderDetail.getItemCode(),
                    tableOrderDetail.getOrderQty(),
                    tableOrderDetail.getDiscount()
            );



            if(addOrderDetail(setOrderTable)!=true || updateItemTable(setOrderTable)!=true){
                isAddedOrderdetailsTable=false;
                break;
            }

        }



        return isAddedOrderdetailsTable && isAddedOrderTable;
    }

    private boolean addOrderDetail(OrderDetails orderDetails){
        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO orderdetail(OrderID, ItemCode, OrderQTY, Discount) VALUES(?,?,?,?)");
            preparedStatement.setObject(1,orderDetails.getOrderId());
            preparedStatement.setObject(2,orderDetails.getItemCode());
            preparedStatement.setObject(3,orderDetails.getOrderQty());
            preparedStatement.setObject(4,orderDetails.getDiscount());


            if(preparedStatement.executeUpdate()==1){
                return true;
            }
            return false;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateItemTable(OrderDetails orderDetails){
        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("select QtyOnHand from item where ItemCode=?");
            preparedStatement.setObject(1,orderDetails.getItemCode());
            ResultSet resultSet=preparedStatement.executeQuery();

            Double qtyOnHand =0.0;

            while (resultSet.next()){
                qtyOnHand =resultSet.getDouble("qtyOnHand");
            }
            qtyOnHand= qtyOnHand-orderDetails.getOrderQty();

            PreparedStatement preparedStatement2=connection.prepareStatement("UPDATE item SET QtyOnHand=? WHERE ItemCode=?");
            preparedStatement2.setObject(1,qtyOnHand);
            preparedStatement2.setObject(2,orderDetails.getItemCode());
            int isUpdated=preparedStatement2.executeUpdate();

            if(isUpdated==1){
                return true;
            }
            return false;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
