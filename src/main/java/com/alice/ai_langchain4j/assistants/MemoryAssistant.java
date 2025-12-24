package com.alice.ai_langchain4j.assistants;

import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

@AiService(wiringMode = AiServiceWiringMode.EXPLICIT, chatModel = "qwenChatModel", chatMemory = "chatMemory")
public interface MemoryAssistant {
    String chat(String userMessage);
}
