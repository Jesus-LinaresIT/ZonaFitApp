package zona_fit.conection;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conection {
    public static Connection getConection(){
        Connection conection = null;
        var data_base = "zona_fit";
        var url = "jdbc:mysql://localhost:3306/" + data_base;
        var user = "root";
        var password = "1234";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conection = DriverManager.getConnection(url, user, password);
        }catch (Exception e){
            System.out.println("Error connect to data base " + e);
        }
        return conection;
    }
}


