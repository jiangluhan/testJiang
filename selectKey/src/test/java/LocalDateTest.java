import org.junit.Test;

import java.time.LocalDate;

/**
 * @author jiangluhan
 * @Description:
 * @date 2022/4/1 14:51
 */
public class LocalDateTest {
    @Test
    // 相当于是只要时间的前半部分，不要时间，只要年月日
    public void test() {
        LocalDate localDate = toLocalDate("2018-01-01 13:00:59");
        System.out.println(localDate);
    }


    /**
     * 传入参数必须是 yyyy-MM-dd HH:mm:ss, 如： 2018-01-01 13:00:59
     *
     * @param time
     * @return
     */
    private static LocalDate toLocalDate(String time) {
        return LocalDate.parse(time.trim().split(" ")[0]);
    }
}
