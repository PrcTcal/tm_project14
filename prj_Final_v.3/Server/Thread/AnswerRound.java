package Thread;

import java.sql.SQLException;
import java.util.Random;
import DB.DB;

public class AnswerRound {
	private boolean Check, game_ends, num_check;
	private String answer;
	private int Round, max_round, checked_num;
	private DB db;
	private String sqlRecipeProcess_word;
	private int[] used_num;
	private Random rand;
	
	public AnswerRound() {
		game_ends = false;
		Check = false;
		answer = "";
		Round = 1;
		db = new DB();
		used_num = new int[10];
		for(int i = 0 ; i < 10 ; i++) {
			used_num[i] = -1;
		}
		checked_num = 0;
		num_check = false;
		rand = new Random();
	}
	
	public void bring_questions(int round, String t) {
		try {
			
			String theme = "";
			if(t.equals("영화")) {
				theme = "movie";
			} else if(t.equals("동물")) {
				theme = "animal";
			} else if(t.equals("애니메이션")) {
				theme = "animation";
			} else if(t.equals("연예인")) {
				theme = "celebrity";
			}
			db.connect();
			db.read("select word from " + theme + ";");
			
			
			if(used_num[0] == -1) {
				used_num[0] = rand.nextInt(10);
			} else {
				while(true) {
					int tmp = rand.nextInt(10);
					while(used_num[checked_num] != -1) {

						if(used_num[checked_num] == tmp) {
							num_check = true;
						}
						checked_num++;
					}
					if(num_check == false) {
						used_num[checked_num] = tmp;
						checked_num = 0;
						num_check = false;
						break;
					}
					checked_num = 0;
					num_check = false;
				}
				
			}
			
			System.out.println(used_num[round-1]);
			
			for(int k = 0 ; k <= used_num[round-1] ; k++) {
				db.rs.next();
				answer = db.rs.getString("word");
			}
			
			
			db.rs.close();
			db.st.close();
			db.connection.close();
		} catch (SQLException se1) {
			se1.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (db.st != null)
					db.st.close();
			} catch (SQLException se2) {
			}
			try {
				if (db.connection != null)
					db.connection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}    
	}
	
	public void reset_array() {
		for(int i = 0 ; i < 10 ; i++) {
			used_num[i]=-1;
		}
	}
	
	public void print_used_num() {
		for(int i = 0 ; i < 10 ; i++) {
			System.out.print(used_num[i] + " ");
		}
		System.out.println();
	}
	
	public int getMax_round() {
		return max_round;
	}

	public void setMax_round(int max_round) {
		this.max_round = max_round;
	}

	public boolean isGame_ends() {
		return game_ends;
	}

	public void setGame_ends(boolean game_ends) {
		this.game_ends = game_ends;
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
