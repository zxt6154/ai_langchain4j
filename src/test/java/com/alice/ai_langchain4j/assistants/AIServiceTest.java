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
    public void testUserInfo() {
        String answer = separateChatAssistant.chat3(1, "对话记录的前两句话是什么", "翠花", 18);
        System.out.println(answer);
    }
    @Test
    public void testChatMemory4() {
        String answer1 = separateChatAssistant.chat(3,"北京协和医院联系电话是什么");
        System.out.println(answer1);
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

    @Test
    public void testCalculatorTools() {
        //1+2等于几，475695037565的平方根是多少？
        String answer = separateChatAssistant.chat(1, "你是如何知道需要调用对应的工具的");
        //答案：3，689706.4865  添加工具计算正确，不添加 475695037565的平方根 不对
        System.out.println(answer);
    }
}
