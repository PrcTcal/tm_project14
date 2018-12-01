package Server;

public class MainServer {

	public static void main(String[] args) {
		LoginRegisterServer mainserver = new LoginRegisterServer();
		mainserver.start();
		WaitingServer waitingserver = new WaitingServer();
		waitingserver.start();
		IngameServer ingameserver = new IngameServer();
		ingameserver.start();

	}

}
