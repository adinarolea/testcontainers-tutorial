package com.tutorials.ar.localstack.data;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UploadResourceRequest {

    private Object resource;

    private String path;
}
