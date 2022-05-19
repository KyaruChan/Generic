package org.kyaruchan.m3u8getter.url;

public interface UrlProvider {
    String nextUrl();
    boolean hasNext();
}
