package com.antocecere77.redisson.test;

import com.antocecere77.redisson.test.dto.Restaurant;
import com.antocecere77.redisson.test.util.RestaurantUtil;
import org.junit.jupiter.api.Test;
import org.redisson.api.GeoUnit;
import org.redisson.api.RGeoReactive;
import org.redisson.api.geo.GeoSearchArgs;
import org.redisson.codec.TypedJsonJacksonCodec;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.function.Function;

public class Lec17GEoSpatialTest extends BaseTest {

    @Test
    public void add() {

        RGeoReactive<Restaurant> geo = this.client.getGeo("restaurants", new TypedJsonJacksonCodec(Restaurant.class));
        Mono<Void> mono = Flux.fromIterable(RestaurantUtil.getRestaurants())
                .flatMap(r -> geo.add(r.getLongitude(), r.getLatitude(), r))
                .then();

        StepVerifier.create(mono)
                .verifyComplete();

/*        "latitude": 32.78136,
        "longitude": -96.80539,*/

        GeoSearchArgs radius = GeoSearchArgs.from(-96.80539, 32.78136)
                .radius(3, GeoUnit.MILES);

        geo.search(radius)
            .flatMapIterable(Function.identity())
            .doOnNext(System.out::println)
            .subscribe();
    }
}
