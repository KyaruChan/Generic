package org.kyaruchan.server.controller;
import java.io.InputStream;

public interface RequestResolver {
    RequestParam resolve(InputStream input);
}
