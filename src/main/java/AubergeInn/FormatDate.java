package AubergeInn;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// classe de formatDate
public class FormatDate {
    private static SimpleDateFormat formatAMJ;

    static
    {
        formatAMJ = new SimpleDateFormat("yyyy-MM-dd");
        formatAMJ.setLenient(false);
    }


    public static Date convertirDate(String dateString) throws ParseException

    {
        return formatAMJ.parse(dateString);

    }

    public static String toString(Date date)
    {
        return formatAMJ.format(date);
    }
}

