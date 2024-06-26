package qltc.DataAccessLayer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//String url = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=test;encrypt=false;trustServerCertificate=false";
public class connection {
    
    static final String URL = "jdbc:sqlserver://localhost\\\\SQLEXPRESS:1433;databaseName=QLTC;encrypt=false;trustServerCertificate=false";
    static final String USER = "sa";
    static final String PASS = "admin123";
    
    public static Connection connectionDB(){
        try {            
            Connection connection = DriverManager.getConnection(URL, USER, PASS);
            return connection;
        }
            // Kết nối thành công
            
        catch (SQLException e) {  
            e.printStackTrace();
        }
        return null;
    }
public static void CloseConnect(Connection c){
    try{
        if (c != null )
        {c.close();}
    }
    catch (Exception e){
        e.printStackTrace();
    }
}}
// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.sql.Statement;

// public class AccessDatabaseInFolderWithSqlServer {

//     public static void main(String[] args) {
//         // Database file path
//         String dbFilePath = "C:\\path\\to\\database.mdf";

//         // JDBC driver and connection string
//         String jdbcDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//         String jdbcUrl = "jdbc:sqlserver:;databaseName=master";

//         // Connect to the database
//         try {
//             Class.forName(jdbcDriver);
//             Connection connection = DriverManager.getConnection(jdbcUrl);

//             // Specify the database file location
//             connection.setCatalog(dbFilePath);



//             // Create a statement
//             Statement statement = connection.createStatement();

//             // Execute a query
//             ResultSet resultSet = statement.executeQuery("SELECT * FROM TableName");

//             // Process the results
//             while (resultSet.next()) {
//                 System.out.println("Column1: " + resultSet.getString("Column1"));
//                 System.out.println("Column2: " + resultSet.getString("Column2"));
//             }

//             // Close the result set, statement, and connection
//             resultSet.close();
//             statement.close();
//             connection.close();
//         } catch (ClassNotFoundException | SQLException e) {
//             e.printStackTrace();
//         }
//     }
// }