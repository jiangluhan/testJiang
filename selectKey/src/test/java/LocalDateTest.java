import org.junit.Test;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author jiangluhan
 * @Description:
 * @date 2022/4/1 14:51
 */
public class LocalDateTest {
    @Test
    // 相当于是只要时间的前半部分，不要时间，只要年月日
    public void test() {
//        LocalDate localDate = toLocalDate("2018-01-01 13:00:59");
//        System.out.println(localDate);
//
//        System.out.println();
//
//        boolean compare = compare("2022-04-01 15:06:50");
//        System.out.println(compare);
//
//        System.out.println();
//
//        dateFormate("2022/04/02");
//        dateFormate("2022-04-02");

        // 获取当天零点时间
        LocalDateTime today = LocalDateTime.of(LocalDate.now(), LocalTime.of(0,0,0));
        System.out.println(today);
        // 获取当月第一天的零点时间
        LocalDateTime time = today.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println(time);
        String startTime = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        LocalDate startDay = LocalDateTime.parse(startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toLocalDate();
//        System.out.println(startDay);


        System.out.println("----------------------");

        LocalDate startDay = LocalDateTime.parse("2022-08-11 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toLocalDate();
        System.out.println(startDay);
        LocalDate endDay = LocalDateTime.parse("2022-08-17 23:59:59",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toLocalDate();
        long daysBetween = ChronoUnit.DAYS.between(startDay, endDay) + 1;
        System.out.println(daysBetween);
    }

//    public List<CountDto> dayCount(QueryDto param) {
//        TBDetectTask condition = new TBDetectTask();
//        //因为默认创建三员，新增的用户的id一定大于3，故写个硬编码 演示用
//        if(param.getUserId() > 3) {
//            condition.setUserId(param.getUserId());
//        }
//        if (ObjectUtils.isEmpty(param.getStartTime())) {
//            LocalDateTime today = LocalDateTime.of(LocalDate.now(), LocalTime.of(0,0,0));
//            param.setStartTime(today.format(DateTimeFormatter.ofPattern(GwConstant.DEFAULT_DATE_TIME_FORMAT)));
//        }
//        if (ObjectUtils.isEmpty(param.getEndTime())) {
//            LocalDateTime today = LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59,59));
//            param.setEndTime(today.format(DateTimeFormatter.ofPattern(GwConstant.DEFAULT_DATE_TIME_FORMAT)));
//        }
//        condition.setReservedField1(param.getStartTime());
//        condition.setReservedField2(param.getEndTime());
//        //计算开始结束的所有日期
//        LocalDate startDay = LocalDateTime.parse(param.getStartTime(),DateTimeFormatter.ofPattern(GwConstant.DEFAULT_DATE_TIME_FORMAT))
//                .toLocalDate();
//        LocalDate endDay = LocalDateTime.parse(param.getEndTime(),DateTimeFormatter.ofPattern(GwConstant.DEFAULT_DATE_TIME_FORMAT))
//                .toLocalDate();
//        long daysBetween = ChronoUnit.DAYS.between(startDay, endDay) + 1;
//
////        Set<String> allDay = IntStream.iterate(0,i->i+1).limit(daysBetween).mapToObj(i->startDay.plusDays(i))
////                .sorted().map(item->item.toString()).collect(Collectors.toSet());
//        List<TBDetectTask> lstTask = taskMapper.selectByTime(condition);
//        //按日统计
//        List<CountDto> dayCount = lstTask.stream().map(item->{// 这里其实就是将LocalDate的时间形式转成字符串复制给属性reservedField2
//            String strDate = item.getCreateTime().toLocalDate().toString();
//            //日期分组
//            item.setReservedField2(strDate);
//            return item;
//        }).collect(Collectors.groupingBy(TBDetectTask::getReservedField2)) // 根据字段reservedField2分组
//                .entrySet().stream().map(item->{
//                    CountDto countDto = new CountDto();
//                    countDto.setTag(item.getKey());
//                    Long totalCount = item.getValue().stream().collect(Collectors.summingLong(TBDetectTask::getTotalCount));
//                    countDto.setTotalCount(totalCount);
//                    Long exceptionCount = item.getValue().stream().collect(Collectors.summingLong(TBDetectTask::getExceptionCnt));
//                    countDto.setExeptionCount(exceptionCount);
//                    countDto.setNormalCount(totalCount - exceptionCount);
//                    return countDto;
//                })
//                .collect(Collectors.toList());
//
//        List<CountDto> dayCountAll = IntStream.iterate(0, i->i+1).limit(daysBetween).mapToObj(i->startDay.plusDays(i))
//                .sorted().map(item->item.toString()).map(dayItem->{
//                    CountDto countDto = dayCount.stream().filter(valueItem->valueItem.getTag().equals(dayItem))
//                            .findFirst().orElse(null);
//                    if (ObjectUtils.isEmpty(countDto)) {
//                        countDto = new CountDto();
//                        countDto.setTag(dayItem);
//                        Long totalCount = 0L;
//                        countDto.setTotalCount(totalCount);
//                        Long exceptionCount = 0L;
//                        countDto.setExeptionCount(exceptionCount);
//                        countDto.setNormalCount(0L);
//                    }
//                    countDto.setTag(countDto.getTag()+"日");
//                    return countDto;
//                }).collect(Collectors.toList());
//        return dayCountAll;
//    }

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

    private void dateFormate(String endTimeString) {
        try {
            // 校验时间是否符合yyyy-MM-dd格式，不符合就抛出异常
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate.parse(endTimeString, df);
            System.out.println("授权到期时间格式正确");
        } catch (Exception e) {
            System.out.println("授权到期时间格式错误");
        }
    }
}
