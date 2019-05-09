package com.jeremy.study.elasticsearch.domain;

import java.io.Serializable;

public class CustomSearchRequest implements Serializable {

    private static final long serialVersionUID = 6868975727520353507L;
    private int from;
    private int size;
    private String dslBody;

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getDslBody() {
        return dslBody;
    }

    public void setDslBody(String dslBody) {
        this.dslBody = dslBody;
    }

    @Override
    public String toString() {
        return "CustomSearchRequest{" +
                "from=" + from +
                ", size=" + size +
                ", dslBody='" + dslBody + '\'' +
                '}';
    }
}
