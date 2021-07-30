package com.antocecere77.redisson.test;

import org.junit.jupiter.api.Test;
import org.redisson.api.RPatternTopicReactive;
import org.redisson.api.RTopicReactive;
import org.redisson.api.listener.PatternMessageListener;
import org.redisson.client.codec.StringCodec;

public class Lec12PubSubTest extends BaseTest {

    @Test
    public void subscribe1() {
        RTopicReactive topic = this.client.getTopic("slack-room", StringCodec.INSTANCE);

        topic.getMessages(String.class)
                .doOnError(System.out::println)
                .doOnNext(System.out::println)
                .subscribe();

        sleep(600000);
    }

    @Test
    public void subscribe2() {
        RTopicReactive topic = this.client.getTopic("slack-room", StringCodec.INSTANCE);

        topic.getMessages(String.class)
                .doOnError(System.out::println)
                .doOnNext(System.out::println)
                .subscribe();

        sleep(600000);
    }
}
