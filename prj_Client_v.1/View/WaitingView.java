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
	private DefaultTableModel model;	// ���̺� �� �����͸� �𵨸��ϴ� ��ü
	private JTable table;				// ���� ���̴� ���̺�
	private JScrollPane sc;				// ��ũ���� �������� �� �ִ� ��
	
	//������ �޼ҵ�
	public WaitingView() {
		// ���̺��� ������ �����
        String []a= {"����","ID","����"};
        // �켱�� �� ĭ�� ���� ��ĭ���� �ʱ�ȭ
		String [][]b = {{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""}};
		
		
		// �����͸� ���� �𵨸� ���� �� ���̺� �𵨸� �߰�
		// ���̺� �� ���� ����
		model = new DefaultTableModel(b,a) {
			public boolean isCellEditable(int i, int c){ 
				return false; 
			}
		};
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
        r[0] = new JButton("��1");
        r[1] = new JButton("��2");
        r[2] = new JButton("��3");
        r[3] = new JButton("��4");
        
        
        mkr = new JButton("�游���");
        exit = new JButton("����");
        chat = new JTextArea("�̱���(ä��)",10,78);
        
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

	// �� ��ư ��ȯ
	public static JButton getR(int i) {
		return r[i];
	}
	
	public JTable getTable() {
		return table;
	}
	
}
