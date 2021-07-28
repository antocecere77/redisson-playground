package com.antocecere77.redisson.test;

import org.junit.jupiter.api.Test;
import org.redisson.api.RBucketReactive;
import org.redisson.api.RMapReactive;
import org.redisson.client.codec.StringCodec;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.concurrent.TimeUnit;

public class Lec04BucketAsMapTest extends BaseTest {

    @Test
    public void bucketAsMap() {
        // user:1:name
        // user:2:name
        // user:3:name

        Mono<Void> mono = this.client.getBuckets(StringCodec.INSTANCE)
                .get("user:1:name", "user:2:name", "user:3:name")
                .doOnNext(System.out::println)
                .then();

        StepVerifier.create(mono)
                .verifyComplete();
    }

    @Test
    public void retrieveAndSet() {
        RBucketReactive<String> bucket = this.client.getBucket("simpleObject");
        Mono<Void> setMono = bucket.set("This is object value");
        setMono.subscribe(value -> {
            // on invocation completion
        });

        RMapReactive<String, String> map = this.client.getMap("simpleMap");
        Mono<String> putMono = map.put("mapKey", "This is map value");
        putMono.subscribe(value -> {

            System.out.println("previous value: " + value);

        });

        Mono<String> getMono = bucket.get();
        getMono.subscribe(value -> {

            System.out.println("stored object value: " + value);

        });

        Mono<String> getMapMono = map.get("mapKey");
        getMapMono.subscribe(value -> {

            System.out.println("stored map value: " + value);

        });
    }
}
