import java.awt.FlowLayout; import java.awt.event.ActionEvent; import
java.awt.event.ActionListener; import java.sql.Connection; import java.sql.DriverManager; import
java.sql.PreparedStatement; import java.sql.ResultSet; import java.sql.SQLException; import
javax.swing.JButton; import javax.swing.JFrame; import javax.swing.JLabel; import
javax.swing.JOptionPane; import javax.swing.JPasswordField; import javax.swing.JTextField;
public class LoginForm implements ActionListener { JFrame frame; JTextField userField;
JPasswordField passField; JButton loginBtn; // Database details String url =
"jdbc:mysql://localhost:3306/loginn"; // change DB name String dbUser = "root"; String dbPass =
"Gescoe#123"; LoginForm() { // Frame frame = new JFrame("Login System");
frame.setSize(350, 200); frame.setLayout(new FlowLayout());
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Components frame.add(new
JLabel("Username:")); userField = new JTextField(15); frame.add(userField); frame.add(new
JLabel("Password:")); passField = new JPasswordField(15); frame.add(passField); loginBtn =
new JButton("Login"); frame.add(loginBtn); loginBtn.addActionListener(this);
frame.setVisible(true); } public void actionPerformed(ActionEvent e) { String username =
userField.getText(); String password = new String(passField.getPassword()); // Input validation if
(username.isEmpty() || password.isEmpty()) { JOptionPane.showMessageDialog(frame, "Fields
cannot be empty!"); return; } try { // Load driver Class.forName("com.mysql.cj.jdbc.Driver"); //
Connect database Connection con = DriverManager.getConnection(url, dbUser, dbPass); //
PreparedStatement (safe) String query = "SELECT * FROM users WHERE username=? AND
password=?"; PreparedStatement pstmt = con.prepareStatement(query); pstmt.setString(1,
username); pstmt.setString(2, password); ResultSet rs = pstmt.executeQuery(); if (rs.next()) {
JOptionPane.showMessageDialog(frame, "Login Successful!"); } else {
JOptionPane.showMessageDialog(frame, "Invalid Username or Password!"); } // Close
connection con.close(); } catch (ClassNotFoundException ex) {
JOptionPane.showMessageDialog(frame, "Driver not found!"); } catch (SQLException ex) {
JOptionPane.showMessageDialog(frame, "Database Error: " + ex.getMessage()); } catch
(Exception ex) { JOptionPane.showMessageDialog(frame, "Unexpected Error!"); } } public static
void main(String[] args) { new LoginForm(); } }
