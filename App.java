import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

public class App{
    public static void main(String[] args) {
        // init table in DB 
        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tp11?user=root&password=")){
            System.out.println("Connect to DB");
            try(Statement stmt = con.createStatement()){
                stmt.execute("CREATE TABLE IF NOT EXISTS countries ("
                + "id INT NULL AUTO_INCREMENT PRIMARY KEY, "
                + "country VARCHAR(50) UNIQUE NOT NULL"
                + ")");

                        System.out.println("Done.\ncreating table 'cities'...");
                        stmt.execute("CREATE TABLE IF NOT EXISTS cities ("
                        + "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
                        + "city VARCHAR(50) NOT NULL, "
                        + "countryid INT NOT NULL REFERENCES countries(id) , "
                        + "ucity VARCHAR(60) NOT NULL UNIQUE"
                        + ")");
                                System.out.println("Done.\n");

                                System.out.println("Done.\ncreating table 'hotel'...");
                                stmt.execute("CREATE TABLE IF NOT EXISTS hotel("
                                + "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
                                + "hotel VARCHAR(100) NOT NULL,"
                                + "countryid INT NOT NULL REFERENCES cities(id), "
                                + "stars TINYINT, "
                                + "cost DECIMAL (12,2),"
                                + "info VARCHAR(255)"

                                +")");
                                System.out.println("Done.\n");

                                System.out.println("Done. \ncreating table 'images'...");
                                stmt.execute("CREATE TABLE IF NOT EXISTS images("
                                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                                + "hotelid INT REFERENCES hotel(id),"
                                + "imagepath VARCHAR(256) NOT NULL"
                                +
                                
                                ")");
                                System.out.println("Done. \n");

                                System.out.println("Done. \ncreating table 'roles'");
                                stmt.execute("CREATE TABLE IF NOT EXISTS roles("
                                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                                + "role VARCHAR(20) UNIQUE NOT NULL"
                                +");");
                               

                                System.out.println("Done.\ncreating table 'users'");
                                stmt.execute("CREATE TABLE IF NOT EXISTS users("
                                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                                + "username VARCHAR(30) UNIQUE NOT NULL,"
                                + "pass VARCHAR(80),"
                                + "email VARCHAR(255) NULL, "
                                + "roleid INT NOT NULL REFERENCES roles(id),"
                                + "discount TINYINT,"
                                + "avatar VARCHAR(255) NULL"
                                +")");
                                System.out.println("Done.\n");



                                    
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}