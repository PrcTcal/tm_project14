package prj_temp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LogIn extends JPanel{
	private JPanel p_t, p_b, mid1, mid2;
	private JButton login, register;
	private JLabel id_l, pw_l;
	private JTextField id, pw;
	private start F;
	private register rg;
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(event.getSource() == login) {
				JOptionPane.showMessageDialog(null, "로그인 되었습니다");
				F.changePanel();
				F.setTitle("대기실");
				F.setSize(800,600);
			} else {
				rg = new register();
			}
		}
	}
	
	public LogIn(start f) {
		setSize(330, 150);
        setLayout(new GridLayout(2,1));
         
        F = f;
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
		
		ButtonListener listen = new ButtonListener();
		login.addActionListener(listen);
		register.addActionListener(listen);

		setVisible(true);
		
		
	}

}
