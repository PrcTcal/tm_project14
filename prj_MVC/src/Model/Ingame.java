package Model;
// Ingame ��
public class Ingame {
	private String title;	// �� ����
	private String pw;		// �� ��й�ȣ
	private String info;	// �� ����(�ӽ÷� ����� ����. ���Ŀ� ���� �Ǵ� ���� ����)
	
	// ������ �޼ҵ�
	public Ingame(String i) {
		title = "";
		pw = "";
		info = i;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	
}
