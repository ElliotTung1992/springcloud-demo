package com.github.dge1992.logaop.core;

import com.github.dge1992.logaop.utils.SpringContextHolder;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author  dong
 * @create  2018/2/6 14:56
 * @desc 被修改的bean临时存放的地方
 **/
@Component
@Scope(scopeName = WebApplicationContext.SCOPE_SESSION)
public class LogObjectHolder implements Serializable{

    private Object object = null;

    public void set(Object obj){
        this.object = obj;
    }

    public List<String> get() throws Exception {
        Class<?> aClass = object.getClass();
        Field[] fields = object.getClass().getDeclaredFields();
        List<String> list = new ArrayList<>();
        for (Field field : fields) {
            String name = field.getName();
            if ("serialVersionUID".equals(name)) {
                continue;
            }
            PropertyDescriptor pd = null;
            pd = new PropertyDescriptor(name, aClass);

            Method getMethod = pd.getReadMethod();
            Object o1 = getMethod.invoke(object);
            if(o1 == null){
                continue;
            }
            if(o1 instanceof Date){
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String date = format.format((Date) o1);
                list.add(name + "=" + date);
            }else{
                list.add(name + "=" + o1);
            }
        }
        return list;
    }

    public static LogObjectHolder me(){
        LogObjectHolder bean = SpringContextHolder.getBean(LogObjectHolder.class);
        return bean;
    }
}
