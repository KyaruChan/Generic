package org.kyaruchan.server.service;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class FileContentHolder implements ContentHolder{
    @Override
    public boolean save(String name, String content) {
        File file = new File(name + new Date().toString());
        try(FileWriter fw = new FileWriter(file)){
            fw.write(content);
        }catch (IOException e){
            System.out.println("[Server] --> 文件写入异常");
            return false;
        }

        return true;
    }
}
