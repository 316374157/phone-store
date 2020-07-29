package com.phj.project1.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Msg
 * @Description: TODO 通用的返回类，返回json格式的字符串，放一些需要的信息
 * @Author 31637
 * @Date 2020/6/5
 * @Version V1.0
 **/
public class Msg {

    /**
     * 状态码,100表示成功，200表示失败
     */
    private int code;
    /**
     * 提示信息
     */
    private String msg;

    /**
     * 浏览器要返回给用户的数据
     */
    private Map<String,Object> extend = new HashMap<>();

    /**
     * 成功的处理方法
     * @return 返回封装好的处理结果
     */
    public static Msg success(){
        Msg result = new Msg();
        result.setCode(100);
        result.setMsg("处理成功");
        return result;
    }

    /**
     * 失败的处理方法
     * @return 返回封装好的处理结果
     */
    public static Msg fail(){
        Msg result = new Msg();
        result.setCode(200);
        result.setMsg("处理失败");
        return result;
    }

    /**
     * 可以链式操作的添加数据的方法
     * @param key 键
     * @param value 添加的值
     * @return 当前对象
     */
    public Msg add(String key,Object value){
        this.getExtend().put(key, value);
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }
}
