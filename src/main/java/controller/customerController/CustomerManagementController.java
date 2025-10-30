package controller.customerController;

import controller.DB.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerManagementController implements CustomerManagementService {

    @Override
    public boolean addCustomerDetails(Customer customer) {

        String SQL="INSERT INTO customer(CustID, CustTitle, CustName, DOB, salary,CustAddress,City,Province,PostalCode) VALUES(?,?,?,?,?,?,?,?,?);";

        try {
            Connection connection= DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(SQL);

            preparedStatement.setObject(1,customer.getCustomerID());
            preparedStatement.setObject(2,customer.getTitle());
            preparedStatement.setObject(3,customer.getName());
            preparedStatement.setObject(4,customer.getDob());
            preparedStatement.setObject(5,customer.getSalary());
            preparedStatement.setObject(6,customer.getAddress());
            preparedStatement.setObject(7,customer.getCity());
            preparedStatement.setObject(8,customer.getProvince());
            preparedStatement.setObject(9,customer.getPostalCode());

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
    public boolean updateCustomerDetails(Customer customer) {

        String SQL="UPDATE customer SET CustTitle=?,CustName=?,DOB=?,salary=?,CustAddress=?,City=?,Province=?,PostalCode=? WHERE CustID=?";

        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(SQL);


            preparedStatement.setObject(1,customer.getTitle());
            preparedStatement.setObject(2,customer.getName());
            preparedStatement.setObject(3,customer.getDob());
            preparedStatement.setObject(4,customer.getSalary());
            preparedStatement.setObject(5,customer.getAddress());
            preparedStatement.setObject(6,customer.getCity());
            preparedStatement.setObject(7,customer.getProvince());
            preparedStatement.setObject(8,customer.getPostalCode());
            preparedStatement.setObject(9,customer.getCustomerID());

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
    public boolean deleteCustomerDetails(String customerID) {

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("DELETE FROM customer where CustID=?");
            preparedStatement.setObject(1,customerID);

            if(preparedStatement.executeUpdate()==1){
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ObservableList<Customer> getAllCustomerDetails() {

        ObservableList<Customer> customerDetailsList = FXCollections.observableArrayList();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from customer;");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customerDetailsList.add(
                        new Customer(
                                resultSet.getString("CustID"),
                                resultSet.getString("CustName"),
                                resultSet.getString("CustTitle"),
                                resultSet.getDate("DOB").toLocalDate(),
                                resultSet.getDouble("salary"),
                                resultSet.getString("CustAddress"),
                                resultSet.getString("City"),
                                resultSet.getString("Province"),
                                resultSet.getString("PostalCode")
                        )
                );
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return customerDetailsList;
    }


}
