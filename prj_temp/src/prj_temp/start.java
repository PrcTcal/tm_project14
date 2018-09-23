package prj_temp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class start extends JFrame{
	private CardLayout cards = new CardLayout();
	
    
    public start() {
    	setTitle("·Î±×ÀÎ");
        setSize(330, 150);
        getContentPane().setLayout(cards);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
         
        getContentPane().add("One", new LogIn(this));
        getContentPane().add("Two", new waiting(this));
        getContentPane().add("Three", new ingame(this));
         
        setVisible(true);
    }
     
    public void changePanel() {
        cards.next(this.getContentPane());
    }

	public static void main(String[] args) {
		start st = new start();


	


	}

}