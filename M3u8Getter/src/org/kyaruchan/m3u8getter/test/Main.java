package org.kyaruchan.m3u8getter.test;
import org.kyaruchan.m3u8getter.downloader.DownloadProperties;
import org.kyaruchan.m3u8getter.downloader.UrlDownloader;
import org.kyaruchan.m3u8getter.url.FileUrlProvider;
import org.kyaruchan.m3u8getter.url.UrlProvider;
import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if(args.length == 0){
            new Thread(() -> {
                UrlProvider fileUrlProvider = new FileUrlProvider(new File(DownloadProperties.indexFileName));
                UrlDownloader downloader =
                        new UrlDownloader(fileUrlProvider,  (num, isEnd) -> {
                            if(isEnd) {
                                System.out.println("downloading completed: "
                                        + (num - DownloadProperties.startIndex));
                                return;
                            }

                            System.out.println(num + " downloaded");
                        });
                System.out.println("Downloading is on starting");
                downloader.download();
            }).start();

            /* 扫描用户输入 */
            while(true) {
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();
                if (input.equals("stop")) {
                    DownloadProperties.keeping.set(false);
                    break;
                }else{
                    System.out.println("Wrong input!");
                }
            }
        }
        else if(args.length == 1){
            if(args[0].equals("test")){
                int count = 0;
                UrlProvider fileUrlProvider = new FileUrlProvider(new File(DownloadProperties.indexFileName));
                while(fileUrlProvider.hasNext()) ++count;
                System.out.println("url has " + count);
            }
        }else if(args.length > 1){
            throw new IllegalArgumentException("Too many arguments");
        }
    }
}
