package com.lowkey.complex.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author lowkey
 * @description 定时任务, 具体规则看下方注释
 * @date 2023年01月09日 17:01
 */
@Component
public class TestSchedule {
    private final Logger logger = LoggerFactory.getLogger(TestSchedule.class);

    /**
     * @author yuanjifan
     * @description 测试定时任务, 每小时执行一次
     * @date 2023/4/28 9:57
     */
    @Scheduled(cron = "0 0 */1 * * ?")
    public void test() {
        Thread.currentThread().setName("THREAD-TEST-SCHEDULE");
        logger.warn("test schedule run.......");
    }

    //    字段	        是否必填    允许值	        允许特殊字符	        备注
    //    Seconds	    是	       0–59	            *,-	                标准实现不支持此字段。
    //    Minutes	    是	       0–59	            *,-
    //    Hours	        是	       0–23	            *,-
    //    Day of month	是	       1–31	            *,-?LW	            ?LW只有部分软件实现了
    //    Month	        是	       1–12 or JAN–DEC	*,-
    //    Day of week	是	       0–7 or SUN–SAT	*,-?L#	            ?L#只有部分软件实现了,Linux和Spring的允许值为0-7，0和7为周日,Quartz的允许值为1-7，1为周日
    //    Year	        否	       1970–2099	    *,-	                标准实现不支持此字段。


    //    标准字段
    //    逗号用于分隔列表。例如，在第5个字段(星期几)中使用 MON,WED,FRI 表示周一、周三和周五。
    //    连字符定义范围。例如，2000-2010 表示2000年至2010年期间的每年，包括2000年和2010年。
    //    除非用反斜杠(\)转义，否则命令中的百分号(%)会被替换成换行符，第一个百分号后面的所有数据都会作为标准输入发送给命令。

    /**
     * @author yuanjifan
     * @description 规则
     * @date 2023/4/28 10:00
     * <p>
     * 非标准字段
     * * : 表示匹配该域的任意值。比如Minutes域使用*，就表示每分钟都会触发。
     * <p>
     * “L”代表“Last”。当在星期几字段中使用的时候，可以指定给定月份的结构，
     * 例如“最后一个星期五”(5L)。在月日字段中，可以指定一个月的最后一天。
     * <p>
     * “day of month”字段可以使用“W”字符。指定最接近给定日期的工作日（星期一-星期五）。
     * 例如，15W，意思是：“最接近该月15日的工作日。”；
     * 所以，如果15号是星期六，触发器在14号星期五触发。
     * 如果15日是星期天，触发器在16日星期一触发。
     * 如果15号是星期二，那么它在15号星期二触发。
     * “1W”，如果这个月的第一天是星期六，不会跨到上个月，触发器会在这个月的第三天（也就是星期一）触发。
     * 只有指定一天（不能是范围或列表）的时候，才能指定“W”字符。
     * <p>
     * 星期几字段可以使用“#”，后面必须跟一个介于1和5之间的数字。
     * 例如，5#3表示每个月的第三个星期五。
     * <p>
     * 在某些实现中，“?”用来代替“*”以将月中的某一天或周中的某一天留空。
     * 其他cron的实现是替换“?”为cron守护进程的启动时间，例如：？？* * * *，如果cron在上午8:25启动，将更新为25 8 * * * *并在每天的这个时间运行，直到再次重新启动。
     * <p>
     * 分钟字段设置 *\/5表示每5分钟一次(\是转义符)，注意：这里指的是能被5整除的分钟数。
     */
    @Scheduled(cron = "0 0 */1 * * ?")
    public void test1() {
        Thread.currentThread().setName("THREAD-TEST-SCHEDULE");
        logger.warn("test schedule run.......");
    }

    //    常用cron表达式
    //    */10 * * * * ? 每隔10秒执行一次
    //
    //    0 */5 * * * ? 每隔5分钟执行一次
    //
    //    0 2,22,32 * * * ? 在2分、22分、32分执行一次
    //
    //    0 0 4-8 * * ? 每天4-8点整点执行一次
    //
    //    0 0 2 * * ? 每天凌晨2点执行一次
    //
    //    0 0 2 1 * ? 每月1号凌晨2点执行一次
}
