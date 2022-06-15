package com.kt.edu.thirdproject.config;

import lombok.Builder;
import lombok.Getter;

@Getter
public enum CacheType {

    ARTISTS("articles", 5 * 60, 10000);

    private String cacheName;
    private int expireAfterWrite;
    private int maximumSize;

    CacheType(String cacheName, int expireAfterWrite, int maximumSize) {
        this.cacheName = cacheName;
        this.expireAfterWrite = expireAfterWrite;
        this.maximumSize = maximumSize;
    }

}
