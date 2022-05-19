package org.kyaruchan.m3u8getter.downloader;

public interface DownloaderObserver {
    void sendDownloaded(int num, boolean isEnd);
}
