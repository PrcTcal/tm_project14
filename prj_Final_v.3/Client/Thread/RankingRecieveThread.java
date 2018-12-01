package Thread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import View.WaitingView;

public class RankingRecieveThread extends Thread {

	private WaitingView wv;
	private Socket s;
	private DataInputStream inputstream;
	private DataOutputStream outputstream;
	private String ID, user;
	
	public RankingRecieveThread(Socket s, WaitingView wv, String user) {
		this.s = s;
		this.wv = wv;
		this.user = user;
		try {
			inputstream = new DataInputStream(s.getInputStream());
			outputstream = new DataOutputStream(s.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void run() {
//		int num,i,j;
//		int count = 0;
//		String ID, Score;
		
		try {

			while(outputstream != null) {
				int num,i,j;
				int count = 0;
				String ID, Score;
				outputstream.writeUTF(user);
				num = Integer.parseInt(inputstream.readUTF());
				while(count < num) {
					i = Integer.parseInt(inputstream.readUTF());
					j = Integer.parseInt(inputstream.readUTF());
					ID = inputstream.readUTF();
					Score = inputstream.readUTF();
					System.out.println(ID + "   " + Score);
					wv.getTable().setValueAt(i, j, 0);
					wv.getTable().setValueAt(ID, j,1);
					wv.getTable().setValueAt(Score, j, 2);
					count ++;
				}
			}
		} catch (IOException e) {

		}
	}
}
