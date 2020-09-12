package com.tutorials.ar.localstack.data;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ResourceDetails<T> {

    private String path;

    private T resource;
}