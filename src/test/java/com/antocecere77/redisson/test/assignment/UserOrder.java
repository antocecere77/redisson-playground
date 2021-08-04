package com.antocecere77.redisson.test.assignment;

import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserOrder {

    private int id;
    private Category category;
}
