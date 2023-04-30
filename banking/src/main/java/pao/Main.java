package pao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import pao.Utils.Demo;

/**
 * Hello world!
 *
 */
public class Main 
{
    public static void main( String[] args )
    {
        Connection connection;
        try{
            String url = "jdbc:mysql://localhost:3306/bank";
            String user = "root";
            String password = "1234";

            connection = DriverManager.getConnection(url, user, password);
            System.out.println("It works");
        }catch (SQLException e){
            System.out.println(e.toString());

            return;
        }
        
        Demo demo = new Demo(connection);
        // demo customer service
        demo.customer();

        // demo account service
        demo.account();
        
        // demo transaction service
        demo.transaction();

        // demo card service
        demo.card();
    }
}
