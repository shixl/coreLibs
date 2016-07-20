package study.com.s_sxl.carelib.netUtils;

import java.util.HashMap;

public class ApiFactory {

    private static ApiFactory factory;
    private static HashMap<String,Object> serviceMap = new HashMap<>();

    public static ApiFactory getFactory(){
        if(factory == null){
            synchronized (ApiFactory.class){
                if(factory == null){
                    factory = new ApiFactory();
                }
            }
        }

        return  factory;
    }

    public <T> T create(Class<T> clz){
        Object service = serviceMap.get(clz.getName());
        if(service == null){
            service = RetrofitFactory.getRetrofit().create(clz);
            serviceMap.put(clz.getName(),service);
        }

        return (T) service;
    }
}
