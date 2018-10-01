package prj_temp;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class waiting extends JPanel{
	
	private JButton mkr, exit;
	public static JButton[] r;
	private JPanel w_t, w_b, w_t_l, w_t_l_r, w_mkr, w_rank, w_chat;
	private start F;
	private JTextArea chat;
	private make_room mr;
	private static int room_num;
	private DefaultTableModel model;	// 테이블에 들어갈 데이터를 모델링하는 객체
	private JTable table;				// 눈에 보이는 테이블
	private JScrollPane sc;				// 스크롤을 오르내릴 수 있는 판
	private DB db;
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(event.getSource() == exit) {
				JOptionPane.showMessageDialog(null, "게임을 종료합니다");
				F.dispose();
			} else if(event.getSource() == mkr){
				if(room_num < 4) {
					mr = new make_room(F);
				} else {
					JOptionPane.showMessageDialog(null, "더 이상 방을 생성할 수 없습니다!");
				}
			} else {
				JOptionPane.showMessageDialog(null, "방에 입장합니다");
				F.changePanel();
				F.setTitle("Catch-ur-Crayon");
			} 
		}
	}
	
	// room_num을 반환
	public static int getRoom_num() {
		return room_num;
	}
	// room_num을 i만큼 증가
	public static void rn_increase(int i) {
		room_num += i;
	}
	
	// 순위표의 데이터를 최신화하는 메소드
	public void update_rank() {
				
				try {
					db.connect();
					int i = 1;
					int j=0;
					// DBMS에 "account 테이블에서 id, score를 점수 높은순으로 추출해라"라는 명령어를 실행한 뒤 결과값을 rs에 저장
					db.rs = db.st.executeQuery("select id,score from account order by score desc;");
					
					// DB에서 가져온 id와 score를 순서대로 순위표 테이블(JTable)에 순위, ID, 점수 순으로 저장
					while(db.rs.next()) {
						String sqlRecipeProcess_id = db.rs.getString("id");
						String sqlRecipeProcess_score = db.rs.getString("score");
						table.setValueAt(i, j, 0);
						table.setValueAt(sqlRecipeProcess_id, j,1);
						table.setValueAt(sqlRecipeProcess_score, j, 2);
						i++;
						j++;
					}
					// 순위표 테이블(JTable)의 내용을 최신화
					table.updateUI();
					db.rs.close();
					db.st.close();
					db.connection.close();
				} catch (SQLException se1) {
					se1.printStackTrace();
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					try {
						if (db.st != null)
							db.st.close();
					} catch (SQLException se2) {
					}
					try {
						if (db.connection != null)
							db.connection.close();
					} catch (SQLException se) {
						se.printStackTrace();
					}
				}    
	}
	
	
	public waiting(start f) {
        setLayout(new BorderLayout());
        F=f;
        room_num = 0;
        
        
        // 테이블의 데이터 내용들
        String []a= {"순위","ID","점수"};
        // 우선은 각 칸의 값을 빈칸으로 초기화
		String [][]b = {{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""}};
		
		
		// 데이터를 넣을 모델링 생성 및 테이블에 모델링 추가
		model = new DefaultTableModel(b,a);
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
		
		// DB 생성 및 순위표 최신화
		db = new DB();
		update_rank();
		
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
        //sc.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
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
        setVisible(true);
        ButtonListener listen = new ButtonListener();
        mkr.addActionListener(listen);
        exit.addActionListener(listen);
        r[0].addActionListener(listen);
        r[1].addActionListener(listen);
        r[2].addActionListener(listen);
        r[3].addActionListener(listen);
		
		
		
	}

}
