package Model;
// ���� ��
public class waiting {
	private int room_num;		// �������ִ� �� ����
	private int select;			// ���� ������ �� ��ȣ
	
	// ������ �޼ҵ�
	public waiting() {
		room_num = 0;
		select = 0;
	}

	public int getRoom_num() {
		return room_num;
	}

	// ������ �� ������ 1 ������Ű�� �޼ҵ�
	// �游��� â���� ���� ���� �� �� �޼ҵ带 ȣ���Ѵ�
	public void addRoom_Num() {
		room_num += 1;
	}

	public int getSelect() {
		return select;
	}
	
	// ������ ���� ��ư�� ���° ������ �� ���ڸ� �����ϴ� �޼ҵ�
	// ���ǿ��� �濡 ������ �� �� �޼ҵ带 ȣ���Ѵ�
	public void setSelect(int select) {
		this.select = select;
	}
	
	
}
