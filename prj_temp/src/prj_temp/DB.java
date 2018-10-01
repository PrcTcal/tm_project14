package prj_temp;
import java.sql.*;
public class DB {
	public Connection connection;	// DB�� �����ϴ� ��ü
	public Statement st;			// DBMS���� ������ �������� �����ϴ� ��ü
	public ResultSet rs;			// st�� DBMS���� �������� ������ �� ������ ����� �����ϴ� ��ü
	
	// ������ �޼ҵ�
	public DB() {
		connection = null;
		st = null;
		rs = null;
	}
	
	// client�� DB�� �����ϴ� �޼ҵ�
	public void connect() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		// JDBC�� ���� connection�� (ip�ּ� = localhost) (��Ʈ��ȣ = 3306) (DataBase�� = db1)
		// (MySQL ID = temp) (MySQL PW = 1234)�� �Է��Ͽ� ����
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1?serverTimezone=UTC" , "temp", "1234");
		st = connection.createStatement();
	}
	
	
}
