package com.alice.ai_langchain4j.assistants;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;
import reactor.core.publisher.Flux;

//embeddingModel    qwenChatModel
@AiService(wiringMode = AiServiceWiringMode.EXPLICIT
        ,streamingChatModel = "qwenStreamingChatModel"
        ,chatMemoryProvider = "chatMemoryProviderAlice"
        ,tools = "appointmentTools"
        ,contentRetriever = "chatMemoryProviderPinecone")
public interface AliceAgent {
    @SystemMessage(fromResource = "alice-prompt-template.txt")
    Flux<String> chat(@MemoryId Long memoryId, @UserMessage String userMessage);
}
