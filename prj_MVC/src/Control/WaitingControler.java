package Control;
import javax.swing.*;
import Starter.Application;
import Model.DB;
import Model.waiting;
import Model.Music;
import View.RegisterView;
import View.WaitingView;
import View.MakeRoomView;
import View.RoomLockView;
import Control.MakeRoomControler;
import Control.IngameControler;
import Control.RoomLockControler;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
public class WaitingControler {
	private Application app;
	private waiting w;	// 대기실 모델 객체
	private ButtonListener listen;
	private WaitingView view;
	private MakeRoomView mv;
	private MakeRoomControler mc;
	private IngameControler ic;
	private RoomLockView rv;
	private RoomLockControler rc;
	private DB db;
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			// 방만들기 버튼 선택시
			if(event.getSource() == view.getMkr()) {
				// 생성된 방이 4개 미만일때
				if(w.getRoom_num() < 4) {
					// 방만들기 창 생성
					mv = new MakeRoomView();
					mc = new MakeRoomControler(app,mv,WaitingControler.this,ic);
					mc.buttonHandler();
				// 생성된 방이 4개 이상일때
				} else {
					JOptionPane.showMessageDialog(null, "더 이상 방을 생성할 수 없습니다!");
				}
			// 종료 버튼 선택시
			} else if(event.getSource() == view.getExit()){
				app.offMusic();
				app.dispose();
			// 방 버튼 선택시
			} else {
				// 4가지 방중에서 선택된 방이 어떤 방인지 찾는다.
				for(int i = 0 ; i < 4 ; i++) {
					if(event.getSource() == view.getR(i)) {
						// 대기실 모델에 선택한 방이 몇번방인지 저장
						w.setSelect(i);
						// 선택한 방에 비밀번호가 설정되있지 않을시
						if(ic.getPW(i).equals("")){
							JOptionPane.showMessageDialog(null, "방에 입장합니다");
							app.changePanel();
							app.setTitle("방제목 : " + ic.getTitle(i));
							// 무시해도됨
							ic.setInfo(w.getSelect());
						// 선택한 방에 비밀번호가 설정 되있을시
						} else {
							// 비밀번호 입력 창 생성
							rv = new RoomLockView();
							rc = new RoomLockControler(app,rv,WaitingControler.this, ic);
							rc.buttonHandler();
						}
					}
				}
				
			}
		}
	}
	
	// 파라매터로 문자열을 받아 방 제목으로 지정해주고 방 버튼을 활성화 시키는 메소드
	public void Enable_Room(String title) {
		view.getR(w.getRoom_num()).setEnabled(true);
		view.getR(w.getRoom_num()).setText(title);
		w.addRoom_Num();
	}
	
	public int getRoom_num() {
		return w.getRoom_num();
	}
	
	public int getSelect() {
		return w.getSelect();
	}
	
	// 대기실의 순위표를 최신화 시켜주는 메소드
	public void update_rank() {
		try {
			db.connect();
			int i = 1;
			int j=0;
			// DBMS에 "account 테이블에서 id, score를 점수 높은순으로 추출해라"라는 명령어를 실행한 뒤 결과값을 rs에 저장
			db.read("select id,score from account order by score desc;");

			// DB에서 가져온 id와 score를 순서대로 순위표 테이블(JTable)에 순위, ID, 점수 순으로 저장
			while(db.rs.next()) {
				String sqlRecipeProcess_id = db.rs.getString("id");
				String sqlRecipeProcess_score = db.rs.getString("score");
				view.getTable().setValueAt(i, j, 0);
				view.getTable().setValueAt(sqlRecipeProcess_id, j,1);
				view.getTable().setValueAt(sqlRecipeProcess_score, j, 2);
				i++;
				j++;
			}
			// 순위표 테이블(JTable)의 내용을 최신화
			view.getTable().updateUI();
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
	
	// 생성자 메소드
	public WaitingControler(Application app, WaitingView wv, IngameControler ic) {
		db = new DB();
		w = new waiting();
		this.app = app;
		this.view = wv;
		this.ic = ic;
	}
	
	public void buttonHandler() {
		listen = new ButtonListener();
		view.getMkr().addActionListener(listen);
        view.getExit().addActionListener(listen);
        view.getR(0).addActionListener(listen);
        view.getR(1).addActionListener(listen);
        view.getR(2).addActionListener(listen);
        view.getR(3).addActionListener(listen);
	}	
}
