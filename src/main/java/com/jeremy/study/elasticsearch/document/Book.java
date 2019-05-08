package com.jeremy.study.elasticsearch.document;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.HashMap;
import java.util.Map;

public class Book implements BaseDoc {
    private static final long serialVersionUID = -759629151992954827L;

    private String bookName;

    private String bookCover;

    private String bookAuthor;

    private String shortDesc;

    private String readUrl;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookCover() {
        return bookCover;
    }

    public void setBookCover(String bookCover) {
        this.bookCover = bookCover;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getReadUrl() {
        return readUrl;
    }

    public void setReadUrl(String readUrl) {
        this.readUrl = readUrl;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                ", bookCover='" + bookCover + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", shortDesc='" + shortDesc + '\'' +
                ", readUrl='" + readUrl + '\'' +
                '}';
    }

    @Override
    public String getElsMapping() {
        Map<String, String> baseMap = new HashMap<>();
        baseMap.put("type", "text");
        baseMap.put("analyzer", "ik_max_word");
        baseMap.put("search_analyzer", "ik_max_word");
        Map<String, Map<String, String>> filedMap = new HashMap<>();
        filedMap.put("bookName", baseMap);
        filedMap.put("bookAuthor", baseMap);
        filedMap.put("shortDesc", baseMap);
        Map<String, Map<String, Map<String, String>>> mapping = new HashMap<>();
        mapping.put("properties", filedMap);
        return JSON.toJSONString(mapping, SerializerFeature.DisableCircularReferenceDetect);
    }
}
