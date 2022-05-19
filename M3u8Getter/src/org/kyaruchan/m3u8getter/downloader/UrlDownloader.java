package org.kyaruchan.m3u8getter.downloader;
import org.kyaruchan.m3u8getter.url.UrlProvider;
import java.io.*;
import java.net.URL;

public class UrlDownloader {
    private UrlProvider provider;
    private File target;
    private DownloaderObserver observer;

    public UrlDownloader(UrlProvider provider, DownloaderObserver observer){
        if(provider == null) throw new IllegalArgumentException("Provider can not be null");
        if(observer == null) this.observer = (num, isEnd) -> {};
        else this.observer = observer;
        this.provider = provider;

        target = new File(DownloadProperties.targetFileName);
        if(target.exists()){
            throw new RuntimeException("File exists");
        }
    }

    public void download(){
        doDownload();
    }

    private void doDownload(){
        byte[] buffer = new byte[4096];
        int currentIndex = 1; //当前下载的url索引
        int currentCount = 0; //据上一次暂停后的下载数量

        try {
            /* 创建文件流 */
            OutputStream outStream = new FileOutputStream(target);

            /* 下载所有url中的视频到一个文件 */
            while (provider.hasNext()) {
                /* 边界检测 */
                if(currentIndex < DownloadProperties.startIndex) {
                    ++currentIndex;
                    continue;
                }
                if(currentIndex >= DownloadProperties.endIndex) break;

                /* 是否保活 */
                if(!DownloadProperties.keeping.get()) break;

                /* 下载url中的文件 */
                String location = provider.nextUrl();
                URL url = new URL(location);
                BufferedInputStream bin = new BufferedInputStream(url.openStream());

                int valid = 0;
                while((valid = bin.read(buffer)) != -1){
                    outStream.write(buffer, 0, valid);
                }

                bin.close();
                observer.sendDownloaded(currentIndex, false);
                ++currentIndex;
                ++currentCount;

                /* 延迟 */
                if(currentCount >= DownloadProperties.delayCount && DownloadProperties.delay > 0){
                    System.out.println("On hold");
                    currentCount = 0;
                    Thread.sleep(DownloadProperties.delay * 1000L);
                }
            }

            /* 关闭流 */
            outStream.close();
            observer.sendDownloaded(currentIndex, true);
        }catch (IOException |InterruptedException e){
            throw new RuntimeException("Failed to download, cause by " + e.getMessage());
        }
    }
}
