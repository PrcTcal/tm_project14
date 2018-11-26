package View;

import javax.swing.*;
import java.awt.*;

public class MakeRoomView extends JPanel{
	private JPanel p_t, p_b, l1, l2;
	private JTextField title, pw, round;
	private JLabel l_title, l_pw, l_theme, l_round;
	private JComboBox<String> theme;
	private JButton ok, cancel;
	private String[] words;
	
	private Frame n;
	
	//생성자 메소드
	public MakeRoomView() {
		n = new Frame("방만들기");
		n.setSize(330,280);
		n.add(this);
		n.setLocation(400, 100);
		setLayout(null);
		
		words = new String[]{"테마1", "테마2", "테마3", "테마4"};
		l_title = new JLabel("방 제목");
		l_title.setBounds(10,10,100,35);
		
		l_pw = new JLabel("비밀번호");
		l_pw.setBounds(10,50,100,35);
		
		title = new JTextField(20);
		title.setBounds(80,10,200,35);
		
		pw = new JTextField(20);
		pw.setBounds(80,50,200,35);
		
		l_theme = new JLabel("테마");
		l_theme.setBounds(10,90,100,35);
		
		l_round = new JLabel("라운드");
		l_round.setBounds(10,130,100,35);
		
		theme = new JComboBox<String>(words);
		theme.setBounds(80,90,75,35);
		
		round = new JTextField(3);
		round.setBounds(80,130,100,35);
		
		ok = new JButton("확인");
		ok.setBounds(55,180,100,35);
		cancel = new JButton("취소");
		cancel.setBounds(175,180,100,35);
		
		add(l_title);
		add(l_pw);
		add(l_theme);
		add(l_round);
		add(title);
		add(pw);
		add(theme);
		add(round);
		add(ok);
		add(cancel);

		n.setVisible(true);
	}

	
	
	public String[] getWords() {
		return words;
	}



	public void setWords(String words, int i) {
		this.words[i] = words;
	}



	public JTextField getRound() {
		return round;
	}



	public JComboBox<String> getTheme() {
		return theme;
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
