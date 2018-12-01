package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import Thread.LoginRegisterThread;

public class LoginRegisterServer extends Thread{

	private Socket client = null;
	private ServerSocket server =null;
	private LoginRegisterThread lt = null;
	
	
	static public int getPort() {
		return 8046;
	}
	
	
	public void run() {
		try {
			server = new ServerSocket(getPort());   
			while(true) {
				client = server.accept();
				lt = new LoginRegisterThread(client);
				lt.start();
			}   
		}catch(IOException e) {
			e.printStackTrace();
		}
	}   



}
