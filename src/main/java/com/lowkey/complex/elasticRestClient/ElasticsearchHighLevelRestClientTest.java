package com.lowkey.complex.elasticRestClient;

import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ElasticsearchHighLevelRestClientTest {
    private static final Logger logger = LoggerFactory.getLogger(ElasticsearchHighLevelRestClientTest.class);

    /**
     * Get cluster information
     */
    public static void createIndex(String indexName) throws IOException {
        CreateIndexRequest request = new CreateIndexRequest(indexName);
        request.settings(Settings.builder()
                .put("index.number_of_shards", 1)
                .put("index.number_of_replicas", 1)
        );

        request.mapping(
                "{\n" +
                        "  \"properties\": {\n" +
                        "    \"message\": {\n" +
                        "      \"type\": \"text\"\n" +
                        "    }\n" +
                        "  }\n" +
                        "}",
                XContentType.JSON);

        CreateIndexResponse createIndexResponse = ElasticsearchHighLevelRestClient.getInstance().indices().create(request, RequestOptions.DEFAULT);

        if (createIndexResponse.isAcknowledged()) {
            logger.info("Index created successfully");
        }
    }

    /**
     * bulk data to index
     */
    public static void bulkData(String indexName) throws IOException {
        BulkRequest request = new BulkRequest();
        request.add(new IndexRequest("posts").index(indexName)
                .source(XContentType.JSON, "message", "foo"));
        request.add(new IndexRequest("posts").index(indexName)
                .source(XContentType.JSON, "message", "bar"));
        request.add(new IndexRequest("posts").index(indexName)
                .source(XContentType.JSON, "message", "baz"));

        BulkResponse bulkResponse = ElasticsearchHighLevelRestClient.getInstance().bulk(request, RequestOptions.DEFAULT);

        if (bulkResponse.hasFailures()) {
            logger.info("bulk data to index failed:{}", bulkResponse.buildFailureMessage());
        } else {
            logger.info("bulk data to index successfully");
        }
    }

    public static void main(String[] args) throws IOException {
        createIndex("test_index");
        bulkData("test_index");
    }
}
