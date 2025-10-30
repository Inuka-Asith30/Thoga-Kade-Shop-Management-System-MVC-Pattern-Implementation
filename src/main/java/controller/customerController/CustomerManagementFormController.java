package controller.customerController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CustomerManagementFormController extends Component implements Initializable {

    ObservableList<Customer> customerDetailsList=FXCollections.observableArrayList();

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btndelete;

    @FXML
    private JFXComboBox<String> cmbProvince;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCity;

    @FXML
    private TableColumn<?, ?> colCustomerID;

    @FXML
    private TableColumn<?, ?> colDOB;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPostalcode;

    @FXML
    private TableColumn<?, ?> colProvince;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private TableView<Customer> tblCustomerDetails;

    @FXML
    private DatePicker dPDateOfBith;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtCity;

    @FXML
    private JFXTextField txtCustomerId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPostalCode;

    @FXML
    private JFXTextField txtSalary;


    @FXML
    private JFXComboBox<String> cmbTitle;


    CustomerManagementService customerManagementService = new CustomerManagementController();

    @FXML
    void btnAddOnAction(ActionEvent event) {

        String customerId = txtCustomerId.getText();
        String address = txtAddress.getText();
        String city = txtCity.getText();
        Double salary = Double.parseDouble(txtSalary.getText());
        String title = cmbTitle.getValue();
        String postalCode = txtPostalCode.getText();
        String name = txtName.getText();
        LocalDate dob = dPDateOfBith.getValue();
        String province = cmbProvince.getValue();


        boolean isAdded=customerManagementService.addCustomerDetails(
                new Customer(customerId, name, title, dob, salary, address, city, province, postalCode)
        );
        if(isAdded){
            JOptionPane.showConfirmDialog(this,"Added Successfully");
            loadCustomerDetails();
        }
        else{
            JOptionPane.showConfirmDialog(this,"Added not Successfully");
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        Boolean isDeleted=customerManagementService.deleteCustomerDetails(txtCustomerId.getText());

        if(isDeleted){
            JOptionPane.showConfirmDialog(this,"Deleted Sucessfully");
            loadCustomerDetails();
        }
        else{
            JOptionPane.showConfirmDialog(this,"Deleted not Sucessfully");
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        String customerId = txtCustomerId.getText();
        String address = txtAddress.getText();
        String city = txtCity.getText();
        Double salary = Double.parseDouble(txtSalary.getText());
        String title = cmbTitle.getValue();
        String postalCode = txtPostalCode.getText();
        String name = txtName.getText();
        LocalDate dob = dPDateOfBith.getValue();
        String province = cmbProvince.getValue();


        boolean isUpdated=customerManagementService.updateCustomerDetails(
                new Customer(customerId, name, title, dob, salary, address, city, province, postalCode)
        );

        if(isUpdated){
            JOptionPane.showConfirmDialog(this,"Updated Successfully");
            loadCustomerDetails();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<String> provinceList = FXCollections.observableArrayList(
                "Southern",
                "Western",
                "Eastern",
                "Northern",
                "Sabaragamuwa",
                "Wayamba",
                "Central"
        );
        cmbProvince.setItems(provinceList);

        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colPostalcode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));

        ObservableList<String> titleList = FXCollections.observableArrayList(
                "Mr",
                "Miss",
                "Ms"
        );
        cmbTitle.setItems(titleList);

        loadCustomerDetails();

        tblCustomerDetails.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) ->{
            if(newValue!=null){
                setSelectedValue(newValue);

            }
        }));

    }

    public void loadCustomerDetails() {

        customerDetailsList.clear();
        customerDetailsList=customerManagementService.getAllCustomerDetails();
        tblCustomerDetails.setItems(customerDetailsList);
    }

    public void setSelectedValue(Customer customer){
        txtCustomerId.setText(customer.getCustomerID());
        txtName.setText(customer.getName());
        txtCity.setText(customer.getCity());
        txtAddress.setText(customer.getAddress());
        txtSalary.setText(String.valueOf(customer.getSalary()));
        txtPostalCode.setText(customer.getPostalCode());
        cmbTitle.setValue(customer.getTitle());
        cmbProvince.setValue(customer.getProvince());
        dPDateOfBith.setValue(customer.getDob());
    }




}
