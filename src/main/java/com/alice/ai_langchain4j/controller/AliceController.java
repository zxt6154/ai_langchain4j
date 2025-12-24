package com.alice.ai_langchain4j.controller;

import com.alice.ai_langchain4j.assistants.AliceAgent;
import com.alice.ai_langchain4j.assistants.AliceTestAgent;
import com.alice.ai_langchain4j.model.ChatForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Tag(name = "agent 测试")
@RestController
@RequestMapping("/agent_test")
public class AliceController {

    @Autowired
    private AliceTestAgent aliceTestAgent;
    @Autowired
    private AliceAgent aliceAgent;

    @Operation(summary = "对话")
    @PostMapping("/chat")
    public String chat(@RequestBody ChatForm chatForm)  {
        return aliceTestAgent.chat(chatForm.getMemoryId(), chatForm.getMessage());
    }

    @Operation(summary = "对话2")
    @PostMapping("/chat2")
    public Flux<String> chat2(@RequestBody ChatForm chatForm)  {
        return aliceAgent.chat(chatForm.getMemoryId(), chatForm.getMessage());
    }
}