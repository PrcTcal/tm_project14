package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class LoginView extends JPanel{
	private JPanel p_t, p_b, mid1, mid2;
	private JButton login, register;
	private JLabel id_l, pw_l;
	private JTextField id, pw;
	
	// 생성자 메소드
	public LoginView() {
		setSize(330, 150);
        setLayout(new GridLayout(2,1));
         
        mid1 = new JPanel();
        mid2 = new JPanel();
        mid1.setLayout(new FlowLayout(FlowLayout.CENTER));
        mid2.setLayout(new FlowLayout(FlowLayout.CENTER));
        
		p_t = new JPanel();
		p_b = new JPanel();
		id_l = new JLabel("ID");
		pw_l = new JLabel("PW");
		id = new JTextField(20);
		pw = new JTextField(20);
		login = new JButton("로그인");
		register = new JButton("회원가입");
		p_b.setLayout(new GridLayout(1,2));
		p_t.setLayout(new GridLayout(2,2));
		
		mid1.add(id_l);
		p_t.add(mid1);
		p_t.add(id);
		mid2.add(pw_l);
		p_t.add(mid2);
		p_t.add(pw);
		p_b.add(login);
		p_b.add(register);
		add(p_t);
		add(p_b);
	}

	public JButton getLogin() {
		return login;
	}

	public JButton getRegister() {
		return register;
	}
	
	public String getID() {
		return id.getText();
	}
	
	public String getPW() {
		return pw.getText();
	}
	
	public void resetPW() {
		pw.setText("");
	}
	
	public void resetText() {
		id.setText("");
		pw.setText("");
	}
}
