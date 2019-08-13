package thread.threadlocal;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author zhouliang
 * 在事务中使用threadlocal
 * 确保所有的sql都在同一个开启了事务的链接上执行（只需要保证这些sql的执行都是通过同一个连接）
 */
public class ThreadLocalInTransaction {


    static class JdbsUtils{

        private Connection connection = null;

        private static final ThreadLocal<Connection> THREAD_LOCAL = new ThreadLocal<>();

        /**
         * 从threadlocal中获取连接
         * @return
         * @throws SQLException
         */
        private static Connection getCon() throws SQLException {
            if(THREAD_LOCAL.get() == null){
                ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");
                DataSource dataSource = applicationContext.getBean("dataSource", DataSource.class);
                THREAD_LOCAL.set(dataSource.getConnection());
            }
            return THREAD_LOCAL.get();
        }

        /**
         * 开启事务
         * @throws SQLException
         */
        private static void startTransaction() throws SQLException {
            Connection connection = THREAD_LOCAL.get();
            if(connection == null){
                ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");
                DataSource dataSource = applicationContext.getBean("dataSource", DataSource.class);
                connection = dataSource.getConnection();
                THREAD_LOCAL.set(connection);
            }
            connection.setAutoCommit(false);
        }

        /**
         * 提交事务
         * @throws SQLException
         */
        private static void commitTransaction() throws SQLException {
            Connection connection = THREAD_LOCAL.get();
            if(connection != null){
                connection.commit();
            }
        }

        /**
         * 关闭数据库连接
         */
        private static void closeConnection(){
            Connection connection = THREAD_LOCAL.get();
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally {
                    // 注意点，最后一定要移除对应map里面的key，不然会造成内存泄漏
                    THREAD_LOCAL.remove();
                }
            }
        }
    }
}
