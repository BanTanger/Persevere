# CountDownLatch

https://www.baeldung.com/java-countdown-latch

**通过 CountDownLatch 可以使线程阻塞，直至其他线程完成给定任务**

## 原理
CountDownLatch 内部有个 counter 字段，可以根据需要递减它，因此可以用来阻塞线程，直至它被递减到 0

假设现在我们有个并行操作，可以用想要处理的线程数相同的计数器来实例化 CountDownLatch。
在每个线程处理完自己的任务后再调用 countDown()，而调用 await() 的线程将阻塞到工作线程都完成（即 counter 被减少到 0）


# CyclicBarrier

https://www.baeldung.com/java-cyclic-barrier

CyclicBarrier 内部有 parties 字段，通过 await() 递减它，表示当前线程以达到屏障点

该调用是同步的，调用此方法的线程将阻塞。直至指定数量的线程在 barrier 上调用了相同的方法。


# CountDownLatch vs CyclicBarrier

https://www.baeldung.com/java-cyclicbarrier-countdownlatch

CyclicBarrier 维护线程计数，CountDownLatch 维护任务计数



