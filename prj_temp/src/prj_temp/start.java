package prj_temp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class start extends JFrame{
	private CardLayout cards = new CardLayout();
	public static start st = null;
	private waiting w;
	private LogIn lg;
	private ingame ig;
	
	/* ���� ����
	 * waiting���� ����ǥ�� �ֽ�ȭ�Ƿ��� LogIn, register, start�� �ϳ����� waiting��ü�� update_rank()��
	 * ȣ���ؾ��ϴµ� start�� �̹� container�� LogIn, waiting, ingame�� add���ִ� �������� �����ڸ� �����ϱ�
	 * ������ start���� �� ��ü���� ���� ������ ����� �̰��� add���ִ� ���� ȿ�����̶�� �Ǵ��Ͽ� �����Ͽ���.
	 * �ӽ������� changePanel�� ȣ���Ҷ� waiting�� update_rank()�� ȣ���ϵ��� �Ͽ���.
	 */
    
    public start() {
    	setTitle("�α���");
        setSize(330, 150);
        getContentPane().setLayout(cards);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        lg = new LogIn(this);
        w = new waiting(this);
        ig = new ingame(this); 
         
         
        getContentPane().add("One", lg);
        getContentPane().add("Two", w);
        getContentPane().add("Three", ig);
         
        setVisible(true);
    }
     
    public void changePanel() {
        cards.next(this.getContentPane());
        w.update_rank();
    }
    

	public static void main(String[] args) {
		st = new start();


	


	}

}