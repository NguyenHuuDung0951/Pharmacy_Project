package com.example.pharmacy_project.dao;

import com.example.pharmacy_project.connectDB.ConnectDB;
import com.example.pharmacy_project.entities.ChucVu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ChucVuDao {
    public ChucVuDao(){

    }
    public ObservableList<String> getAllChucVu(){
        ObservableList<String> dsCv = FXCollections.observableArrayList();
        try{
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "Select *from ChucVu";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
               dsCv.add(rs.getString("tenChucVu"));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return dsCv;
    }


}
