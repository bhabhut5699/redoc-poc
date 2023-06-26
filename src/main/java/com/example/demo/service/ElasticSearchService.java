package com.example.demo.service;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ElasticSearchService {
    private final RestHighLevelClient elasticsearchClient;
    @Autowired
    public ElasticSearchService(RestHighLevelClient elasticsearchClient) {
        this.elasticsearchClient = elasticsearchClient;
    }

public List<Map<String, Object>> matchAllServices() throws IOException {
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    searchSourceBuilder.query(QueryBuilders.matchAllQuery());

    SearchRequest searchRequest = new SearchRequest();
    searchRequest.source(searchSourceBuilder);

    SearchResponse searchResponse = elasticsearchClient.search(searchRequest, RequestOptions.DEFAULT);

    SearchHits hits = searchResponse.getHits();
    List<Map<String, Object>> searchResults = new ArrayList<>();
    for (SearchHit hit : hits.getHits()) {
        Map<String, Object> sourceAsMap = hit.getSourceAsMap();
        searchResults.add(sourceAsMap);
    }

    return searchResults;
}
    public SearchResponse searchByTermQuery(String fieldName, String termValue) throws IOException {
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery(fieldName, termValue);

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(termQueryBuilder);
        sourceBuilder.size(10); // Set the number of results to retrieve
        sourceBuilder.sort("_score", SortOrder.DESC); // Optional: Sort the results by relevance

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.source(sourceBuilder);

        return elasticsearchClient.search(searchRequest, RequestOptions.DEFAULT);
    }

    public SearchResponse searchByRange(double min, double max) throws IOException {
        RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("price")
                .gte(min)
                .lte(max);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(rangeQuery);
        searchSourceBuilder.sort("price", SortOrder.ASC); // Optional sorting by price

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.source(searchSourceBuilder);

        return elasticsearchClient.search(searchRequest, RequestOptions.DEFAULT);
    }

    public SearchResponse searchWithBoolQuery() throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(QueryBuilders.matchQuery("field1", "value1"));
        boolQuery.mustNot(QueryBuilders.termQuery("field2", "value2"));
        boolQuery.should(QueryBuilders.rangeQuery("field3").gt(10));

        searchSourceBuilder.query(boolQuery);

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.source(searchSourceBuilder);

        return elasticsearchClient.search(searchRequest, RequestOptions.DEFAULT);
    }
}
