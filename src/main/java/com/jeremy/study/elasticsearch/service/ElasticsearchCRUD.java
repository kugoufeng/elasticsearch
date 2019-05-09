package com.jeremy.study.elasticsearch.service;

import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;

import java.util.concurrent.ExecutionException;

public interface ElasticsearchCRUD {

    /**
     * 设置索引的settings
     **/
    CreateIndexResponse indexSettings(String index, String settings) throws ExecutionException, InterruptedException;

    /**
     * 设置索引的mapping
     **/
    CreateIndexResponse indexMapping(String index, String type, String mapping) throws ExecutionException, InterruptedException;

    /**
     * 新增数据
     **/
    IndexResponse create(String index, String type, String id, String jsonData);

    /**
     * 查询数据
     */
    GetResponse get(String index, String type, String id);

    /**
     * 更新数据
     */
    UpdateResponse update(String index, String type, String id, String jsonData);

    /**
     * 删除数据
     */
    DeleteResponse delete(String index, String type, String id);

    /**
     * 搜索数据
     */
    SearchResponse search(String[] indices, String[] types, String dslBody);

    /**
     * 搜索数据
     */
    SearchResponse search(String[] indices, String[] types, int from,int size,String dslBody);
}
