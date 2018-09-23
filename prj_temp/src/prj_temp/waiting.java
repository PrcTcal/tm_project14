package prj_temp;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;
public class waiting extends JPanel{
	
	private JButton r1,r2,r3,r4, mkr, exit;
	private JPanel w_t, w_b, w_t_l, w_t_l_r, w_mkr, w_rank, w_chat;
	private start F;
	private JTextArea chat, rank;
	private make_room mr;
	private int room_num;
	
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(event.getSource() == exit) {
				JOptionPane.showMessageDialog(null, "게임을 종료합니다");
				F.dispose();
			} else if(event.getSource() == mkr){
				mr = new make_room(F);
				room_num+=1;
				open_room();
				
			} else {
				JOptionPane.showMessageDialog(null, "방에 입장합니다");
				F.changePanel();
				F.setTitle("Catch-ur-Crayon");
			} 
		}
	}
	
	public void open_room() {
		if(room_num == 1) {
			r1.setEnabled(true);
		} else if(room_num == 2) {
			r2.setEnabled(true);
		} else if(room_num == 3) {
			r3.setEnabled(true);
		} else {
			r4.setEnabled(true);
		}
	}
	
	public waiting(start f) {
        setLayout(new BorderLayout());
        F=f;
        room_num = 0;
       
        w_t = new JPanel();
        w_b = new JPanel();
        w_t_l = new JPanel();
        w_t_l_r = new JPanel();
        w_mkr = new JPanel();
        w_rank = new JPanel();
        w_chat = new JPanel();
        
        w_t.setLayout(new BorderLayout());
        w_b.setLayout(new BorderLayout());
        w_t_l.setLayout(new BorderLayout());
        w_t_l_r.setLayout(new GridLayout(2,2,5,5));
        w_mkr.setLayout(new FlowLayout(FlowLayout.RIGHT));
        w_rank.setLayout(new FlowLayout(FlowLayout.CENTER));
        w_rank.setBackground(Color.white);
        w_chat.setLayout(new FlowLayout(FlowLayout.CENTER));
        w_chat.setBackground(Color.white);
        
        
        
        r1 = new JButton("방1");
        r2 = new JButton("방2");
        r3 = new JButton("방3");
        r4 = new JButton("방4");
        mkr = new JButton("방만들기");
        exit = new JButton("종료");
        chat = new JTextArea("미구현(채팅)",10,78);
        rank = new JTextArea("미구현(랭킹)",18,30);
        Border lineBorder = BorderFactory.createLineBorder(Color.black, 3);
        Border emptyBorder = BorderFactory.createEmptyBorder(7, 7, 7, 7);
        chat.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
        rank.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
        
        r1.setEnabled(false);
        r2.setEnabled(false);
    	r3.setEnabled(false);
    	r4.setEnabled(false);
        
        

        w_t_l_r.add(r1);
        w_t_l_r.add(r2);
        w_t_l_r.add(r3);
        w_t_l_r.add(r4);
        
        w_t_l.add(w_t_l_r, BorderLayout.CENTER);
        w_mkr.add(mkr);
        w_t_l.add(w_mkr, BorderLayout.SOUTH);
        
        w_t.add(w_t_l, BorderLayout.CENTER);
        w_rank.add(rank);
        w_t.add(w_rank, BorderLayout.EAST);
        
        w_chat.add(chat);
        w_b.add(w_chat, BorderLayout.CENTER);
        w_b.add(exit, BorderLayout.EAST);
   
        add(w_t, BorderLayout.CENTER);
        add(w_b, BorderLayout.SOUTH);
        setVisible(true);
        ButtonListener listen = new ButtonListener();
        mkr.addActionListener(listen);
        exit.addActionListener(listen);
		
		
		
	}

}
