package org.kyaruchan.server.controller;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* 注册本地服务，接受Consumer请求并调用服务 */
public class ServerCenter {
    private Map<String, Object> localServices = new HashMap<>(); // 注册的本地服务
    private Map<String, List<String>> deniedMethods = new HashMap<>(); // 不允许调用的方法
    private Map<String, List<String>> allowedMethods = new HashMap<>(); // 允许调用的方法
    private boolean aliveFlag = true;

    public ServerCenter(){
        registerServiceWithAllowed("server-center", this, new String[]{"stopServer"});
    }

    /**
     * 启动服务中心，监听来自指定端口的请求
     * @param port 监听的端口
     */
    public void active(int port){
        final ExecutorService pool = Executors.newFixedThreadPool(10);

        try{
            final ServerSocket server = new ServerSocket(port);

            while(true){
                Socket client = server.accept();
                pool.execute(new ServerCommand(client));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    // 注册服务，同时指定不能访问的方法名
    public ServerCenter registerServiceWithDenied(String id, Object service, String[] deniedMethods){
        if(deniedMethods != null){
            this.deniedMethods.put(id, Arrays.asList(deniedMethods));
        }
        return registerService(id, service);
    }

    // 注册服务，同时指定可以访问的方法名
    public ServerCenter registerServiceWithAllowed(String id, Object service, String[] allowedMethods){
        if(allowedMethods != null){
            this.allowedMethods.put(id, Arrays.asList(allowedMethods));
        }
        return registerService(id, service);
    }

    // 注册服务，不指定任何方法名
    public ServerCenter registerService(String id, Object service){
        assert service != null : "service does not allowed to null.";
        assert localServices.get(id) == null : "service was exists.";
        localServices.put(id, service);
        return this;
    }

    public void stopServer(){
        aliveFlag = false;
    }

    private class ServerCommand implements Runnable{
        private Socket client;

        public ServerCommand(Socket client){
            this.client = client;
        }

        @Override
        public void run() {
            try {
                // 解析出Consumer传递的参数
                final RequestResolver requestResolver = new ObjectRequestResolver();
                RequestParam param = requestResolver.resolve(client.getInputStream());

            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
