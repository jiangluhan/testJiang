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

        System.out.println();

        boolean compare = compare("2022-04-01 15:06:50");
        System.out.println(compare);
    }

    public boolean compare(String time) {
        // 当前时间往后推一天
        LocalDate now = LocalDate.now().plusDays(1L);
        System.out.println("系统时间加上一天后：" + now);

        // 当前时间
        LocalDate t = toLocalDate(time);
        System.out.println("参数时间：" + t);

        // 当前时间往后推七天
        System.out.println("参数时间加上七天后：" + t.plusDays(7L));

        // 判断now是否比t晚并且now是否比t+7早
        return now.isAfter(t) && now.isBefore(t.plusDays(7L));
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
