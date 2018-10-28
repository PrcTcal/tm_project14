package Starter;
import Control.*;
import View.*;
import Model.Music;
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
	private Music music;
	
	//생성자 메소드
	public Application() {
		lv = new LoginView();
		lc = new LoginControler(this,lv,rv,rc);
		iv = new IngameView();
		ic = new IngameControler(this, iv);
		wv = new WaitingView();
		wc = new WaitingControler(this, wv, ic);
		
		setUndecorated(true);
		setTitle("로그인");
		setSize(1280, 720);
		// setSize(330, 150);
        // Card Layout으로 지정
        getContentPane().setLayout(cards);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
		
		// Card Layout에 Login Controler, Waiting Controler, Ingame Controler를 넣어준다
        getContentPane().add("One", lv);
        getContentPane().add("Two", wv);
        getContentPane().add("Three", iv);
        wc.update_rank();
        wc.buttonHandler();
        ic.buttonHandler();
        
        // 음악재생을 위한 객체를 선언함과 동시에 재생할 곡과 isLoop에 true값을 넣어주면서 무한반복 시킴.
     	music = new Music("music.mp3", true);
     	// 음악 시작.
     	music.start();
        setVisible(true);
	}
	
	public void offMusic() {
		music.close();
	}
	
	// Card Layout에 들어가있는 패널들에서 다음 순번의 패널로 화면을 바꿔준다
	public void changePanel() {
		 cards.next(this.getContentPane());
	}
	
	public static void main(String[] args) {
		app = new Application();
	}

}
