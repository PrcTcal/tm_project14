package View;
import javax.swing.*;
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
	
	private JPanel paint_p, paintgui_p, paintdraw_p, gui_p;	// �׸��� ��ü�г�, �׸����� ui�г�, �׸����� �׸��г�
	private JTextArea chat_txa;	 // ä����
	private JButton start_bt, exit_bt, pencil_bt, eraser_bt, eraseall_bt, colorselect_bt, menuexit_bt;	// ��ŸƮ��ư, �����ư, ���ʹ�ư, ���찳��ư, �������ư
	private JLabel background_l, linethickness_l, menubar_l;	// �޹��� ��, �����⸦ ��Ÿ���� ��
	private JTextField setlinethickness_tx, chat_tx;		// �� ���⸦ �Է½�Ű�� �ؽ�Ʈ�ʵ�
	private Color selectedColor;		// ���õ� �÷��� ��Ÿ��.
	private Graphics graphics;		// �׷��Ƚ� ����
	private Graphics2D graphics2;	// �����⸦ ���� �׷��Ƚ�2D ���
	private int thickness = 10;		// �����⸦ �Է¹��� ����
	private int startX;			// ���� x��ǥ
	private int startY;			// ���� y��ǥ
	private int endX;			// �� x��ǥ
	private int endY;			// �� y��ǥ
	private int mouseX, mouseY; // �޴��ٿ������ ��ǥ��.
	private boolean tf = false;		// ???????
	
	// �޹��󺧿� ����� �̹��������� ����
	private ImageIcon ingameback_img = new ImageIcon("C:\\\\Users\\\\hojja\\\\Desktop\\\\�İ�\\\\2�г�\\\\JAVA\\\\Workspace\\\\prj_Client_v_2\\\\bin\\\\images\\\\ingameback.jpg");
	// �޴��ٿ� ���� �̹��������� ����.
	private ImageIcon menu_img = new ImageIcon("C:\\\\Users\\\\hojja\\\\Desktop\\\\�İ�\\\\2�г�\\\\JAVA\\\\Workspace\\\\prj_Client_v_2\\\\bin\\\\images\\\\menubar.png");
	// �⺻ �����ư�� ���� �̹��������� ����.
	private ImageIcon exitbasic_img = new ImageIcon("C:\\\\Users\\\\hojja\\\\Desktop\\\\�İ�\\\\2�г�\\\\JAVA\\\\Workspace\\\\prj_Client_v_2\\\\bin\\\\images\\\\exitButtonBasic.png");
	// �����ư���� ���콺�� �÷����ÿ� ������ �̹����� ���� �̹��� ������ ����.
	private ImageIcon exitentered_img = new ImageIcon("C:\\\\Users\\\\hojja\\\\Desktop\\\\�İ�\\\\2�г�\\\\JAVA\\\\Workspace\\\\prj_Client_v_2\\\\bin\\\\images\\\\exitButtonEntered.png");
	
	
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
				
				// font ����
				Font font = new Font("���ʷյ���", Font.BOLD, 25);
				// ���ʹ�ư�� ����
				pencil_bt = new JButton("����");
				// ���ʹ�ư�� ��Ʈ�� font�� ����
				pencil_bt.setFont(font);
				// ���ʹ�ư�� ��ġ�� ũ�⸦ ����.
				pencil_bt.setBounds(10,10,90,45);
				
				// ���찳 ��ư�� ����
				eraser_bt = new JButton("���찳");
				// ���찳 ��ư�� ��Ʈ�� font�� ����.
				eraser_bt.setFont(font);
				// ���찳 ��ư�� ��ġ�� ũ�⸦ ����.
				eraser_bt.setBounds(105,10,109,45);
				
				eraseall_bt = new JButton("clear");
				eraseall_bt.setFont(font);
				eraseall_bt.setBounds(245, 10, 110, 45);
				
				// ������ ��ư ����
				colorselect_bt = new  JButton("������");
				// ������ ��ư�� ��Ʈ�� font�� ����.
				colorselect_bt.setFont(font);
				// ������ ��ư�� ��ġ�� ũ�⸦ ����.
				colorselect_bt.setBounds(370,10,110,45);
				
				// "���⺯��" �� ��Ÿ�� �󺧻���
				linethickness_l = new JLabel("���⺯��");
				// ���⺯�� ���� ��Ʈ�� font�� ����.
				linethickness_l.setFont(font);
				// ���⺯�� ���� ��ġ�� ũ�� ����.
				linethickness_l.setBounds(680,10,100,45);
				
				// ���⸦ �Է��� �ؽ�Ʈ�ʵ� ���� �⺻�� 10�� ��
				setlinethickness_tx = new JTextField("10", 5);
				// ��Ʈ��  font�� ����
				setlinethickness_tx.setFont(font);
				// ��� ���ķ� ����.
				setlinethickness_tx.setHorizontalAlignment(JTextField.CENTER);
				// ��ġ�� ũ�⸦ ����.
				setlinethickness_tx.setBounds(780, 15, 50, 35);
				
				// ui�гο� �����ϴ� �κ�.
				paintgui_p.add(pencil_bt);
				paintgui_p.add(eraser_bt);
				paintgui_p.add(eraseall_bt);
				paintgui_p.add(colorselect_bt);
				paintgui_p.add(linethickness_l);
				paintgui_p.add(setlinethickness_tx);
				
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
				paint_p.setBounds(80, 50, 900, 520);
				
				//selectedColor = Color.BLACK;
				
				// �׷��Ⱥ��� �ʱ�ȭ
				graphics = app.getGraphics();
				// graphics2�� graphic������ Graphics2Dȭ ���� ������. -> �����⺯�� ����� ����ϱ� ����
				graphics2 = (Graphics2D)graphics;
				graphics2.setColor(selectedColor);
				
				
				

				chat_txa = new JTextArea(20, 5);
				chat_txa.setBackground(Color.LIGHT_GRAY);
				chat_txa.setBounds(80, 570, 900, 100);
				
				chat_tx = new JTextField(20);
				chat_tx.setBounds(80,665,900,30);		
				
				gui_p = new JPanel();
				gui_p.setLayout(null);
				gui_p.setBounds(980, 50, 250, 644);
				
				
				add(paint_p);
				add(gui_p);
				add(chat_txa);
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
