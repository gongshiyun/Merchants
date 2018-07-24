package com.gsyun.passbook.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gongshiyun
 * @Description 通用响应对象
 * @date 2018/7/25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    /**
     * 错误码,正确返回0
     */
    private Integer errorCode = 0;

    /**
     * 错误信息,正确返回空字符串
     */
    private String errorMsg = "";

    /**
     * 返回值对象
     */
    private Object data;

    /**
     * 正确的响应构造函数
     * @param data 返回值对象
     */
    public Response(Object data) {
        this.data = data;
    }
}
