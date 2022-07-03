package org.kyaruchan.server.controller;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

/* 解析以序列化方式传递参数的请求 */
public class ObjectRequestResolver implements RequestResolver{
    @Override
    public RequestParam resolve(InputStream input) {
        try{
            ObjectInputStream oin = new ObjectInputStream(input);
            String id = oin.readUTF();
            String methodName = oin.readUTF();
            Object[] params = (Object[]) oin.readObject();
            return new RequestParam(id, methodName, params);
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }
}
