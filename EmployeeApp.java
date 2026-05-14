import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class EmployeeApp {
    static final String URL = "jdbc:mysql://localhost:3306/company";
    static final String USER = "root";
    static final String PASSWORD = "Gescoe#123";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);

            int choice;

            do {
                System.out.println("\n1. Add Employee");
                System.out.println("2. View Employees");
                System.out.println("3. Update Employee");
                System.out.println("4. Delete Employee");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter name: ");
                        String name = sc.next();

                        System.out.print("Enter department: ");
                        String dept = sc.next();

                        System.out.print("Enter salary: ");
                        double salary = sc.nextDouble();

                        String insertQuery = "INSERT INTO employee (name, department, salary) VALUES (?, ?, ?)";
                        PreparedStatement ps1 = con.prepareStatement(insertQuery);

                        ps1.setString(1, name);
                        ps1.setString(2, dept);
                        ps1.setDouble(3, salary);

                        ps1.executeUpdate();

                        System.out.println("Employee added!");
                        break;

                    case 2:
                        String selectQuery = "SELECT * FROM employee";
                        Statement st = con.createStatement();
                        ResultSet rs = st.executeQuery(selectQuery);

                        while (rs.next()) {
                            System.out.println(
                                rs.getInt("id") + " " +
                                rs.getString("name") + " " +
                                rs.getString("department") + " " +
                                rs.getDouble("salary")
                            );
                        }
                        break;

                    case 3:
                        System.out.print("Enter ID to update: ");
                        int id = sc.nextInt();

                        System.out.print("Enter new salary: ");
                        double newSalary = sc.nextDouble();

                        String updateQuery = "UPDATE employee SET salary=? WHERE id=?";
                        PreparedStatement ps2 = con.prepareStatement(updateQuery);

                        ps2.setDouble(1, newSalary);
                        ps2.setInt(2, id);

                        ps2.executeUpdate();

                        System.out.println("Employee updated!");
                        break;

                    case 4:
                        System.out.print("Enter ID to delete: ");
                        int delId = sc.nextInt();

                        String deleteQuery = "DELETE FROM employee WHERE id=?";
                        PreparedStatement ps3 = con.prepareStatement(deleteQuery);

                        ps3.setInt(1, delId);

                        ps3.executeUpdate();

                        System.out.println("Employee deleted!");
                        break;
                }

            } while (choice != 5);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}