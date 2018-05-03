package com.zl.checkapi.mysql.jdbc;

import java.sql.*;

/**
 * @author zhouliang
 * @since 2018-04-25 16:48
 **/
public class CreateTableTest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://192.168.1.241:3306/risk?useUnicode=true&amp;characterEncoding=utf8";
        String userName = "bqs";
        String passWord = "123456";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, userName, passWord);
            String sql = "select * from usr_partner where partner_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "demo");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                System.out.println("execute successful");
                System.out.println(resultSet.getString(1));

            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(resultSet!=null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement!=null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
