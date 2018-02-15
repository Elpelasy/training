package com.mkyong.entity;

import java.util.List;

public class AjaxResponseBody {

    String msg;
    List<Stock> result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Stock> getResult() {
        return result;
    }

    public void setResult(List<Stock> result) {
        this.result = result;
    }

}
