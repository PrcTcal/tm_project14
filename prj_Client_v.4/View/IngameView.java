package View;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;
import Starter.Application;


public class IngameView extends JPanel{
//	private JPanel g_t, g_b, button;
//	private JTextArea paint, chat, user, undecided;
//	private JButton start, exit;
//	private Application app;
//	private String title;
//	private String pw;
	
	private JPanel paint_p, paintgui_p, paintdraw_p, gui_p, userinfo1_p, userinfo2_p, userinfo3_p, userinfo4_p;	// �׸��� ��ü�г�, �׸����� ui�г�, �׸����� �׸��г�
	private JTextArea chat_txa;	 // ä����
	private JButton ready_bt, exit_bt, pencil_bt, eraser_bt, eraseall_bt, colorselect_bt, menuexit_bt;	// ��ŸƮ��ư, �����ư, ���ʹ�ư, ���찳��ư, �������ư
	private JLabel background_l, linethickness_l, menubar_l, user1ID_l, user2ID_l, user3ID_l, user4ID_l, user1ch_l, user2ch_l, user3ch_l, user4ch_l, timer_l, theme_l, round_l;	// �޹��� ��, �����⸦ ��Ÿ���� ��
	private JTextField setlinethickness_tx, chat_tx, user1score_tx, user2score_tx, user3score_tx, user4score_tx, question_tx;		// �� ���⸦ �Է½�Ű�� �ؽ�Ʈ�ʵ�
	private Color selectedColor;		// ���õ� �÷��� ��Ÿ��.
	private Graphics graphics;		// �׷��Ƚ� ����
	private Graphics2D graphics2;	// �����⸦ ���� �׷��Ƚ�2D ���
	private JScrollPane scroll;
	private String sendchat_s;
	private int thickness = 10;		// �����⸦ �Է¹��� ����
	private int startX;			// ���� x��ǥ
	private int startY;			// ���� y��ǥ
	private int endX;			// �� x��ǥ
	private int endY;			// �� y��ǥ
	private int mouseX, mouseY; // �޴��ٿ������ ��ǥ��.
	private boolean tf = false;		// ???????
	
	// �޹��󺧿� ����� �̹��������� ����
	private ImageIcon ingameback_img = new ImageIcon("src/images/ingameback.jpg");
	// �޴��ٿ� ���� �̹��������� ����.
	private ImageIcon menu_img = new ImageIcon("src/images/menubar.png");
	// �⺻ �����ư�� ���� �̹��������� ����.
	private ImageIcon exitbasic_img = new ImageIcon("src/images/exitButtonBasic.png");
	// �����ư���� ���콺�� �÷����ÿ� ������ �̹����� ���� �̹��� ������ ����.
	private ImageIcon exitentered_img = new ImageIcon("src/images/exitButtonEntered.png");
	private ImageIcon pencil_img = new ImageIcon("src/images/pencil.png"); 
	private ImageIcon eraser_img = new ImageIcon("src/images/eraser.png"); 
	private ImageIcon clear_img = new ImageIcon("src/images/clear.png"); 
	private ImageIcon colorchooser_img = new ImageIcon("src/images/colorchooser.png"); 
	private ImageIcon exitBasic2_img = new ImageIcon("src/images/exitBasic2.png"); 
	private ImageIcon exitEntered2_img = new ImageIcon("src/images/exitEntered2.png"); 
	private ImageIcon readyBasic_img = new ImageIcon("src/images/readyBasic.png"); 
	private ImageIcon readyEntered_img = new ImageIcon("src/images/readyEntered.png"); 
	
	//������ �޼ҵ�
	public IngameView(Application app) {
		
		// �����θ� ����ϱ� ���� ����.
				setLayout(null);
				// �Ѱܹ��� �����Ӱ�ü ����.
				
				
				// ingameback_img�� �����ϸ鼭 ���� �󺧻���
				background_l = new JLabel(ingameback_img);
				// ������ ��ġ�� ũ�� ����
				background_l.setBounds(0, 0, 1280, 720);

				// �޴��� ���� menu_img�� �����ϸ� ����.
				menubar_l = new JLabel(menu_img);
				// �޴����� ��ġ�� ����.
				menubar_l.setBounds(0, 0, 1280, 30);

				

				
				// �����ư�� exitbasic_img�� �����ϸ� ����.
				menuexit_bt = new JButton(exitbasic_img);
				// �����ư�� ��ġ�� ����.
				menuexit_bt.setBounds(1245, 0, 30, 30);
				// �����ư�� �׵θ� ������ ������Ŵ.
				menuexit_bt.setBorderPainted(false);
				// �����ư�� ����� ä���� ����.
				menuexit_bt.setContentAreaFilled(false);
				// �����ư�� ��Ŀ�� ǥ�� ����.
				menuexit_bt.setFocusPainted(false);
				
				

				// �׸��� �г� ����
				paint_p = new JPanel();
				// �����θ� ����ϱ� ���� ����.
				paint_p.setLayout(null);
				
				// �׸��� �� ui�г� ����
				paintgui_p = new JPanel();
				// ����� ȸ������ ����.
				paintgui_p.setBackground(Color.gray);
				// �����θ� ����ϱ� ���� ����.
				paintgui_p.setLayout(null);
				
				// ���ʹ�ư�� ����
				pencil_bt = new JButton(pencil_img);
				// �����ư�� �׵θ� ������ ������Ŵ.
				pencil_bt.setBorderPainted(false);
				// �����ư�� ����� ä���� ����.
				pencil_bt.setContentAreaFilled(false);
				// �����ư�� ��Ŀ�� ǥ�� ����.
				pencil_bt.setFocusPainted(false);
				// ���ʹ�ư�� ��ġ�� ũ�⸦ ����.
				pencil_bt.setBounds(10,10,50,50);
				
				// ���찳 ��ư�� ����
				eraser_bt = new JButton(eraser_img);
				// �����ư�� �׵θ� ������ ������Ŵ.
				eraser_bt.setBorderPainted(false);
				// �����ư�� ����� ä���� ����.
				eraser_bt.setContentAreaFilled(false);
				// �����ư�� ��Ŀ�� ǥ�� ����.
				eraser_bt.setFocusPainted(false);
				// ���찳 ��ư�� ��ġ�� ũ�⸦ ����.
				eraser_bt.setBounds(65,10,50,50);
				
				eraseall_bt = new JButton(clear_img);
				// �����ư�� �׵θ� ������ ������Ŵ.
				eraseall_bt.setBorderPainted(false);
				// �����ư�� ����� ä���� ����.
				eraseall_bt.setContentAreaFilled(false);
				// �����ư�� ��Ŀ�� ǥ�� ����.
				eraseall_bt.setFocusPainted(false);
				eraseall_bt.setBounds(120, 10, 50, 50);
				
				// ������ ��ư ����
				colorselect_bt = new  JButton(colorchooser_img);
				// �����ư�� �׵θ� ������ ������Ŵ.
				colorselect_bt.setBorderPainted(false);
				// �����ư�� ����� ä���� ����.
				colorselect_bt.setContentAreaFilled(false);
				// �����ư�� ��Ŀ�� ǥ�� ����.
				colorselect_bt.setFocusPainted(false);
				// ������ ��ư�� ��ġ�� ũ�⸦ ����.
				colorselect_bt.setBounds(175,10,50,50);
				
				// font ����
				Font font = new Font("���ʷյ���", Font.BOLD, 25);
				// "���⺯��" �� ��Ÿ�� �󺧻���
				linethickness_l = new JLabel("���⺯��");
				// ���⺯�� ���� ��Ʈ�� font�� ����.
				linethickness_l.setFont(font);
				// ���⺯�� ���� ��ġ�� ũ�� ����.
				linethickness_l.setBounds(230,20,60,45);
				
				// ���⸦ �Է��� �ؽ�Ʈ�ʵ� ���� �⺻�� 10�� ��
				setlinethickness_tx = new JTextField("10", 5);
				// ��Ʈ��  font�� ����
				setlinethickness_tx.setFont(font);
				// ��� ���ķ� ����.
				setlinethickness_tx.setHorizontalAlignment(JTextField.CENTER);
				// ��ġ�� ũ�⸦ ����.
				setlinethickness_tx.setBounds(285, 20, 50, 35);
				
				question_tx = new JTextField("���þ�");
				question_tx.setBounds(400, 15, 150, 40);
				
				theme_l = new JLabel("�׸�");
				theme_l.setBounds(350, 15, 50, 45);
				
				timer_l = new JLabel("03:00");
				timer_l.setFont(font);
				timer_l.setBounds(640, 15, 100, 50);
				
				round_l = new JLabel("����");
				round_l.setBounds(800, 15, 50, 50);
				
				// ui�гο� �����ϴ� �κ�.
				paintgui_p.add(pencil_bt);
				paintgui_p.add(eraser_bt);
				paintgui_p.add(eraseall_bt);
				paintgui_p.add(colorselect_bt);
				paintgui_p.add(linethickness_l);
				paintgui_p.add(setlinethickness_tx);
				paintgui_p.add(question_tx);
				paintgui_p.add(theme_l);
				paintgui_p.add(timer_l);
				paintgui_p.add(round_l);
				
				// �׸��� �׸� �гλ���.
				paintdraw_p = new JPanel();
				// ����� �Ͼ������ ����.
				paintdraw_p.setBackground(Color.WHITE);
				
				// ui�г��� ��ġ�� ũ�� ����.
				paintgui_p.setBounds(0,0,900,60);
				// �׸��г��� ��ġ�� ũ�� ����.
				paintdraw_p.setBounds(0, 50, 900, 800);
				// �׸��� �гο� ui�� �׸��г��� ����
				paint_p.add(paintgui_p);
				paint_p.add(paintdraw_p);
				// �׸��� �г��� ��ġ�� ũ�⸦ ����.
				paint_p.setBounds(80, 40, 900, 520);
				
				
				// �׷��Ⱥ��� �ʱ�ȭ
				graphics = app.getGraphics();
				// graphics2�� graphic������ Graphics2Dȭ ���� ������. -> �����⺯�� ����� ����ϱ� ����
				graphics2 = (Graphics2D)graphics;
				graphics2.setColor(selectedColor);
				
				// font ����
				Font font2 = new Font("���ʷյ���", Font.BOLD, 15);
				chat_tx = new JTextField("ID : ");
				chat_tx.setBounds(80, 660, 900, 48);
				chat_tx.setCaretPosition(chat_tx.getText().length());
				chat_tx.setFont(font2);
				

				chat_txa = new JTextArea();
				chat_txa.setEditable(false);
				chat_txa.setFont(font2);
				scroll = new JScrollPane(chat_txa, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				scroll.setBounds(80, 560, 900, 100);
				
//				chat_tx = new JTextField(20);
//				chat_tx.setBounds(80,665,900,30);		
				
				gui_p = new JPanel();
				gui_p.setLayout(null);
				gui_p.setBounds(980, 40, 250, 668);
				
				Border lineBorder = BorderFactory.createLineBorder(Color.black, 3);
		        Border emptyBorder = BorderFactory.createEmptyBorder(7, 7, 7, 7);
		        
		        gui_p.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
		        chat_tx.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
				
		        ready_bt = new JButton(readyBasic_img);
		        ready_bt.setBounds(-2, 495, 250, 120);
		        // �����ư�� �׵θ� ������ ������Ŵ.
		        ready_bt.setBorderPainted(false);
		        // �����ư�� ����� ä���� ����.
		        ready_bt.setContentAreaFilled(false);
		        // �����ư�� ��Ŀ�� ǥ�� ����.
		        ready_bt.setFocusPainted(false);
		        
		        exit_bt = new JButton(exitBasic2_img);
		        exit_bt.setBounds(-2, 565, 250, 120);
		        //exit_bt.setRolloverIcon(exitEntered2_img);
		        // �����ư�� �׵θ� ������ ������Ŵ.
		        exit_bt.setBorderPainted(false);
		        // �����ư�� ����� ä���� ����.
		        exit_bt.setContentAreaFilled(false);
		        // �����ư�� ��Ŀ�� ǥ�� ����.
		        exit_bt.setFocusPainted(false);
		        
		        userinfo1_p = new JPanel();
		        userinfo1_p.setLayout(null);
		        userinfo1_p.setBounds(5, 10, 240, 120);
		      
		        user1ch_l = new JLabel("ĳ����");
		        user1ch_l.setBounds(20, 10, 100, 100);

		        user1ID_l = new JLabel("������");
		        user1ID_l.setBounds(100, 0, 100, 125);

		        user1score_tx = new JTextField("1000��");
		        user1score_tx.setBounds(100, 125, 100, 125);

		        userinfo1_p.add(user1ch_l);
		        userinfo1_p.add(user1ID_l);
		        userinfo1_p.add(user1score_tx);

		        //----------------user1
		        
		        userinfo2_p = new JPanel();
		        userinfo2_p.setLayout(null);
		        userinfo2_p.setBounds(5, 135, 240, 120);
		        
		        user2ch_l = new JLabel("ĳ����");
		        user2ch_l.setBounds(0, 0, 100, 100);

		        user2ID_l = new JLabel("������");
		        user2ID_l.setBounds(100, 0, 100, 125);

		        user2score_tx = new JTextField("1000��");
		        user2score_tx.setBounds(100, 125, 100, 125);

		        userinfo2_p.add(user2ch_l);
		        userinfo2_p.add(user2ID_l);
		        userinfo2_p.add(user2score_tx);

		        //----------------user2
		        
		        userinfo3_p = new JPanel();
		        userinfo3_p.setLayout(null);
		        userinfo3_p.setBounds(5, 260, 240, 120);
		        
		        user3ch_l = new JLabel("ĳ����");
		        user3ch_l.setBounds(0, 0, 100, 100);

		        user3ID_l = new JLabel("�̽�ȣ");
		        user3ID_l.setBounds(100, 0, 100, 125);

		        user3score_tx = new JTextField("1000��");
		        user3score_tx.setBounds(100, 125, 100, 125);

		        userinfo3_p.add(user3ch_l);
		        userinfo3_p.add(user3ID_l);
		        userinfo3_p.add(user3score_tx);
		        
		        //----------------user3
		        
		        userinfo4_p = new JPanel();
		        userinfo4_p.setLayout(null);
		        userinfo4_p.setBounds(5, 385, 240, 120);
		        
		        user4ch_l = new JLabel("ĳ����");
		        user4ch_l.setBounds(0, 0, 100, 100);

		        user4ID_l = new JLabel("����");
		        user4ID_l.setBounds(100, 0, 100, 125);

		        user4score_tx = new JTextField("1000��");
		        user4score_tx.setBounds(100, 125, 100, 125);

		        userinfo4_p.add(user4ch_l);
		        userinfo4_p.add(user4ID_l);
		        userinfo4_p.add(user4score_tx);
		        
		        //----------------user4
		        
		        gui_p.setBackground(Color.WHITE);
		        gui_p.add(userinfo1_p);
		        gui_p.add(userinfo2_p);
		        gui_p.add(userinfo3_p);
		        gui_p.add(userinfo4_p);
		        gui_p.add(ready_bt);
		        gui_p.add(exit_bt);
		        
				add(paint_p);
				add(gui_p);
				add(scroll);
				add(chat_tx);
				add(menuexit_bt);
				add(menubar_l);
				add(background_l);
				
				setVisible(true);
//		setLayout(new BorderLayout());
//		title = "";
//		pw = "";
//		
//		g_t = new JPanel();
//		g_b = new JPanel();
//		button = new JPanel();
//		
//		g_t.setLayout(new BorderLayout());
//		g_b.setLayout(new BorderLayout());
//		button.setLayout(new GridLayout(2,1));
//		
//		paint = new JTextArea("�׸���",18,50);
//		chat = new JTextArea("ä����",10,70);
//		user = new JTextArea("����� UI",18,15);
//		undecided = new JTextArea("����",18,15);
//		
//		start = new JButton("����");
//		exit = new JButton("����");
//		
//		button.add(start);
//		button.add(exit);
//		
//		g_t.add(paint, BorderLayout.CENTER);
//		g_t.add(user, BorderLayout.EAST);
//		g_t.add(undecided, BorderLayout.WEST);
//		g_b.add(chat, BorderLayout.CENTER);
//		g_b.add(button, BorderLayout.EAST);
//		add(g_t, BorderLayout.CENTER);
//		add(g_b, BorderLayout.SOUTH);
//		
//		Border lineBorder = BorderFactory.createLineBorder(Color.black, 3);
//        Border emptyBorder = BorderFactory.createEmptyBorder(7, 7, 7, 7);
//        chat.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
//        paint.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
//        user.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
//        undecided.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
		
		
	}

	
	
	



	public JLabel getTimer_l() {
		return timer_l;
	}







	public ImageIcon getExitBasic2_img() {
		return exitBasic2_img;
	}



	public ImageIcon getExitEntered2_img() {
		return exitEntered2_img;
	}



	public ImageIcon getReadyBasic_img() {
		return readyBasic_img;
	}



	public JButton getReady_bt() {
		return ready_bt;
	}



	public ImageIcon getReadyEntered_img() {
		return readyEntered_img;
	}



	public String getSendchat_s() {
		return sendchat_s;
	}



	public void setSendchat_s(String sendchat_s) {
		this.sendchat_s = sendchat_s;
	}



	public JTextArea getChat_txa() {
		return chat_txa;
	}



	public JTextField getChat_tx() {
		return chat_tx;
	}



	public int getStartX() {
		return startX;
	}



	public int getStartY() {
		return startY;
	}



	public JTextField getSetlinethickness_tx() {
		return setlinethickness_tx;
	}



	public Color getSelectedColor() {
		return selectedColor;
	}



	public void setSelectedColor(Color selectedColor) {
		this.selectedColor = selectedColor;
	}



	public int getEndX() {
		return endX;
	}



	public void setEndX(int endX) {
		this.endX = endX;
	}



	public int getEndY() {
		return endY;
	}



	public void setEndY(int endY) {
		this.endY = endY;
	}



	public boolean isTf() {
		return tf;
	}



	public void setTf(boolean tf) {
		this.tf = tf;
	}



	public Graphics2D getGraphics2() {
		return graphics2;
	}



	public void setThickness(int thickness) {
		this.thickness = thickness;
	}



	public int getThickness() {
		return thickness;
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



	public JPanel getPaintdraw_p() {
		return paintdraw_p;
	}



	public JButton getPencil_bt() {
		return pencil_bt;
	}



	public JButton getEraser_bt() {
		return eraser_bt;
	}



	public JButton getEraseall_bt() {
		return eraseall_bt;
	}



	public JButton getColorselect_bt() {
		return colorselect_bt;
	}



	public JButton getMenuexit_bt() {
		return menuexit_bt;
	}



	public JLabel getMenubar_l() {
		return menubar_l;
	}



	public ImageIcon getExitbasic_img() {
		return exitbasic_img;
	}



	public ImageIcon getExitentered_img() {
		return exitentered_img;
	}



	public void setStartX(int startX) {
		this.startX = startX;
	}



	public void setStartY(int startY) {
		this.startY = startY;
	}



	public JButton getExit_bt() {
		return exit_bt;
	}


//
//	public JButton getStart() {
//		return start;
//	}
//
//	public JButton getExit() {
//		return exit;
//	}
//	
//	public void setChat(String str) {
//		chat.setText(str);
//	}
}