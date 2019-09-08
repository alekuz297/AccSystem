package util;

import com.sun.rowset.CachedRowSetImpl;
import java.sql.*;
public class DBUtil {

    private  static  final  String JDBC_DRIVER = "org.postgresql.Driver";
    private  static Connection conn =null;
   private static String url = "jdbc:postgresql://localhost:5432/Vertspapiri";
  private   static String login = "postgres";
   private static String password = "postgres";

    private static void  dbConnect() throws SQLException, ClassNotFoundException {
       try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Where your postgres JDBC DRIVER");
        e.printStackTrace();
        throw e;
        }
        System.out.println("Postgres JDBC Driver Registered!");
        try {
            conn= DriverManager.getConnection(url,login,password);

        } catch (SQLException e){
            System.out.println("Connection Failed! Check output console" + e);
            e.printStackTrace();
            throw e;
        }
        }
    private static void dbDisconnect() throws SQLException {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e){
            throw e;
        }
    }
    public static ResultSet dbExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        ResultSet resultSet = null;
        CachedRowSetImpl crs = null;
        try {
            dbConnect();
            System.out.println("Select statement: " + queryStmt + "\n");
            stmt = conn.createStatement();
           resultSet = stmt.executeQuery(queryStmt);
            crs = new CachedRowSetImpl();
            crs.populate(resultSet);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeQuery operation : " + e);
            throw e;
        } finally {
            if (resultSet != null) {
               resultSet.close();
            }
            if (stmt != null) {
               stmt.close();
            }
               dbDisconnect();
        }
            return crs;
    }
    public static void dbExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException {
            Statement stmt = null;
        try {
            dbConnect();
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlStmt);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeUpdate operation : " + e);
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
           dbDisconnect();
        }
    }
    public static void dbExecute(String sqlStmt) throws SQLException, ClassNotFoundException {
          Statement stmt = null;
        try {
            dbConnect();
            stmt = conn.createStatement();
            stmt.execute(sqlStmt);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeUpdate operation : " + e);
            throw e;
        } finally {
            if (stmt != null) {
            stmt.close();
            }
            dbDisconnect();
        }
    }
    public static Connection getCon() {
        Connection connection = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(url,login,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}












