package com.bantanger.transaction.service;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author chensongmin
 * @description
 * @create 2025/1/2
 */
public class CountDownLatchTest {

    // https://www.baeldung.com/java-countdown-latch

    private List<String> outputScraper = Collections.synchronizedList(new ArrayList<>());
    @Test
    public void CyclicBarrier_test() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7);

        ExecutorService es = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 20; i++) {
            es.execute(() -> {
                try {
                    if (cyclicBarrier.getNumberWaiting() <= 0) {
                        outputScraper.add("Count Updated");
                    }
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    // error handling
                }
            });
        }
        es.shutdown();

        System.out.println(outputScraper.size());
        assertTrue(outputScraper.size() > 7);
    }
}
