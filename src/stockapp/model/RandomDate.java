package stockapp.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class RandomDate implements Serializable {
	Random random=new Random();
	SimpleDateFormat sdf= new SimpleDateFormat("dd.MM.yyyy");
	Calendar cal=Calendar.getInstance();
	private String str1="01.01.1989";
	private String str2=cal.get((Calendar.DAY_OF_MONTH))+"."+
			cal.get(Calendar.MONTH)+"."+cal.get(Calendar.YEAR);
	
	public String GetRandomDate() throws ParseException{
		cal.setTime(sdf.parse(str1));
		Long value1 = cal.getTimeInMillis();
		
		cal.setTime(sdf.parse(str2));
        Long value2 = cal.getTimeInMillis();
        
        long value3 = (long)(value1 + Math.random()*(value2 - value1));
        cal.setTimeInMillis(value3);
        
        return sdf.format(cal.getTime());
	}

}
