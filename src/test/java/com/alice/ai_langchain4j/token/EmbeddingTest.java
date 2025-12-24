package com.alice.ai_langchain4j.token;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingSearchResult;
import dev.langchain4j.store.embedding.EmbeddingStore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmbeddingTest {
    @Autowired
    private EmbeddingModel embeddingModel;

    @Test
    public void testEmbeddingModel() {
        Response<Embedding> embed = embeddingModel.embed("你好");
        System.out.println("向量维度：" + embed.content().vector().length);
        System.out.println("向量输出：" + embed.toString());
    }
    @Autowired
    private EmbeddingStore embeddingStore;

    /**
     * 将文本转换成向量，然后存储到pinecone中
     *
     * 参考：
     * https://docs.langchain4j.dev/tutorials/embedding-stores
     */
    @Test
    public void testPineconeEmbedded() {

//        TextSegment segment1 = TextSegment.from("我喜欢羽毛球");
//        Embedding embedding1 = embeddingModel.embed(segment1).content();
//        embeddingStore.add(embedding1, segment1);
//
//        TextSegment segment2 = TextSegment.from("今天天气很好");
//        Embedding embedding2 = embeddingModel.embed(segment2).content();
//        embeddingStore.add(embedding2, segment2);
          TextSegment segment3 = TextSegment.from("我喜欢牛肉");
          Embedding embedding3 = embeddingModel.embed(segment3).content();
          embeddingStore.add(embedding3,segment3);
    }
    @Test
    public void embeddingSearch() {
        Embedding queryEmbedding = embeddingModel.embed("你最喜欢的食物是什么").content();
        EmbeddingSearchRequest searchRequest = EmbeddingSearchRequest.builder()
                .queryEmbedding(queryEmbedding)
                .maxResults(1)
                .build();
        EmbeddingSearchResult<TextSegment> searchResult = embeddingStore.search(searchRequest);
        EmbeddingMatch<TextSegment> embeddingMatch = searchResult.matches().get(0);
        //获取匹配项的相似度得分
        System.out.println(embeddingMatch.score()); // 0.8144288515898701

        //返回文本结果
        System.out.println(embeddingMatch.embedded().text());
    }
}
