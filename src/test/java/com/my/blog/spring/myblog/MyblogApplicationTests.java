package com.my.blog.spring.myblog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@SpringBootTest
class MyblogApplicationTests {

    @Test
    void contextLoads() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMMM yyyy, EEEE, HH:mm:ss");
        Calendar calendar = new GregorianCalendar();
        System.out.println(dateFormat.format(calendar.getTime()));
    }

}
