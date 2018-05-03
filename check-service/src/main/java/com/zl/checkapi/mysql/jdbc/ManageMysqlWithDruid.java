package com.zl.checkapi.mysql.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author zhouliang
 * @since 2018-04-25 17:31
 **/
public class ManageMysqlWithDruid {
    private final static Logger LOG = LoggerFactory.getLogger(ManageMysqlWithDruid.class);
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");
        LOG.info("spring容器启动成功!");
        DataSource dataSource = applicationContext.getBean("dataSource", DataSource.class);
        try {
            Connection connection = dataSource.getConnection();
            String sql = "select * from usr_partner where partner_id !=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "null");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                System.out.println(resultSet.getString(2));
            }
            //资源放回
            if(resultSet!=null){
                resultSet.close();
            }
            if(preparedStatement!=null){
                preparedStatement.close();
            }
            if(connection!=null){
                preparedStatement.close();
            }
            LOG.info("即将退出系统");
            System.exit(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
