package Control;
import java.awt.event.*;
import Starter.Application;
import Model.Ingame;
import View.IngameView;
import javax.swing.*;

public class IngameControler {
	private Application app;
	private IngameView iv;
	private ButtonListener listen;
	private Ingame []i;		// ���� �� ��ü(Ingame ��)
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			// ���� ���� ��ư ���ý� -> �̱���
			if(event.getSource() == iv.getStart()) {
				JOptionPane.showMessageDialog(null, "���� ����");
			// ���� ��ư ���ý�
			} else {
				JOptionPane.showMessageDialog(null, "���Ƿ� �����ϴ�");
				app.changePanel();
				app.changePanel();
				app.setTitle("����");
			}
		}
	}
	
	// ������ �޼ҵ�
	public IngameControler(Application app, IngameView iv) {
		this.app = app;
		this.iv = iv;
		// ���� �� ��ü�� 4�� �������ش�
		i = new Ingame[4];
		i[0] = new Ingame("1����");
		i[1] = new Ingame("2����");
		i[2] = new Ingame("3����");
		i[3] = new Ingame("4����");
	}
	
	public void buttonHandler() {
		listen = new ButtonListener();
		iv.getStart().addActionListener(listen);
		iv.getExit().addActionListener(listen);
	}
	
	// ���ӹ� ��ü�� ��й�ȣ�� �����´�
	public String getPW(int num) {
		return i[num].getPw();
	}
	
	// ���ӹ� ��ü�� ������ �����´�
	public String getTitle(int num) {
		return i[num].getTitle();
	}
	
	// ���ӹ� ��ü�� ������ �����Ѵ�
	public void setTitle(int num, String title) {
		i[num].setTitle(title);
	}
	
	// ���ӹ� ��ü�� ������ �����Ѵ�
	public void setPW(int num, String pw) {
		i[num].setPw(pw);
	}
	
	// �� �ʿ� ����
	public void setInfo(int num) {
		iv.setChat(i[num].getInfo());
	}
	
}
