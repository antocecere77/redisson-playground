package com.antocecere77.redisson.test;

import org.junit.jupiter.api.Test;
import org.redisson.api.RListReactive;
import org.redisson.client.codec.LongCodec;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Lec09ListQueueStackTest extends BaseTest {

    @Test
    public void listTest() {
        //lrange number-input 0 -1
        RListReactive<Long> list = this.client.getList("number-input", LongCodec.INSTANCE);

        List<Long> longList = LongStream.rangeClosed(1, 10)
                .boxed()
                .collect(Collectors.toList());

        StepVerifier.create(list.addAll(longList).then())
                .verifyComplete();

        StepVerifier.create(list.size())
                .expectNext(10)
                .verifyComplete();
    }
}
