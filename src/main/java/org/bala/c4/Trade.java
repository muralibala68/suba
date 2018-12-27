package org.bala.c4;

import lombok.Data;

@Data
public class Trade {
    private final String productId;
    private final long size;
    private final double price;
    private final long timestamp;
}
