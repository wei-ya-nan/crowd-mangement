package com.wyn.crowd.util;

import com.sun.org.apache.regexp.internal.RE;

/**
 * @author wei-ya-nan
 * @version 1.0
 * @date 2022/3/15
 * @param <T>
 *
 */
public class ResultEntity<T> {

    public static final String SUCCESS  = "SUCCESS";

    public static final String FAILED = "FAILED";


    //用来封装当前请求的处理的结果是失败的还是成功的
    private String result;

    //请求处理失败时返回的错误信息
    private String message;

    //要返回的数据
    private  T data;

    /**
     * 请求处理成功的切不需要返回数据
     * @param <Type>
     * @return
     */

    public static <Type> ResultEntity<Type> successWithData(){
        return new ResultEntity<Type>(SUCCESS, null, null);
    }

    /**
     * 请求处理成功但是需要返回数据
     * @param data
     * @param <Type>
     * @return
     */
    public static <Type> ResultEntity<Type> successWithData(Type data){
        return new ResultEntity<Type>(SUCCESS,null,data);

    }

    /**
     * 请求处理失败
     * @param message 失败的错误消息
     * @param <Type>
     * @return
     */
    public static <Type> ResultEntity<Type> failed(String message){
        return  new ResultEntity<Type>(FAILED, message, null);
    }

    public ResultEntity() {
    }

    public ResultEntity(String result, String message, T data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }

    public static String getSUCCESS() {
        return SUCCESS;
    }

    public static String getFAILED() {
        return FAILED;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultEntity{" +
                "result='" + result + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }


}
