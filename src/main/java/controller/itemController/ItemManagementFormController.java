package controller.itemController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Item;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ItemManagementFormController extends Component implements Initializable {

    ObservableList<Item> itemDetailList= FXCollections.observableArrayList();

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btndelete;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colPackSize;

    @FXML
    private TableColumn<?, ?> colQtyOnHand;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private JFXTextField txtPackSize;

    @FXML
    private JFXTextField txtQtyOnHand;

    @FXML
    private JFXTextField txtUnitPrice;

    @FXML
    private TableView<Item> tblItems;

    @FXML
    private JFXTextArea txtDescription;

    ItemManagementService itemManagementService=new ItemManagementController();

    @FXML
    void btnAddOnAction(ActionEvent event) {

        String itemCode=txtItemCode.getText();
        String description=txtDescription.getText();
        String packSize=txtPackSize.getText();
        Double unitPrice=Double.parseDouble(txtUnitPrice.getText());
        Integer qtyOnHand=Integer.parseInt(txtQtyOnHand.getText());

        boolean isAdded=itemManagementService.addItemDetails(new Item(itemCode,description,packSize,unitPrice,qtyOnHand));

        if(isAdded){
            JOptionPane.showConfirmDialog(this,"Added Successfully");
            loadItemDetails();
        }
        else{
            JOptionPane.showConfirmDialog(this,"Added not Successfully");
        }

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        String itemCode=txtItemCode.getText();
        boolean isDeleted=itemManagementService.deleteItemDetails(itemCode);

        if(isDeleted){
            JOptionPane.showConfirmDialog(this,"Deleted Successfully");
            loadItemDetails();
        }
        else{
            JOptionPane.showConfirmDialog(this,"Deleted not Successfully");
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        String itemCode=txtItemCode.getText();
        String description=txtDescription.getText();
        String packSize=txtPackSize.getText();
        Double unitPrice=Double.parseDouble(txtUnitPrice.getText());
        Integer qtyOnHand=Integer.parseInt(txtQtyOnHand.getText());

        boolean isUpdated=itemManagementService.updateItemDetails(new Item(itemCode,description,packSize,unitPrice,qtyOnHand));

        if(isUpdated){
            JOptionPane.showConfirmDialog(this,"Updated Successfully");
            loadItemDetails();
        }
        else{
            JOptionPane.showConfirmDialog(this,"Updated not Successfully");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPackSize.setCellValueFactory(new PropertyValueFactory<>("packSize"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));

        loadItemDetails();

        tblItems.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) ->{
            if(newValue!=null){
                setSelectedValue(newValue);
            }
        }));


    }
    public void loadItemDetails(){

        itemDetailList.clear();
        itemDetailList=itemManagementService.getAllItemDetails();
        tblItems.setItems(itemDetailList);

    }
    public void setSelectedValue(Item newValue){

        txtItemCode.setText(newValue.getItemCode());
        txtDescription.setText(newValue.getDescription());
        txtPackSize.setText(newValue.getPackSize());
        txtQtyOnHand.setText(String.valueOf(newValue.getQtyOnHand()));
        txtUnitPrice.setText(String.valueOf(newValue.getUnitPrice()));

    }



}
