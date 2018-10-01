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
	
	/* 변경 사항
	 * waiting에서 순위표가 최신화되려면 LogIn, register, start중 하나에서 waiting객체의 update_rank()를
	 * 호출해야하는데 start는 이미 container에 LogIn, waiting, ingame을 add해주는 과정에서 생성자를 선언하기
	 * 때문에 start에서 각 객체들을 내부 변수로 만들고 이것을 add해주는 것이 효율적이라고 판단하여 수정하였다.
	 * 임시적으로 changePanel을 호출할때 waiting의 update_rank()를 호출하도록 하였다.
	 */
    
    public start() {
    	setTitle("로그인");
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