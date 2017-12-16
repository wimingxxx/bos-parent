package com.qwm.bos.web.action.base;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author: qiwenming(杞文明)
 * @date: 17/12/11 下午10:11
 * @className: BaseAction
 * @description:
 *  表现层通用实现
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T>{
    public static final String HOME = "home";
    public static final String LIST = "list";
    //模型对象那个
    protected  T model;
    @Override
    public T getModel() {
        return model;
    }

    //在构造方法中动态获取实体类型，通过反射创建model对象
    public BaseAction() {
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        //获得BaseAction上声明的泛型数组
        Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
        Class<T> entityClass = (Class<T>) actualTypeArguments[0];
        //通过反射创建对象
        try {
            model = entityClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
