package org.kyaruchan.m3u8getter.downloader;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

public class DownloadProperties {
    public static final String indexFileName;
    public static final String targetFileName;
    public static final int startIndex;
    public static final int endIndex;
    public static final int delay;
    public static final int delayCount;
    public static volatile AtomicBoolean keeping = new AtomicBoolean(true);

    static {
        Properties properties = new Properties();
        try{
            Reader reader = new FileReader("download.properties");
            properties.load(reader);

            indexFileName = properties.getProperty("indexFileName");
            targetFileName = properties.getProperty("targetFileName");
            startIndex = Integer.parseInt(properties.getProperty("startIndex"));
            endIndex = Integer.parseInt(properties.getProperty("endIndex"));
            delay = Integer.parseInt(properties.getProperty("delay"));
            delayCount = Integer.parseInt(properties.getProperty("delayCount"));

            if(startIndex < 0 || endIndex < 0 || delay < 0 || delayCount < 1){
                throw new IllegalArgumentException("Properties has some mistake");
            }
        }catch (IOException e){
            throw new RuntimeException("Failed to load properties: " + e.getMessage());
        }
    }
}
