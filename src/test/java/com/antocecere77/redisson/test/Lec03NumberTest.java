package com.antocecere77.redisson.test;

import org.junit.jupiter.api.Test;
import org.redisson.api.RAtomicLongReactive;
import org.redisson.api.RBucketReactive;
import org.redisson.client.codec.StringCodec;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Lec03NumberTest extends BaseTest {

    @Test
    public void keyValueIncreaseTest() {

        // set k y -- incr decr
        RAtomicLongReactive atomicLong = this.client.getAtomicLong("user:1:visit");

        Mono<Void> mono = Flux.range(1, 30)
                .delayElements(Duration.ofSeconds(1))
                .flatMap(i -> atomicLong.incrementAndGet())
                .then();

        StepVerifier.create(mono)
                .verifyComplete();
    }
}
