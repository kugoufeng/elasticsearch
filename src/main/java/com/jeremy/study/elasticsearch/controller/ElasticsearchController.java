package com.jeremy.study.elasticsearch.controller;

import com.jeremy.study.elasticsearch.crawler.MiguReadCrawler;
import com.jeremy.study.elasticsearch.domain.CustomSearchRequest;
import com.jeremy.study.elasticsearch.service.ElasticsearchCRUD;
import org.elasticsearch.action.search.SearchResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class ElasticsearchController {
    private static Logger logger = LoggerFactory.getLogger(ElasticsearchController.class);

    @Autowired
    ElasticsearchCRUD elasticsearchCRUDImpl;

    SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");

    @RequestMapping("/loadData")
    public String loadData(@RequestParam(required = false) Integer depth)
            throws Exception {
        depth = depth == null || depth <= 0 ? 4 : depth;
        String format = fmt.format(new Date());
        MiguReadCrawler miguReadCrawler =
                new MiguReadCrawler("crawl".concat("\\").concat(format), true, elasticsearchCRUDImpl);
        miguReadCrawler.start(depth);
        return "success";
    }

    @RequestMapping(value="/customSearch", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public String search(@RequestBody CustomSearchRequest customSearchRequest) {
        SearchResponse search = elasticsearchCRUDImpl.search(new String[]{"book"},
                new String[]{"textBook"},
                customSearchRequest.getFrom(), customSearchRequest.getSize(),
                customSearchRequest.getDslBody());
        return search.toString();
    }

}
