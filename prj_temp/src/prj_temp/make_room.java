package prj_temp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class make_room extends JPanel{
	private JPanel p_t, p_b, l1, l2;
	private JTextField title, pw;
	private JLabel l_title, l_pw;
	private JButton ok, cancel;
	private Frame n;
	private start F;
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(event.getSource() == ok) {
				JOptionPane.showMessageDialog(null, "�濡 �����մϴ�");
				n.dispose();
				F.changePanel();
				F.setTitle("Catch-ur-Crayon");
				if(waiting.getRoom_num() <4) {
					waiting.r[waiting.getRoom_num()].setEnabled(true);
					waiting.rn_increase(1);
				}
			} else {
				n.dispose();
			}
		}
	}
	
	public make_room(start f) {
		n = new Frame("�� �����");
		n.setSize(330,150);
		F=f;
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
		ButtonListener listen = new ButtonListener();
		ok.addActionListener(listen);
		cancel.addActionListener(listen);
	}
}
