package com.jeremy.study.elasticsearch.service.impl;

import com.jeremy.study.elasticsearch.service.ElasticsearchCRUD;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class ElasticsearchCRUDImpl implements ElasticsearchCRUD {

    @Autowired
    TransportClient client;


    @Override
    public CreateIndexResponse indexSettings(String index, String settings) throws ExecutionException, InterruptedException {
        CreateIndexRequest request = new CreateIndexRequest(index);
        request.settings(settings, XContentType.JSON);
        return client.admin().indices().create(request).get();
    }

    @Override
    public CreateIndexResponse indexMapping(String index, String type, String mapping) throws ExecutionException, InterruptedException {
        CreateIndexRequest request = new CreateIndexRequest(index);
        request.mapping(type, mapping, XContentType.JSON);
        return client.admin().indices().create(request).get();
    }

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
    public SearchResponse search(String[] indices, String[] types, String dslBody) {
        return search(indices, types, 0, 10, dslBody);
    }

    @Override
    public SearchResponse search(String[] indices, String[] types, int from, int size, String dslBody) {
        return client.prepareSearch(indices).setTypes(types)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.wrapperQuery(dslBody))
                .setFrom(from)
                .setSize(size)
                .get();
    }
}
