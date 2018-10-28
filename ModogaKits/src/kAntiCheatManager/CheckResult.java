package kAntiCheatManager;

public class CheckResult {

	private Level level;
	private String message;
	
	public CheckResult(Level lv, String msg) {
		level = lv;
		message = msg;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
