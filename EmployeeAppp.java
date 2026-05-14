import java.sql.Connection; import java.sql.DriverManager; public class EmployeeAppp { public
static void main(String[] args) { try { Class.forName("com.mysql.cj.jdbc.Driver"); Connection con
= DriverManager.getConnection( "jdbc:mysql://localhost:3306/company1", "root", "Gescoe#123"
); System.out.println("Connected successfully!"); } catch (Exception e) { e.printStackTrace(); } } }
