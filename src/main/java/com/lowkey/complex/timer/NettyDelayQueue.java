package com.lowkey.complex.timer;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.Timer;
import io.netty.util.TimerTask;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * wheel ：时间轮，图中的圆盘可以看作是钟表的刻度。比如一圈round 长度为24秒，刻度数为 8，那么每一个刻度表示 3秒。那么时间精度就是 3秒。时间长度 / 刻度数值越大，精度越大。
 * 当添加一个定时、延时任务A，假如会延迟25秒后才会执行，可时间轮一圈round 的长度才24秒，那么此时会根据时间轮长度和刻度得到一个圈数 round和对应的指针位置 index，也是就任务A会绕一圈指向0格子上，此时时间轮会记录该任务的round和 index信息。当round=0，index=0 ，指针指向0格子 任务A并不会执行，因为 round=0不满足要求。
 * 所以每一个格子代表的是一些时间，比如1秒和25秒 都会指向0格子上，而任务则放在每个格子对应的链表中，这点和HashMap的数据有些类似。
 * Netty构建延时队列主要用HashedWheelTimer，HashedWheelTimer底层数据结构依然是使用DelayedQueue，只是采用时间轮的算法来实现。
 * 下面我们用Netty 简单实现延时队列，HashedWheelTimer构造函数比较多，解释一下各参数的含义。
 * ThreadFactory ：表示用于生成工作线程，一般采用线程池；
 * tickDuration和unit：每格的时间间隔，默认100ms；
 * ticksPerWheel：一圈下来有几格，默认512，而如果传入数值的不是2的N次方，则会调整为大于等于该参数的一个2的N次方数值，有利于优化hash值的计算。
 *
 * @author yuanjifan
 * @description
 * @date 2022/1/17 15:57
 */
public class NettyDelayQueue {

    public static void main(String[] args) {

        final Timer timer = new HashedWheelTimer(Executors.defaultThreadFactory(), 5, TimeUnit.SECONDS, 2);
        //TimerTask：一个定时任务的实现接口，其中run方法包装了定时任务的逻辑。
        //Timeout：一个定时任务提交到Timer之后返回的句柄，通过这个句柄外部可以取消这个定时任务，并对定时任务的状态进行一些基本的判断。
        //Timer：是HashedWheelTimer实现的父接口，仅定义了如何提交定时任务和如何停止整个定时机制。
        //定时任务,每5s执行一次
        TimerTask task1 = new TimerTask() {
            public void run(Timeout timeout) {
                System.out.println("order1 5s 后执行 ");
                timer.newTimeout(this, 5, TimeUnit.SECONDS);//结束时候再次注册
            }
        };
        timer.newTimeout(task1, 5, TimeUnit.SECONDS);
        TimerTask task2 = new TimerTask() {
            public void run(Timeout timeout) {
                System.out.println("order2 10s 后执行");
                timer.newTimeout(this, 10, TimeUnit.SECONDS);//结束时候再注册
            }
        };

        timer.newTimeout(task2, 10, TimeUnit.SECONDS);

        //延迟任务
        timer.newTimeout(timeout -> System.out.println("order3 15s 后执行一次"), 15, TimeUnit.SECONDS);

    }
}
