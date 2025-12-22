package com.alice.ai_langchain4j;

import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LLMTest {
    @Autowired
    private OpenAiChatModel openAiChatModel;
    @Autowired
    private OllamaChatModel ollamaChatModel;

    @Test
    public void testSpringBoot() {
        //向模型提问
        String answer = openAiChatModel.chat("你好");
        //输出结果
        System.out.println(answer);
    }
    /**
     * gpt-4o-mini语言模型接入测试
     */
    @Test
    public void testGPTDemo() {
   //LangChain4j提供的代理服务器，该代理服务器会将演示密钥替换成真实密钥， 再将请求转发给OpenAI API
        //.baseUrl("http://langchain4j.dev/demo/openai/v1") //设置模型api地址（如果apiKey="demo"，则可省略baseUrl的配置）
        OpenAiChatModel model = OpenAiChatModel.builder()
        .apiKey("demo") //设置模型apiKey
        .modelName("gpt-4o-mini") //设置模型名
        .build();
        //向模型提问
        String answer = model.chat("你好");
        //输出结果
        System.out.println(answer);
    }
    @Test
    public void testOllama() {
        //向模型提问
        String answer = ollamaChatModel.chat("你好");
        //输出结果
        System.out.println(answer);
    }
}
