package Model;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import Starter.Application;
import javazoom.jl.player.Player;

public class Music extends Thread{
	private Player player;	
	private boolean isLoop; // �뷡�� ���ѹݺ������� ���� ����
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	
	public Music(String name, boolean isLoop)
	{
		try {
			this.isLoop = isLoop;
			/*
			// music�̶�� �����ȿ� �ִ� �ش��̸��� ������ �����Ŵ toURI�� ��ġ�� ������.
			file = new File(Application.class.getResource("C:\\Users\\hojja\\git\\tm_project14\\prj_MVC\\prj_MVC\\src\\music\\" + name).toURI());
			fis = new FileInputStream(file);
			//�ش� ������ ���ۿ� ��Ƽ� �о�� �� �ֵ��� �Ѵ�.
			bis = new BufferedInputStream(fis);
			//player�� �ش� ������ ��´�.
			player = new Player(bis);
			*/
			String path = "C:\\\\Users\\\\hojja\\\\Desktop\\\\�İ�\\\\2�г�\\\\JAVA\\\\Workspace\\\\prj_Client_v_1\\\\src\\\\music\\\\" + name;
			player = new Player(new FileInputStream(path));
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	// ���� �����ϴ� �޼ҵ�
	public void close()
	{
		isLoop = false;  // false ���� �־��ָ鼭 run�޼ҵ忡�� �� ����� �����Ŵ.
		player.close();
		this.interrupt(); //���� ����ϴ� �����带 ������.
	}
	
	
	// �����带 ����ϱ����� �� �ʿ��� run�޼ҵ�
	@Override
	public void run()
	{
		try {
			//isLoop���� true��� ���� ���ѹݺ���Ŵ.
			do {
				player.play();
			}while(isLoop);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}