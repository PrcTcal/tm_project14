package Starter;
import Control.*;
import View.*;
import Model.Music;
//import Model.Login;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// ������ start class�� Starter package�� application���� ��ü
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
	
	//������ �޼ҵ�
	public Application() {
		lv = new LoginView();
		lc = new LoginControler(this,lv,rv,rc);
		iv = new IngameView();
		ic = new IngameControler(this, iv);
		wv = new WaitingView();
		wc = new WaitingControler(this, wv, ic);
		
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
        setVisible(true);
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
