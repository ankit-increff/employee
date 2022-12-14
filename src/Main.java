

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;  //1

/*
* 1. import java.sql
* 2. load and register the driver
* 3. create connection
* 4. create statement
* 5. execute a query
* 6. process the results
* 7.close
* */

import java.util.Properties;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {

        Properties props = new Properties();
        InputStream inStream = new FileInputStream("employee.properties");
        props.load(inStream);

        String url = "jdbc:mysql://localhost:3306/Employee";
        String pass = "pass";
        String username = "increff";

        Class.forName(props.getProperty("jdbc.driver"));  //2
        Connection con=DriverManager.getConnection(props.getProperty("jdbc.url"),props.getProperty("jdbc.username"),props.getProperty("jdbc.password")); //3

        select(con);
//        insert(con);

//        Statement stmt = con.createStatement();  //4 (returns object of statement)
//        ResultSet rs = stmt.executeQuery(query); //5
//        while(rs.next()) {
//            System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getInt(3)); //6
//        }
        con.close(); //7
    }

    private static void select(Connection con) throws SQLException{
        String query = "select * from employees";

        System.out.println("selecting employees");
        Statement stmt = con.createStatement();  //4 (returns object of statement)
        ResultSet rs = stmt.executeQuery(query); //5
        while(rs.next()) {
            System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getInt(3)); //6
        }
        stmt.close(); //7
    }

    private static void insert(Connection con) throws SQLException{
        System.out.println("inserting employees");

        Statement stmt = con.createStatement();  //4 (returns object of statement)
//        stmt.executeUpdate("insert into employees values(0, 'name_0', 30)"); //5
        for(int i=0;i<3;i++) {
            int id = i;
            int age = 30+2*i;
            String name = "name_"+i;
            String query = "insert into employees values("+id+", '"+name+"', "+age+")";
            System.out.println(query);
            stmt.executeUpdate(query);  //6
        }
        stmt.close(); //7
    }

    private static void delete(Connection con) throws SQLException{
//        String query = ;

        System.out.println("deleting all employees");
        Statement stmt = con.createStatement();  //4 (returns object of statement)
        ResultSet rs = stmt.executeQuery("select * from employees"); //5
//        while(rs.next()) {
//            System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getInt(3)); //6
//        }
//        stmt.close(); //7

//        .....yet to write
    }

}


/*
* pass statements instead of con to other functions
* closing con is necessary?
* throws sqlexception??
*
*
* */
