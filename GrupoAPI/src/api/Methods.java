package api;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.bukkit.event.Listener;

public class Methods
  implements Listener
{

	
	public static String getRemainingTime(long seconds)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(seconds);
		int mYear = calendar.get(Calendar.YEAR);
		int mMonth = calendar.get(Calendar.MONTH);
		int mDay = calendar.get(Calendar.DAY_OF_MONTH);
		int Hour = calendar.get(Calendar.HOUR_OF_DAY);
		int Minute = calendar.get(Calendar.MINUTE);
		mMonth++;
		if(mMonth > 12){
			mMonth = 01;
		}
		return (String.valueOf(mDay).length() == 1 ? "0" :"" )+
				mDay+"/"+(String.valueOf(mMonth).length() == 1 ? "0" :"" )+
				mMonth+"/"+mYear+" às "+(String.valueOf(Hour).length() == 1 ? "0" :"" )+
				Hour+":"+(String.valueOf(Minute).length() == 1 ? "0" :"" )+Minute;
	}
	
	
	public static String formatExpira(Long log){
		String time = "Nenhum";
		long banTime = log;
		long timeLeft = banTime - System.currentTimeMillis();
	      long seconds = timeLeft / 1000L;
	      time = Methods.getRemainingTime(seconds);
	      return time;
	}
	
  public static String getRemainingTime(String time, long seconds)
  {
    int day = (int)TimeUnit.SECONDS.toDays(seconds);
    long hours = TimeUnit.SECONDS.toHours(seconds) - day * 24;
    long minute = TimeUnit.SECONDS.toMinutes(seconds) - TimeUnit.SECONDS.toHours(seconds) * 60L;
    long second = TimeUnit.SECONDS.toSeconds(seconds) - TimeUnit.SECONDS.toMinutes(seconds) * 60L;
    String sDay = day + "d ";String sHour = hours + "h ";String sMinute = minute + "m ";String sSecond = second + "s ";
    if (day == 0) {
      sDay = "";
    }
    if (hours == 0L) {
      sHour = "";
    }
    if (minute == 0L) {
      sMinute = "";
    }
    if (second == 0L) {
      sSecond = "";
    }
    time = sDay + sHour + sMinute + sSecond;
    time = time.trim();
    if (time.equals("")) {
      time = "Nenhum";
    }
    return time;
  }
}
