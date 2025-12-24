package com.alice.ai_langchain4j.config;

import com.alice.ai_langchain4j.store.MongoChatMemoryStore;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class AliceAgentConfig {
    @Autowired
    private MongoChatMemoryStore mongoChatMemoryStore;
    @Autowired
    private EmbeddingStore embeddingStore;
    @Autowired
    private EmbeddingModel embeddingModel;

    @Bean
    ChatMemoryProvider chatMemoryProviderAlice() {
        return memoryId -> MessageWindowChatMemory.builder()
                .id(memoryId)
                .maxMessages(20)
                .chatMemoryStore(mongoChatMemoryStore).build();
    }
    @Bean
    ContentRetriever contentRetrieverAlice() {
        Document document1 = FileSystemDocumentLoader.loadDocument("/Users/alice/knowledge/医院信息.md");
        Document document2 = FileSystemDocumentLoader.loadDocument("/Users/alice/knowledge/科室信息.md");
        Document document3 = FileSystemDocumentLoader.loadDocument("/Users/alice/knowledge//神经内科.md");
        List<Document> list = Arrays.asList(document1, document2, document3);
        InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();
        EmbeddingStoreIngestor.ingest(list, embeddingStore);

        return EmbeddingStoreContentRetriever.from(embeddingStore);
    }
    @Bean
    ContentRetriever contentRetrieverPinecone() {
        return EmbeddingStoreContentRetriever
                .builder()
                .embeddingModel(embeddingModel)
                .embeddingStore(embeddingStore)
                .maxResults(1)
                .minScore(0.8)
                .build();
    }
}
