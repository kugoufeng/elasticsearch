package com.jeremy.study.elasticsearch.service.impl;

import com.jeremy.study.elasticsearch.service.ElasticsearchCRUD;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ElasticsearchCRUDImpl implements ElasticsearchCRUD {

    @Resource
    TransportClient client;

    @Override
    public IndexResponse create(String index, String type, String id, String jsonData) {
        return client.prepareIndex(index, type, id)
                .setSource(jsonData, XContentType.JSON)
                .get();
    }

    @Override
    public GetResponse get(String index, String type, String id) {
        return client.prepareGet(index, type, id).get();
    }

    @Override
    public UpdateResponse update(String index, String type, String id, String jsonData) {
        return client.prepareUpdate().setDoc(client.prepareIndex(index, type, id)
                .setSource(jsonData, XContentType.JSON)).get();
    }

    @Override
    public DeleteResponse delete(String index, String type, String id) {
        return client.prepareDelete(index, type, id).get();
    }

    @Override
    public SearchResponse search(String[] indices, String[] types, String jsonData) {
        return client.prepareSearch(indices).setTypes(types).setSearchType(SearchType.DFS_QUERY_THEN_FETCH).setQuery(QueryBuilders.wrapperQuery(jsonData)).get();
    }
}
