package photo.tds.helpers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConversorDate {
	public static Date StringToDate(String fecha) {
		DateFormat conversor = new SimpleDateFormat("d MMM y");
		try {
			return conversor.parse(fecha);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
