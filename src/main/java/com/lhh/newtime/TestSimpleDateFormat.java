package com.lhh.newtime;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TestSimpleDateFormat extends Exception{

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//
//        Callable<Date> task = new Callable<Date>() {
//            @Override
//            public Date call() throws Exception {
//                //线程安全方式
//                return DateFormatThreadLocal.convert("20160812");
//                //线程不安全方式
////                return sdf.parse("20160812");
//            }
//        };
//
//        ExecutorService pool = Executors.newFixedThreadPool(10);
//
//        List<Future<Date>> futures = new ArrayList<>();
//
//        for (int i = 0; i < 10; i++) {
//            futures.add(pool.submit(task));
//        }
//
//
//        for (Future<Date> future : futures) {
//
//            System.out.println(future.get());
//        }
//
//        pool.shutdown();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");

        Callable<LocalDate> task = new Callable<LocalDate>() {
            @Override
            public LocalDate call() throws Exception {
                //线程安全方式
                return LocalDate.parse("20160812", dtf);
                //线程不安全方式
//                return sdf.parse("20160812");
            }
        };

        ExecutorService pool = Executors.newFixedThreadPool(10);

        List<Future<LocalDate>> futures = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            futures.add(pool.submit(task));
        }


        for (Future<LocalDate > future : futures) {

            System.out.println(future.get());
        }

        pool.shutdown();
    }
}
