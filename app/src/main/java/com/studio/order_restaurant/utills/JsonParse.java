package com.studio.order_restaurant.utills;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.studio.order_restaurant.bean.ShopBean;

import java.lang.reflect.Type;
import java.util.List;

/*
*单例
*/
public class JsonParse {

    //3.定义一个静态的对象
    private static JsonParse instance;

    //1.将构造方法私有化，让别人无法new
    private JsonParse(){}

    //2.提供一个方法获取实例
    public synchronized static JsonParse getInstance(){
        if (instance == null){
            instance = new JsonParse();
        }
        return instance;
    }

    public List<ShopBean> getShopList(String json){
        return new Gson().fromJson(json,new TypeToken<List<ShopBean>>(){}.getType());
    }

}
