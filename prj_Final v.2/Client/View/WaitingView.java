package View;
import Starter.Application;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java.awt.*;
import java.awt.event.*;
public class WaitingView extends JPanel{

	public static JButton r1_bt, r2_bt, r3_bt, r4_bt;
	private JButton mkr_bt, exit_bt, userexit_bt;
	private JTextArea chatarea_ta, rank_ta;
	private JTextField chatfield_tf, id_tf;
	private JLabel back_l, menubar_l, character_l;
	private JPanel user_p, userbottom_p, room_p, roomtop_p, roombot_p, rank_p;
	private JScrollPane scroll;
	private String ID,avatar;
	private LoginView Lgview;
	// ���ȭ���̹����� ���� �̹��������� ����.
	private ImageIcon back_img = new ImageIcon("src/images/waitingback.jpg");
	// �޴��ٿ� ���� �̹��������� ����.
	private ImageIcon menu_img = new ImageIcon("src/images/menubar.png");
	// �⺻ �����ư�� ���� �̹��������� ����.
	private ImageIcon exitbasic_img = new ImageIcon("src/images/exitButtonBasic.png");
	// �����ư���� ���콺�� �÷����ÿ� ������ �̹����� ���� �̹��� ������ ����.
	private ImageIcon exitentered_img = new ImageIcon("src/images/exitButtonEntered.png");
	// ĳ����1�� ���� �̹��������� ����.
	private ImageIcon character_img = new ImageIcon("src/images/character1.png");
	private ImageIcon chosen_character;
	// ���콺�� x��ǥ y��ǥ�� ������ ����.
	private int mouseX, mouseY;


	private Application app;
	private DefaultTableModel model;	// ���̺� �� �����͸� �𵨸��ϴ� ��ü
	private JTable table;				// ���� ���̴� ���̺�
	private JScrollPane sc;				// ��ũ���� �������� �� �ִ� ��

	//������ �޼ҵ�
	public WaitingView() {
		// ���̺��� ������ �����
		String []a= {"����","ID","����"};
		// �켱�� �� ĭ�� ���� ��ĭ���� �ʱ�ȭ
		String [][]b = {{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""}};


		// �����͸� ���� �𵨸� ���� �� ���̺� �𵨸� �߰�
		// ���̺� �� ���� ����
		model = new DefaultTableModel(b,a) {
			public boolean isCellEditable(int i, int c){ 
				return false; 
			}
		};
		table = new JTable(model);
		// ���̺��� ĭ�� ũ�� ����
		table.getColumnModel().getColumn(0).setPreferredWidth(30); 
		table.getColumnModel().getColumn(1).setPreferredWidth(130); 
		table.getColumnModel().getColumn(2).setPreferredWidth(40); 
		// ��ũ���ǿ� ���̺� �߰�
		sc = new JScrollPane(table);
		sc.setPreferredSize(new Dimension(240,420));
		//sc.setPreferredSize(new Dimension(200,345));


		// DefaultTableCellHeaderRenderer ���� (��� ������ ����)
		DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
		// DefaultTableCellHeaderRenderer�� ������ ��� ���ķ� ����
		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		// ������ ���̺��� ColumnModel�� ������
		TableColumnModel tcmSchedule = table.getColumnModel();


		// �ݺ����� �̿��Ͽ� ���̺��� ��� ���ķ� ����
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
			tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
		}

		setLayout(null);

		// �����ư�� exitbasic_img�� �����ϸ� ����.
		exit_bt = new JButton(exitbasic_img);
		// �����ư�� ��ġ�� ����.
		exit_bt.setBounds(1245, 0, 30, 30);
		// �����ư�� �׵θ� ������ ������Ŵ.
		exit_bt.setBorderPainted(false);
		// �����ư�� ����� ä���� ����.
		exit_bt.setContentAreaFilled(false);
		// �����ư�� ��Ŀ�� ǥ�� ����.
		exit_bt.setFocusPainted(false);



		// �޴��� ���� menu_img�� �����ϸ� ����.
		menubar_l = new JLabel(menu_img);
		// �޴����� ��ġ�� ����.
		menubar_l.setBounds(0, 0, 1280, 30);



		userexit_bt = new JButton("�����ϱ�"); 



		room_p = new JPanel();
		room_p.setLayout(new BorderLayout());

		roomtop_p = new JPanel();
		roomtop_p.setLayout(new GridLayout(2, 2, 10, 10));

		roombot_p = new JPanel();
		roombot_p.setLayout(new FlowLayout(FlowLayout.RIGHT));

		r1_bt = new JButton("��1");
		r2_bt = new JButton("��2");
		r3_bt = new JButton("��3");
		r4_bt = new JButton("��4");

		roomtop_p.add(r1_bt);
		roomtop_p.add(r2_bt);
		roomtop_p.add(r3_bt);
		roomtop_p.add(r4_bt);

		r1_bt.setEnabled(false);
		r2_bt.setEnabled(false);
		r3_bt.setEnabled(false);
		r4_bt.setEnabled(false);


		mkr_bt = new JButton("�游���");
		roombot_p.add(mkr_bt);

		room_p.add(roomtop_p, BorderLayout.CENTER);
		room_p.add(roombot_p, BorderLayout.SOUTH);

		room_p.setBounds(20, 50, 975, 450);

		chatfield_tf = new JTextField("");
		chatfield_tf.setBounds(20, 665, 975, 48);
		chatfield_tf.setCaretPosition(chatfield_tf.getText().length());

		chatarea_ta = new JTextArea();
		chatarea_ta.setEditable(false);

		scroll = new JScrollPane(chatarea_ta, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setBounds(20, 510, 975, 155);

		rank_p = new JPanel();
		rank_p.setBounds(1010, 50, 250, 450);
		rank_p.add(sc);

		back_l = new JLabel(back_img);
		back_l.setBounds(0, 0, 1280, 720);

		user_p = new JPanel();
		user_p.setLayout(new GridLayout(2, 1));

		userbottom_p = new JPanel();
		userbottom_p.setLayout(new GridLayout(2, 1));

		id_tf = new JTextField("");
		character_l = new JLabel();

		userbottom_p.add(id_tf);
		userbottom_p.add(userexit_bt);

		user_p.add(character_l);
		user_p.add(userbottom_p);

		user_p.setBounds(1010, 510, 250, 210);

		Border lineBorder = BorderFactory.createLineBorder(Color.black, 3);
		Border emptyBorder = BorderFactory.createEmptyBorder(7, 7, 7, 7);

		room_p.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
		chatfield_tf.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
		rank_p.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
		user_p.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));

		add(room_p);
		add(scroll);
		add(chatfield_tf);
		add(rank_p);
		add(user_p);
		add(exit_bt);
		add(menubar_l);
		add(back_l);

		setVisible(true);

	}


	// �� ��ư ��ȯ
	public static JButton getR(int i) {
		if(i == 0) {
			return r1_bt;
		} else if(i == 1) {
			return r2_bt;
		} else if(i == 2) {
			return r3_bt;
		} else {
			return r4_bt;
		}
	}



	public void setCharacter_img(String character_num) {

		if(character_num.equals("1")) {
			avatar = "smallcharacter1.png";
		} else if(character_num.equals("2")) {
			avatar = "smallcharacter2.png";
		} else {
			avatar = "smallcharacter3.png";
		}
		character_img = new ImageIcon("images/" + avatar);
		character_l.setIcon(character_img);
	}


	public JButton getMkr_bt() {
		return mkr_bt;
	}



	public JButton getExit_bt() {
		return exit_bt;
	}



	public int getMouseX() {
		return mouseX;
	}



	public void setMouseX(int mouseX) {
		this.mouseX = mouseX;
	}



	public int getMouseY() {
		return mouseY;
	}



	public void setMouseY(int mouseY) {
		this.mouseY = mouseY;
	}



	public ImageIcon getExitbasic_img() {
		return exitbasic_img;
	}



	public ImageIcon getExitentered_img() {
		return exitentered_img;
	}



	public JButton getUserexit_bt() {
		return userexit_bt;
	}



	public JLabel getMenubar_l() {
		return menubar_l;
	}



	public JTable getTable() {
		return table;
	}

	public JTextArea getTextArea() {
		return chatarea_ta;
	}

	public JTextField getTextField() {
		return chatfield_tf;
	}

	public String getChatText() {
		return chatfield_tf.getText();
	}

	public void setInitChatText() {
		chatfield_tf.setText("");
	}

	public void setChatText(String s) {
		chatarea_ta.append(s);
	}


	public void getUserID(String ID) {
		this.ID = ID;
		id_tf.setText(ID);
	}
}






