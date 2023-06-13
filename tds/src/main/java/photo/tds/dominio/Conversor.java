package photo.tds.dominio;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Conversor {
	private final static String FORMATOHORA = "d MMM y";

	public static Date StringToDate(String fecha) {
		try {
			return (Date) new SimpleDateFormat(FORMATOHORA).parse(fecha);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public static String DateToString(Date fecha) {
		DateFormat dateFormat = new SimpleDateFormat(FORMATOHORA);
		String strDate = dateFormat.format(fecha);
		return strDate;
	}
}
