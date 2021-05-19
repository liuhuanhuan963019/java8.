package com.lhh.newtime;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

public class TestLocalDateTime {
    // 1.LocalDate  LocalTime  LocalDateTime
    @Test
    public void test1() {
        // 获取当前时间
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);  //2021-05-18T19:46:00.996

        LocalDateTime of = LocalDateTime.of(2021, 12, 4, 5, 20);
        System.out.println(of);


        //当前年份加上两年
        LocalDateTime localDateTime = ldt.plusYears(2);
        System.out.println(localDateTime);   //2023-05-18T19:46:00.996

        //当前月份减去两个月
        LocalDateTime localDateTime1 = ldt.minusMonths(2);
        System.out.println(localDateTime1);  //2021-03-18T19:47:13.529


        System.out.println("--------------------------");

        System.out.println(ldt.getYear());  //2021
        System.out.println(ldt.getMonthValue());  //5
        System.out.println(ldt.getDayOfMonth());   //18
        System.out.println(ldt.getHour());    //19
        System.out.println(ldt.getMinute());  //48
        System.out.println(ldt.getSecond());  //46
    }


    //2.Instant  时间戳（以1970年0点0时0分0秒到某个时间到毫秒值）  计算机所能识别的时间间隔
    @Test
        public void test2() {
            Instant ins1 = Instant.now();   //默认获取以utc时区为基础的
            System.out.println(ins1);

            // 带有偏移时间差的信息
            OffsetDateTime offsetDateTime = ins1.atOffset(ZoneOffset.ofHours(8));
            // 相比较utc加上8个小时
            System.out.println(offsetDateTime);  //2021-05-18T19:52:31.836+08:00


            //转换为毫秒值  ->时间戳
            System.out.println(ins1.toEpochMilli());   //1621338924590


            //依据时间戳显示时间
            Instant instant = Instant.ofEpochMilli(1);
            System.out.println(instant);   //1970-01-01T00:00:00.001Z
    }


    //3.Duation  计算两个时间之间的间隔
    //Period：计算两个"日期之间的间隔"
    @Test
    public void test3() {

        // Duation计算两个时间之间的间隔
        Instant ins1 = Instant.now();

        try{
            Thread.sleep(1000);
        }catch (InterruptedException e) {

        }
        Instant ins2 = Instant.now();

        Duration between = Duration.between(ins1, ins2);
        System.out.println(between.toMillis());  //计算两个时间之间的间隔  毫秒 1004

        System.out.println("--------------------------------------");

        LocalDateTime ldt1 = LocalDateTime.now();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        LocalDateTime ldt2 = LocalDateTime.now();
        System.out.println(Duration.between(ldt1, ldt2).toMillis());  //1001
    }

    @Test
    public void test4() {
        // period计算两个日期之间的间隔
        LocalDate ld1 = LocalDate.of(2021,4,10);
        LocalDate ld2 = LocalDate.now();

        Period between = Period.between(ld1, ld2);
        System.out.println(between);

        System.out.println(between.getYears());  //0
        System.out.println(between.getMonths());  //1
        System.out.println(between.getDays());   //8
    }


    //4.TemporaAdjuster; 时间矫正器
    @Test
    public void test5() {
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);   //2021-05-19T22:53:08.870

        LocalDateTime localDateTime = ldt.withDayOfMonth(10);
        System.out.println(localDateTime);   //2021-05-10T22:53:08.870

        //计算下一个周日
        LocalDateTime with = ldt.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println(with);   //2021-05-23T22:58:13.293


        //自定义下一个工作日
        LocalDateTime ldt5 = ldt.with((l) -> {
            LocalDateTime ldt4 = (LocalDateTime) l;

            DayOfWeek dayOfWeek = ldt4.getDayOfWeek();

            if (dayOfWeek.equals(DayOfWeek.FRIDAY)) {
                return ldt4.plusDays(3);
            } else if (dayOfWeek.equals(DayOfWeek.SATURDAY)) {
                return ldt4.plusDays(2);
            } else {
                return ldt4.plusDays(1);
            }
        });
        System.out.println(ldt5);   //2021-05-20T23:03:01.847
    }


    //5.时间格式化和时区的处理
    @Test
    public void test6() {
        //DateTimeFormatter  格式化时间和日期
        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;

        LocalDateTime ldt = LocalDateTime.now();

        String format = dtf.format(ldt);

        System.out.println(format);  //2021-05-19T23:06:59.431

        System.out.println("----------------------------------");


        // 时间->str
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy年MM月dd日");

        String format1 = dtf2.format(ldt);

        System.out.println(format1);  //2021年05月19日'

        //str->时间  解析字符串的时间格式
        LocalDate parse = LocalDate.parse(format1, dtf2);
        System.out.println(parse);   //2021-05-19
    }

    //6.时区的api操作
    @Test
    public void test7() {
        //列出所有的时区
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
//        availableZoneIds.forEach(System.out::println);


        //指定时区显示时间
        LocalDateTime now = LocalDateTime.now(ZoneId.of("America/Marigot"));

        System.out.println(now);   //2021-05-19T11:30:55.838

        LocalDateTime now1 = LocalDateTime.now();

        // 带有时区的时间和日期
        ZonedDateTime dateTime = now1.atZone(ZoneId.of("America/Marigot"));

        System.out.println(dateTime);   //2021-05-19T23:32:31.169-04:00[America/Marigot]
    }
}
