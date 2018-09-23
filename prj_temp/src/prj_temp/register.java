package prj_temp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class register extends JPanel{
	private JPanel p_t, p_b, l1, l2;
	private JTextField id, pw;
	private JLabel l_id, l_pw;
	private JButton reg, cancel;
	private Frame n;
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(event.getSource() == reg) {
				JOptionPane.showMessageDialog(null, "회원가입 되었습니다");
				n.dispose();
			} else {
				n.dispose();
			}
		}
	}
	
	public register() {
		n = new Frame("회원 가입");
		n.setSize(330,150);
		
		n.add(this);
		
		
		n.setLocation(400, 100);
		
		setLayout(new GridLayout(2,1));
		
		l1 = new JPanel();
	    l2 = new JPanel();
	    l1.setLayout(new FlowLayout(FlowLayout.CENTER));
	    l2.setLayout(new FlowLayout(FlowLayout.CENTER));
	       
		p_t = new JPanel();
		p_b = new JPanel();
		l_id = new JLabel("ID");
		l_pw = new JLabel("PW");
		id = new JTextField(20);
		pw = new JTextField(20);
		reg = new JButton("가입");
		cancel = new JButton("취소");
		p_b.setLayout(new GridLayout(1,2));
		p_t.setLayout(new GridLayout(2,2));
		l1.add(l_id);
		p_t.add(l1);
		p_t.add(id);
		l2.add(l_pw);
		p_t.add(l2);
		p_t.add(pw);
		p_b.add(reg);
		p_b.add(cancel);
		add(p_t);
		add(p_b);
		n.setVisible(true);
		ButtonListener listen = new ButtonListener();
		reg.addActionListener(listen);
		cancel.addActionListener(listen);
		
	}
}
