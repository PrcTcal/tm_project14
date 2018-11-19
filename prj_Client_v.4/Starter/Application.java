package Starter;
import Control.*;
import View.*;
import Model.Music;
//import Model.Login;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics;

// ������ start class�� Starter package�� application���� ��ü
public class Application extends JFrame{
	public CardLayout cards = new CardLayout();
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
	
	//������ �޼ҵ�
	public Application() {
				
		setUndecorated(true);
		setTitle("�α���");
		setSize(1280, 720);
		// setSize(330, 150);
        // Card Layout���� ����
        getContentPane().setLayout(cards);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
		
		// Card Layout�� Login Controler, Waiting Controler, Ingame Controler�� �־��ش�
        
        lv = new LoginView();
        wv = new WaitingView();
		lc = new LoginControler(this,lv,rv,rc,wv);
        setVisible(true);
		iv = new IngameView(this);
		ic = new IngameControler(this, iv);
		
		wc = new WaitingControler(this, wv, ic);
		
		getContentPane().add("One", lv);
		getContentPane().add("Two", wv);
        getContentPane().add("Three", iv);
        wc.update_rank();
        wc.buttonHandler();
        ic.buttonHandler();
        
        // ��������� ���� ��ü�� �����԰� ���ÿ� ����� ��� isLoop�� true���� �־��ָ鼭 ���ѹݺ� ��Ŵ.
     	music = new Music("music.mp3", true);
     	// ���� ����.
     	music.start();
        
	}
	
	public void offMusic() {
		music.close();
	}
	
	// Card Layout�� ���ִ� �гε鿡�� ���� ������ �гη� ȭ���� �ٲ��ش�
	public void changePanel() {
		 cards.next(this.getContentPane());
	}
	
	public static void main(String[] args) {
		app = new Application();
	}

}
