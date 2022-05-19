package org.kyaruchan.mu38getter.url;
import java.io.*;

public class FileUrlProvider implements UrlProvider{
    private Reader reader;
    private String next;
    private boolean isEndOfFile = false;

    public FileUrlProvider(File file){
        if(file == null) throw new IllegalArgumentException("File can not be null");

        if(file.isFile()) {
            try {
                reader = new BufferedReader(new FileReader(file));
            }catch (IOException e){
                throw new RuntimeException("Failed to load file, cause by: " + e.getMessage());
            }
        }else throw new IllegalArgumentException("Failed to load file: " + file.getAbsolutePath());
    }

    @Override
    public String nextUrl() {
        return next;
    }

    @Override
    public boolean hasNext() {
        doNext();
        return !isEndOfFile;
    }

    private void doNext(){
        BufferedReader br = (BufferedReader) reader;
        while(!isEndOfFile){
            try {
                String line = br.readLine();

                /* 文件读取完毕 */
                if(line == null){
                    isEndOfFile = true;
                    try{
                        br.close();
                    }catch(IOException e){
                        throw new RuntimeException("Happening error on closing, cause by " + e.getMessage());
                    }
                    break;
                }

                /* 判断读取内容是否为注释 */
                line = line.trim();
                if(line.startsWith("#")) continue;
                next = line;
                break;
            }catch (IOException e){
                throw new RuntimeException("Happening error on reading, cause by: " + e.getMessage());
            }
        }
    }
}
