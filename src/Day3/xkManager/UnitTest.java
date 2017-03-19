package Day3.xkManager;

import Day3.xkManager.Utils.DateUtils;
import org.junit.jupiter.api.Test;

import java.sql.Date;

/**
 * date
 * Created by gjp06 on 17.3.17.
 */
public class UnitTest {

    @Test
    public void testGetDate() {
        String cardId = "412030198604215096";
        Date date = DateUtils.getDate(cardId);
        System.out.println(date);


//        String d = "2014-03-04";
//        Date dat = Date.valueOf(d);
//        System.out.println(dat);

//        String dd = DateUtils.YMDDate();

    }
}
