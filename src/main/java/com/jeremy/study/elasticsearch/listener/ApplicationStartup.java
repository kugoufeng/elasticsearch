package com.jeremy.study.elasticsearch.listener;

import com.jeremy.study.elasticsearch.document.Book;
import com.jeremy.study.elasticsearch.service.ElasticsearchCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.concurrent.ExecutionException;

@Configuration
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    ElasticsearchCRUD elasticsearchCRUDImpl;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try {
            elasticsearchCRUDImpl.indexMapping("book", "textBook", new Book().getElsMapping());
        } catch (ExecutionException e) {
            //索引创建过了，不重复创建
        } catch (InterruptedException e) {
            //索引创建过了，不重复创建
        }
    }
}
