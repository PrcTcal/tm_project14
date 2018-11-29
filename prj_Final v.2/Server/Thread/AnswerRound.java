package Thread;

public class AnswerRound {
	private boolean Check;
	private String answer;
	private int Round;
	
	public AnswerRound() {
		Check = false;
		answer = "";
		Round = 1;
	}
	
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public boolean isCheck() {
		return Check;
	}
	public void setCheck(boolean check) {
		Check = check;
	}
	public int getRound() {
		return Round;
	}
	public void setRound(int round) {
		Round = round;
	}
	
	
}
