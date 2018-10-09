package Starter;
import Control.*;
import View.*;
//import Model.Login;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// 기존의 start class를 Starter package의 application으로 대체
public class Application extends JFrame{
	private CardLayout cards = new CardLayout();
	public static Application app = null;
	//private Login l;
	private LoginView lv;
	private LoginControler lc;
	private RegisterView rv;
	private RegisterControler rc;
	private WaitingView wv;
	private WaitingControler wc;
	private IngameView iv;
	private IngameControler ic;
	
	//생성자 메소드
	public Application() {
		lv = new LoginView();
		lc = new LoginControler(this,lv,rv,rc);
		iv = new IngameView();
		ic = new IngameControler(this, iv);
		wv = new WaitingView();
		wc = new WaitingControler(this, wv, ic);
		
		
		setTitle("로그인");
        setSize(330, 150);
        // Card Layout으로 지정
        getContentPane().setLayout(cards);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
		
		// Card Layout에 Login Controler, Waiting Controler, Ingame Controler를 넣어준다
        getContentPane().add("One", lv);
        getContentPane().add("Two", wv);
        getContentPane().add("Three", iv);
        wc.update_rank();
        lc.buttonHandler();
        wc.buttonHandler();
        ic.buttonHandler();
        setVisible(true);
	}
	
	// Card Layout에 들어가있는 패널들에서 다음 순번의 패널로 화면을 바꿔준다
	public void changePanel() {
		 cards.next(this.getContentPane());
	}
	
	public static void main(String[] args) {
		app = new Application();
	}

}
