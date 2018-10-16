package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class LoginView extends JPanel{
	// �߰� ����
	private JButton login_bt, join_bt, exit_bt;			// ��ư ��������
	private JLabel id_l, pw_l, back_l, menubar_l;		// �� ��������
	private JTextField id_tx;							// �ؽ�Ʈ�ʵ� ��������
	private JPasswordField pw_pw;						// �н������ʵ� ��������

	// ���ȭ���̹����� ���� �̹��������� ����.
	private ImageIcon back_img = new ImageIcon("C:\\\\Users\\\\hojja\\\\git\\\\tm_project14\\\\prj_MVC\\\\prj_MVC\\\\src\\\\images\\\\background.jpg");
	// �޴��ٿ� ���� �̹��������� ����.
	private ImageIcon menu_img = new ImageIcon("C:\\\\Users\\\\hojja\\\\git\\\\tm_project14\\\\prj_MVC\\\\prj_MVC\\\\src\\\\images\\\\menubar.png");
	// �⺻ �����ư�� ���� �̹��������� ����.
	private ImageIcon exitbasic_img = new ImageIcon("C:\\\\Users\\\\hojja\\\\git\\\\tm_project14\\\\prj_MVC\\\\prj_MVC\\\\src\\\\images\\\\exitButtonBasic.png");
	// �����ư���� ���콺�� �÷����ÿ� ������ �̹����� ���� �̹��� ������ ����.
	private ImageIcon exitentered_img = new ImageIcon("C:\\\\Users\\\\hojja\\\\git\\\\tm_project14\\\\prj_MVC\\\\prj_MVC\\\\src\\\\images\\\\exitButtonEntered.png");

	// login_bt��ư�� ���� �̹��������� ����.
	private ImageIcon login_img = new ImageIcon("C:\\\\Users\\\\hojja\\\\git\\\\tm_project14\\\\prj_MVC\\\\prj_MVC\\\\src\\\\images\\\\login.png");
	// login_bt��ư�� ���콺�� �÷����ÿ� �̹����� ���� �̹��������� ����.
	private ImageIcon loginEntered_img = new ImageIcon("C:\\\\Users\\\\hojja\\\\git\\\\tm_project14\\\\prj_MVC\\\\prj_MVC\\\\src\\\\images\\\\loginEntered.png");
	// join_bt��ư�� ���� �̹��������� ����.
	private ImageIcon join_img = new ImageIcon("C:\\\\Users\\\\hojja\\\\git\\\\tm_project14\\\\prj_MVC\\\\prj_MVC\\\\src\\\\images\\\\join.png");
	// join_bt��ư�� ���콺�� �÷����ÿ� �̹����� ���� �̹��������� ����.
	private ImageIcon joinEntered_img = new ImageIcon("C:\\\\Users\\\\hojja\\\\git\\\\tm_project14\\\\prj_MVC\\\\prj_MVC\\\\src\\\\images\\\\joinEntered.png");
	// id_tx�� �̹����� ���� �̹��������� ����.
	private ImageIcon id_img = new ImageIcon("C:\\\\Users\\\\hojja\\\\git\\\\tm_project14\\\\prj_MVC\\\\prj_MVC\\\\src\\\\images\\\\id.png");
	// pw_pw�� �̹����� ���� �̹��������� ����.
	private ImageIcon pw_img = new ImageIcon("C:\\\\Users\\\\hojja\\\\git\\\\tm_project14\\\\prj_MVC\\\\prj_MVC\\\\src\\\\images\\\\pw.png");

	// ���콺�� x��ǥ y��ǥ�� ������ ����.
	private int mouseX, mouseY;

	// ������ �޼ҵ�
	public LoginView() {
		// �����θ� ����ϱ� ���� ����.
		setLayout(null);

		// back_img�� �����ϸ鼭 ���� ���� ����.
		back_l = new JLabel(back_img);
		// ����̹����� ��ġ�� ũ�� ����.
		back_l.setBounds(0, 0, 1280, 720);

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



		// login_img�� �����ϸ� ��ư��ü ����.
		login_bt = new JButton(login_img);
		// login_bt�� ��ġ�� ũ�� ����.
		login_bt.setBounds(515, 550, 120, 60);
		// �׵θ� ����.
		login_bt.setBorderPainted(false);
		// ����� ä���� �ʰ� ����.
		login_bt.setContentAreaFilled(false);
		// ��Ŀ�� ȿ���� ����.
		login_bt.setFocusPainted(false);
		// ����� �����ϰ���.
		login_bt.setOpaque(false);




		// join_img�� �����ϸ� ��ư��ü ����.
		join_bt = new JButton(join_img);
		// ũ��� ��ġ�� ����.
		join_bt.setBounds(660, 550, 120, 60);
		// �׵θ� ����.
		join_bt.setBorderPainted(false);
		// ����� ä���� �ʰ� ����.
		join_bt.setContentAreaFilled(false);
		// ��Ŀ�� ȿ���� ����.
		join_bt.setFocusPainted(false);
		// ����� �����ϰ���.
		join_bt.setOpaque(false);



		// �޴��� ���� menu_img�� �����ϸ� ����.
		menubar_l = new JLabel(menu_img);
		// �޴����� ��ġ�� ����.
		menubar_l.setBounds(0, 0, 1280, 30);





		// id_img�� �����ϸ� id_l���� ����.
		id_l = new JLabel(id_img);
		// id_l�� ��ġ�� ũ�⸦ ����.
		id_l.setBounds(490, 413, 100,100);

		// pw_img�� �����ϸ� pw_l���� ����.
		pw_l = new JLabel(pw_img);
		// pw_l�� ��ġ�� ũ�⸦ ����.
		pw_l.setBounds(490, 463, 100,100);

		// �ؽ�Ʈ�ʵ� ��ü ����.
		id_tx = new JTextField(20);
		// �ؽ�Ʈ�ʵ忡 �ԷµǴ� ������ ��Ʈ�� �����ϱ� ���� ��Ʈ��ü�� ����.
		Font font = new Font("arian", Font.BOLD, 20);
		// id_tx �ؽ�Ʈ�ʵ��� ��Ʈ�� font�� ����.
		id_tx.setFont(font);
		// id_tx�� ��ġ�� ũ�⸦ ����.
		id_tx.setBounds(570, 450, 200, 25);
		// �ؽ�Ʈ�ʵ忡 �ԷµǴ� ������ ���� ����.
		id_tx.setForeground(Color.BLACK);
		// �ؽ�Ʈ�ʵ��� �׵θ��� ����.
		id_tx.setBorder(javax.swing.BorderFactory.createEmptyBorder());

		// �н������ʵ� ��ü ����.
		pw_pw = new JPasswordField(20);
		// pw_pw�� ��ġ�� ũ�⸦ ����.
		pw_pw.setBounds(570, 500, 200, 25);
		// pw_pw�� ��Ʈ�� font�� ����.
		pw_pw.setFont(font);
		// �ʵ忡 �ԷµǴ� ������ ���� ����.
		pw_pw.setForeground(Color.BLACK);
		// �н������ʵ��� �׵θ��� ����.
		pw_pw.setBorder(javax.swing.BorderFactory.createEmptyBorder());

		// add �κ�
		add(login_bt);
		add(join_bt);
		add(id_l);
		add(pw_l);
		add(id_tx);
		add(pw_pw);
		add(exit_bt);
		add(menubar_l);
		add(back_l);
	}

	public JButton getLogin_bt() {
		return login_bt;
	}

	public JButton getJoin_bt() {
		return join_bt;
	}

	public JButton getExit_bt() {
		return exit_bt;
	}

	public JLabel getMenubar_l() {
		return menubar_l;
	}

	public int getMouseX() {
		return mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}

	public void setMouseX(int mouseX) {
		this.mouseX = mouseX;
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

	public ImageIcon getLogin_img() {
		return login_img;
	}

	public ImageIcon getLoginEntered_img() {
		return loginEntered_img;
	}

	public ImageIcon getJoin_img() {
		return join_img;
	}

	public ImageIcon getJoinEntered_img() {
		return joinEntered_img;
	}

	public String getID() {
		return id_tx.getText();
	}
	
	public String getPW() {
		return pw_pw.getText();
	}
	
	public void resetPW() {
		pw_pw.setText("");
	}
	
	public void resetText() {
		id_tx.setText("");
		pw_pw.setText("");
	}
	
}
