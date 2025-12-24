package com.alice.ai_langchain4j.assistants;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;
//embeddingModel    qwenChatModel
@AiService(wiringMode = AiServiceWiringMode.EXPLICIT
        ,chatModel = "qwenChatModel"
        ,chatMemoryProvider = "chatMemoryProviderAlice"
        ,tools = "appointmentTools"
        , contentRetriever = "contentRetrieverAlice")
public interface AliceTestAgent {
    @SystemMessage(fromResource = "alice-prompt-template.txt")
    String chat(@MemoryId Long memoryId, @UserMessage String userMessage);
}
