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
	private DefaultTableModel model;	// ���̺� �� �����͸� �𵨸��ϴ� ��ü
	private JTable table;				// ���� ���̴� ���̺�
	private JScrollPane sc;				// ��ũ���� �������� �� �ִ� ��
	private DB db;
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(event.getSource() == exit) {
				JOptionPane.showMessageDialog(null, "������ �����մϴ�");
				F.dispose();
			} else if(event.getSource() == mkr){
				if(room_num < 4) {
					mr = new make_room(F);
				} else {
					JOptionPane.showMessageDialog(null, "�� �̻� ���� ������ �� �����ϴ�!");
				}
			} else {
				JOptionPane.showMessageDialog(null, "�濡 �����մϴ�");
				F.changePanel();
				F.setTitle("Catch-ur-Crayon");
			} 
		}
	}
	
	// room_num�� ��ȯ
	public static int getRoom_num() {
		return room_num;
	}
	// room_num�� i��ŭ ����
	public static void rn_increase(int i) {
		room_num += i;
	}
	
	// ����ǥ�� �����͸� �ֽ�ȭ�ϴ� �޼ҵ�
	public void update_rank() {
				
				try {
					db.connect();
					int i = 1;
					int j=0;
					// DBMS�� "account ���̺��� id, score�� ���� ���������� �����ض�"��� ��ɾ ������ �� ������� rs�� ����
					db.rs = db.st.executeQuery("select id,score from account order by score desc;");
					
					// DB���� ������ id�� score�� ������� ����ǥ ���̺�(JTable)�� ����, ID, ���� ������ ����
					while(db.rs.next()) {
						String sqlRecipeProcess_id = db.rs.getString("id");
						String sqlRecipeProcess_score = db.rs.getString("score");
						table.setValueAt(i, j, 0);
						table.setValueAt(sqlRecipeProcess_id, j,1);
						table.setValueAt(sqlRecipeProcess_score, j, 2);
						i++;
						j++;
					}
					// ����ǥ ���̺�(JTable)�� ������ �ֽ�ȭ
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
        
        
        // ���̺��� ������ �����
        String []a= {"����","ID","����"};
        // �켱�� �� ĭ�� ���� ��ĭ���� �ʱ�ȭ
		String [][]b = {{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""}};
		
		
		// �����͸� ���� �𵨸� ���� �� ���̺� �𵨸� �߰�
		model = new DefaultTableModel(b,a);
		table = new JTable(model);
		// ���̺��� ĭ�� ũ�� ����
		table.getColumnModel().getColumn(0).setPreferredWidth(30); 
		table.getColumnModel().getColumn(1).setPreferredWidth(130); 
		table.getColumnModel().getColumn(2).setPreferredWidth(40); 
		// ��ũ���ǿ� ���̺� �߰�
		sc = new JScrollPane(table);
		sc.setPreferredSize(new Dimension(200,345));
		
		
		// DefaultTableCellHeaderRenderer ���� (��� ������ ����)
		DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
		// DefaultTableCellHeaderRenderer�� ������ ��� ���ķ� ����
		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		// ������ ���̺��� ColumnModel�� ������
		TableColumnModel tcmSchedule = table.getColumnModel();

		
		// �ݺ����� �̿��Ͽ� ���̺��� ��� ���ķ� ����
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
		tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
		}
		
		// DB ���� �� ����ǥ �ֽ�ȭ
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
        r[0] = new JButton("��1");
        r[1] = new JButton("��2");
        r[2] = new JButton("��3");
        r[3] = new JButton("��4");
        
        
        mkr = new JButton("�游���");
        exit = new JButton("����");
        chat = new JTextArea("�̱���(ä��)",10,78);
        
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
