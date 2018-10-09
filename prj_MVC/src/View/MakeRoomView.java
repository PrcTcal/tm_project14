package View;

import javax.swing.*;
import java.awt.*;

public class MakeRoomView extends JPanel{
	private JPanel p_t, p_b, l1, l2;
	private JTextField title, pw;
	private JLabel l_title, l_pw;
	private JButton ok, cancel;
	private Frame n;
	
	//������ �޼ҵ�
	public MakeRoomView() {
		n = new Frame("�� �����");
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
		l_title = new JLabel("�� ����");
		l_pw = new JLabel("��й�ȣ");
		title = new JTextField(20);
		pw = new JTextField(20);
		ok = new JButton("Ȯ��");
		cancel = new JButton("���");
		p_b.setLayout(new GridLayout(1,2));
		p_t.setLayout(new GridLayout(2,2));
		l1.add(l_title);
		p_t.add(l1);
		p_t.add(title);
		l2.add(l_pw);
		p_t.add(l2);
		p_t.add(pw);
		p_b.add(ok);
		p_b.add(cancel);
		add(p_t);
		add(p_b);
		n.setVisible(true);
	}


	public JButton getOk() {
		return ok;
	}


	public JButton getCancel() {
		return cancel;
	}
	
	public Frame getN() {
		return n;
	}
	
	public String getTitle() {
		return title.getText();
	}
	
	public String getPW() {
		return pw.getText();
	}
}
