package org.kyaruchan.server.service;

public class IntConverter implements Converter<Integer>{
    @Override
    public Integer convert(String original) {
        return Integer.valueOf(original);
    }
}
