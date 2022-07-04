package org.kyaruchan.server.service;

public interface Converter<T> {
    T convert(String original);
}
