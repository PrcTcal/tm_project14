package Thread;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import Control.IngameControler;
import Control.WaitingControler;
import Model.waiting;
import Starter.Application;
import View.WaitingView;

public class MakeRoomRecieveThread extends Thread  {
	private String title,pw, round, theme;
	private DataInputStream inputstream;
	private Socket s;
	private Application app;
	private WaitingView view;
	private IngameControler ic;
	private waiting w;

	public MakeRoomRecieveThread(Socket s, WaitingView view, IngameControler ic, waiting w) {
		this.s = s;
		this.ic =ic;
		this.view = view;
		this.w = w;
		try {
			inputstream = new DataInputStream(s.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	public void run() {
		try {
			while(inputstream!=null) {
				title = inputstream.readUTF();
				pw = inputstream.readUTF();
				round = inputstream.readUTF();
				theme = inputstream.readUTF();
				
				ic.setTitle(w.getRoom_num(), title);
				ic.setPW(w.getRoom_num(), pw);
				ic.setRound(w.getRoom_num(), round);
				ic.setTheme(w.getRoom_num(), theme);
				//
				
				view.getR(w.getRoom_num()).setEnabled(true);
				view.getR(w.getRoom_num()).setText(title);
				
				//
				w.addRoom_Num();
			}
		}catch(IOException e){
			e.printStackTrace();
		}

	}
}





