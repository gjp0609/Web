package Day3.xkManager.Utils;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * Created by gjp06 on 17.3.17.
 */
public class DateUtils {
    public static String YMDDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        return sdf.format(date);
    }

    //412903 19990101 2222
    public static Date getDate(String idCard) {
        StringBuilder sb = null;
        Date date = null;
        if (idCard.length() == 18) {
            sb = new StringBuilder();
            sb.append(idCard.substring(6, 10));
            sb.append("-");
            sb.append(idCard.substring(10, 12));
            sb.append("-");
            sb.append(idCard.substring(12, 14));
        }
        if (sb != null) date = Date.valueOf(sb.toString());
        return date;
    }
}
