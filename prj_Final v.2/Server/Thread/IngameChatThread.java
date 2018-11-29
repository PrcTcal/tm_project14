package Thread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import DB.DB;

import Server.WaitingServer;

public class IngameChatThread extends Thread{

	private Socket s, s2;
	private DataInputStream inputstream;
	private DataOutputStream outputstream;
	private String msg, ID;
	private Iterator it;
	private Collection collection;
	private HashMap hm;
	private DB db;
	private AnswerRound ar;
	

	public IngameChatThread(Socket s, HashMap hm, String ID, AnswerRound ar) {
		// TODO Auto-generated constructor stub
		this.s = s;
		this.hm = hm;
		this.ID = ID;
		this.ar = ar;
		
		try {
			inputstream = new DataInputStream(s.getInputStream());
			outputstream = new DataOutputStream(s.getOutputStream());

			synchronized(hm) {
				hm.put(ID,outputstream);
			}

		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean check_answer(String msg) {
		boolean check = false;

		if(msg.equals(ar.getAnswer())) {
			check = true;
			ar.setCheck(true);
			
		} 

		return check;
	}

	public void run() {
		try {

			while (inputstream != null) {
				msg = inputstream.readUTF();
				// 정답 비교
				System.out.println(msg);
				
				
				//--------------------------------------------------------------

				synchronized(hm) {
					collection = hm.values();
					it = collection.iterator();
				}
				try {
					while(it.hasNext()) {
						outputstream = (DataOutputStream)it.next();
						outputstream.writeUTF(ID + ": " + msg + "\n");
						if(check_answer(msg)) {
							System.out.println("잘된다");
							outputstream.writeUTF(ID + ">>님이 정답을 맞추셨습니다\n");
						} else {
							System.out.println("안된다");
						}
					}
				}catch(IOException e) {
					e.printStackTrace();
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			synchronized(hm) {
				hm.remove(ID);

			}
			try {
				if(s!=null)
					s.close();
			}catch(Exception ex) {}
		}	
	}
}

