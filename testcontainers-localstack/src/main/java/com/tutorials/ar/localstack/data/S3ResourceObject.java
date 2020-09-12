package com.tutorials.ar.localstack.data;

import lombok.Getter;

@Getter
public class S3ResourceObject {

    private String bucket;

    private String key;

    private S3ResourceObject(String bucket, String key) {
        this.bucket = bucket;
        this.key = key;
    }

    public static S3ResourceObject fromUrl(String url) {
        url = url.replace("s3://", "");
        url = url.replace("//", "");
        int indexSeparator = url.indexOf("/");
        return new S3ResourceObject(url.substring(0, indexSeparator), url.substring(indexSeparator + 1));
    }
}
