package com.alice.ai_langchain4j.callTools;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import dev.langchain4j.agent.tool.ToolMemoryId;
import org.springframework.stereotype.Component;

@Component
public class CalculatorTools {

    @Tool(name="加法", value = "返回两个参数相加之和")
    double sum( @ToolMemoryId int memoryId,
                @P(value="加数1", required = true) double a,
                @P(value="加数2", required = true) double b) {
        return a + b;
    }

    @Tool(name="平方根", value = "返回给定参数的平方根")
    double squareRoot(double x) {
        return Math.sqrt(x);
    }
}
