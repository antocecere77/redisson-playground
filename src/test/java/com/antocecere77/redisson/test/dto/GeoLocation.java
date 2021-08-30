package com.antocecere77.redisson.test.dto;

import lombok.*;

@Data
@ToString
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class GeoLocation {

    private double longitude;
    private double latitude;
}
