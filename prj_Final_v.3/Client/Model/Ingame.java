package Model;
// Ingame ��
public class Ingame {
	private String title;	// �� ����
	private String pw;		// �� ��й�ȣ
	private String info;	// �� ����(�ӽ÷� ����� ����. ���Ŀ� ���� �Ǵ� ���� ����)
	private String round;
	private String theme;
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
	
	public String getRound() {
		return round;
	}

	public void setRound(String round) {
		this.round = round;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}
	
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}


	
}
