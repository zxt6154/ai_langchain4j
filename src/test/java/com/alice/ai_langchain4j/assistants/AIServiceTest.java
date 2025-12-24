package com.alice.ai_langchain4j.assistants;

import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.service.AiServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
public class AIServiceTest {
    @Autowired
    private QwenChatModel qwenChatModel;
    @Test
    public void testChat() {
        Assistant assistant = AiServices.create(Assistant.class, qwenChatModel);
        //调用service的接口
        String answer = assistant.chat("Hello");
        System.out.println(answer);
    }

    @Autowired
    private Assistant assistant;

    @Test
    public void testAssistant() {
        String answer = assistant.chat("Hello");
        System.out.println(answer);
    }
    @Autowired
    private SeparateChatAssistant separateChatAssistant;

    @Test
    public void testChatMemory4() {
        String answer1 = separateChatAssistant.chat(1,"我是小红");
        System.out.println(answer1);
        String answer2 = separateChatAssistant.chat(1,"我是谁");
        System.out.println(answer2);
        String answer3 = separateChatAssistant.chat(2,"我是谁");
        System.out.println(answer3);
    }
    @Autowired
    private MemoryAssistant memoryAssistant;
    @Test
    public void testChatMemory3() {
        String answer1 = memoryAssistant.chat("我是小明,我20岁了");
        System.out.println(answer1);
        String answer2 = memoryAssistant.chat("我是谁，我现在多大，青年还是儿童");
        System.out.println(answer2);
    }
    @Test
    public void testChatMemory2() {
        MessageWindowChatMemory messageWindowChatMemory = MessageWindowChatMemory.withMaxMessages(10);
        Assistant assistant2 = AiServices.builder(Assistant.class)
                .chatLanguageModel(qwenChatModel)
                .chatMemory(messageWindowChatMemory)
                .build();

        String answer1 = assistant2.chat("我是小明");
        System.out.println(answer1);
        String answer2 = assistant2.chat("我是谁");
        System.out.println(answer2);
    }
    @Test
    public void testChatMemory() {
        UserMessage userMessage = UserMessage.userMessage("我是小明");
        ChatResponse chatResponse = qwenChatModel.chat(userMessage);
        AiMessage aiMessage = chatResponse.aiMessage();
        System.out.println(aiMessage.text());

        UserMessage userMessage1 = UserMessage.userMessage("我是谁");
        ChatResponse chatResponse1 = qwenChatModel.chat(Arrays.asList(userMessage1, aiMessage, userMessage));
        AiMessage aiMessage1 = chatResponse1.aiMessage();
        System.out.println(aiMessage1.text());
    }
}
