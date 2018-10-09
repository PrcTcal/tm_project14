package View;
import Starter.Application;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java.awt.*;
import java.awt.event.*;
public class WaitingView extends JPanel{
	private JButton mkr, exit;
	public static JButton[] r;
	private JPanel w_t, w_b, w_t_l, w_t_l_r, w_mkr, w_rank, w_chat;
	private Application app;
	private JTextArea chat;
	private DefaultTableModel model;	// 테이블에 들어갈 데이터를 모델링하는 객체
	private JTable table;				// 눈에 보이는 테이블
	private JScrollPane sc;				// 스크롤을 오르내릴 수 있는 판
	
	//생성자 메소드
	public WaitingView() {
		// 테이블의 데이터 내용들
        String []a= {"순위","ID","점수"};
        // 우선은 각 칸의 값을 빈칸으로 초기화
		String [][]b = {{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""}};
		
		
		// 데이터를 넣을 모델링 생성 및 테이블에 모델링 추가
		// 테이블 셀 접근 방지
		model = new DefaultTableModel(b,a) {
			public boolean isCellEditable(int i, int c){ 
				return false; 
			}
		};
		table = new JTable(model);
		// 테이블의 칸별 크기 조정
		table.getColumnModel().getColumn(0).setPreferredWidth(30); 
		table.getColumnModel().getColumn(1).setPreferredWidth(130); 
		table.getColumnModel().getColumn(2).setPreferredWidth(40); 
		// 스크롤판에 테이블 추가
		sc = new JScrollPane(table);
		sc.setPreferredSize(new Dimension(200,345));
		
		
		// DefaultTableCellHeaderRenderer 생성 (가운데 정렬을 위한)
		DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
		// DefaultTableCellHeaderRenderer의 정렬을 가운데 정렬로 지정
		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		// 정렬할 테이블의 ColumnModel을 가져옴
		TableColumnModel tcmSchedule = table.getColumnModel();

		
		// 반복문을 이용하여 테이블을 가운데 정렬로 지정
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
		tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
		}
		
        setLayout(new BorderLayout());
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
        
        
        r = new JButton[4];
        r[0] = new JButton("방1");
        r[1] = new JButton("방2");
        r[2] = new JButton("방3");
        r[3] = new JButton("방4");
        
        
        mkr = new JButton("방만들기");
        exit = new JButton("종료");
        chat = new JTextArea("미구현(채팅)",10,78);
        
        Border lineBorder = BorderFactory.createLineBorder(Color.black, 3);
        Border emptyBorder = BorderFactory.createEmptyBorder(7, 7, 7, 7);
       
        chat.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
        
        r[0].setEnabled(false);
        r[1].setEnabled(false);
        r[2].setEnabled(false);
        r[3].setEnabled(false);
        
        
    	w_t_l_r.add(r[0]);
        w_t_l_r.add(r[1]);
        w_t_l_r.add(r[2]);
        w_t_l_r.add(r[3]);
    	
        
        w_t_l.add(w_t_l_r, BorderLayout.CENTER);
        w_mkr.add(mkr);
        w_t_l.add(w_mkr, BorderLayout.SOUTH);
        
        w_t.add(w_t_l, BorderLayout.CENTER);
        w_rank.add(sc);
        w_t.add(w_rank, BorderLayout.EAST);
        
        w_chat.add(chat);
        w_b.add(w_chat, BorderLayout.CENTER);
        w_b.add(exit, BorderLayout.EAST);
   
        add(w_t, BorderLayout.CENTER);
        add(w_b, BorderLayout.SOUTH);
        
        
       
	}

	public JButton getMkr() {
		return mkr;
	}

	public JButton getExit() {
		return exit;
	}

	// 방 버튼 반환
	public static JButton getR(int i) {
		return r[i];
	}
	
	public JTable getTable() {
		return table;
	}
	
}
