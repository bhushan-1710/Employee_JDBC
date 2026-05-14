import java.awt.FlowLayout; import java.awt.GridLayout; import java.sql.Connection; import
java.sql.DriverManager; import java.sql.PreparedStatement; import java.sql.ResultSet; import
java.sql.Statement; import javax.swing.JButton; import javax.swing.JFrame; import
javax.swing.JLabel; import javax.swing.JOptionPane; import javax.swing.JPasswordField;
import javax.swing.JScrollPane; import javax.swing.JTextArea; import javax.swing.JTextField;
public class EmployeeGUI { static final String URL = "jdbc:mysql://localhost:3306/company1?
useSSL=false&serverTimezone=UTC"; static final String USER = "root"; static final String
PASSWORD = "Gescoe#123"; JFrame frame, loginFrame; JTextField nameField, deptField,
salaryField, idField, searchField; JTextArea area; Connection con; public EmployeeGUI() {
connectDB(); showLogin(); } // 🔌 DB Connection void connectDB() { try {
Class.forName("com.mysql.cj.jdbc.Driver"); con = DriverManager.getConnection(URL, USER,
PASSWORD); } catch (Exception e) { JOptionPane.showMessageDialog(null, "DB Error: " +
e.getMessage()); } } // 🔐 LOGIN SCREEN void showLogin() { loginFrame = new
JFrame("Login"); loginFrame.setSize(300, 200); loginFrame.setLayout(new GridLayout(3,2));
JTextField userField = new JTextField(); JPasswordField passField = new JPasswordField();
JButton loginBtn = new JButton("Login"); loginFrame.add(new JLabel("Username:"));
loginFrame.add(userField); loginFrame.add(new JLabel("Password:"));
loginFrame.add(passField); loginFrame.add(loginBtn); loginBtn.addActionListener(e -> { String
user = userField.getText(); String pass = new String(passField.getPassword()); // Simple login
(you can connect DB later) if(user.equals("admin") && pass.equals("1234")) {
loginFrame.dispose(); showMainUI(); } else { JOptionPane.showMessageDialog(loginFrame,
"Invalid Login"); } }); loginFrame.setVisible(true); } // 🖥️ MAIN UI void showMainUI() { frame =
new JFrame("Employee Management"); frame.setSize(500, 500); frame.setLayout(new
FlowLayout()); nameField = new JTextField(10); deptField = new JTextField(10); salaryField =
new JTextField(10); idField = new JTextField(5); searchField = new JTextField(10); area = new
JTextArea(15, 40); JScrollPane scroll = new JScrollPane(area); JButton addBtn = new
JButton("Add"); JButton viewBtn = new JButton("View"); JButton updateBtn = new
JButton("Update"); JButton deleteBtn = new JButton("Delete"); JButton searchBtn = new
JButton("Search"); frame.add(new JLabel("ID:")); frame.add(idField); frame.add(new
JLabel("Name:")); frame.add(nameField); frame.add(new JLabel("Dept:"));
frame.add(deptField); frame.add(new JLabel("Salary:")); frame.add(salaryField);
frame.add(addBtn); frame.add(viewBtn); frame.add(updateBtn); frame.add(deleteBtn);
frame.add(new JLabel("Search Name:")); frame.add(searchField); frame.add(searchBtn);
frame.add(scroll); // ➕ ADD addBtn.addActionListener(e -> { try { String sql = "INSERT INTO
employee1 (name, department, salary) VALUES (?, ?, ?)"; PreparedStatement ps =
con.prepareStatement(sql); ps.setString(1, nameField.getText()); ps.setString(2,
deptField.getText()); ps.setDouble(3, Double.parseDouble(salaryField.getText()));
ps.executeUpdate(); JOptionPane.showMessageDialog(frame, "Employee Added"); } catch
(Exception ex) { JOptionPane.showMessageDialog(frame, ex.getMessage()); } }); // 👀 VIEW
viewBtn.addActionListener(e -> { try { area.setText(""); Statement st = con.createStatement();
ResultSet rs = st.executeQuery("SELECT * FROM employee1"); while (rs.next()) { area.append(
rs.getInt("id") + " | " + rs.getString("name") + " | " + rs.getString("department") + " | " +
rs.getDouble("salary") + "\n" ); } } catch (Exception ex) {
JOptionPane.showMessageDialog(frame, ex.getMessage()); } }); // 🔄 UPDATE
updateBtn.addActionListener(e -> { try { String sql = "UPDATE employee1 SET salary=?
WHERE id=?"; PreparedStatement ps = con.prepareStatement(sql); ps.setDouble(1,
Double.parseDouble(salaryField.getText())); ps.setInt(2, Integer.parseInt(idField.getText()));
ps.executeUpdate(); JOptionPane.showMessageDialog(frame, "Updated"); } catch (Exception
ex) { JOptionPane.showMessageDialog(frame, ex.getMessage()); } }); // ❌ DELETE
deleteBtn.addActionListener(e -> { try { String sql = "DELETE FROM employee1 WHERE id=?";
PreparedStatement ps = con.prepareStatement(sql); ps.setInt(1,
Integer.parseInt(idField.getText())); ps.executeUpdate();
JOptionPane.showMessageDialog(frame, "Deleted"); } catch (Exception ex) {
JOptionPane.showMessageDialog(frame, ex.getMessage()); } }); // 🔍 SEARCH
searchBtn.addActionListener(e -> { try { area.setText(""); String sql = "SELECT * FROM
employee1 WHERE name LIKE ?"; PreparedStatement ps = con.prepareStatement(sql);

ps.setString(1, "%" + searchField.getText() + "%"); ResultSet rs = ps.executeQuery(); while
(rs.next()) { area.append( rs.getInt("id") + " | " + rs.getString("name") + " | " +
rs.getString("department") + " | " + rs.getDouble("salary") + "\n" ); } } catch (Exception ex) {
JOptionPane.showMessageDialog(frame, ex.getMessage()); } });
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); frame.setVisible(true); } public
static void main(String[] args) { new EmployeeGUI(); } }
