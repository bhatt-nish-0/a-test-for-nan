package com.example.objectmapperexample.objectmapperexample.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class XrtController {

    @GetMapping("/asd")
    public void test() throws JsonProcessingException {
        ObjectMapper objectMapper1 = new ObjectMapper();
        objectMapper1.enable(JsonReadFeature.ALLOW_NON_NUMERIC_NUMBERS.mappedFeature());

        TestNanning valueWithoutNan = objectMapper1.readValue(objectMapper1.writeValueAsString(new TestNanning(1235L)),TestNanning.class);

        TestNanning valueWithNan = objectMapper1.readValue(objectMapper1.writeValueAsString(new TestNanning(Long.MAX_VALUE)),TestNanning.class);

        System.out.println(valueWithNan);
        System.out.println(valueWithoutNan);

        TestNanning val1 = objectMapper1.readValue("{\"value\":\"NaN\"}",TestNanning.class);
        System.out.println(val1);

        long l = 3398L;
        TestNanning val12 = objectMapper1.readValue("{\"value\":" + l + "}",TestNanning.class);

        TestNanning val3 = objectMapper1.readValue("{\"value\":\"NaN\"}",TestNanning.class );

        System.out.println(val12);
        System.out.println("printing val 3!");
        System.out.println(val3);
    }
}

class TestNanning {

    private float value;

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public TestNanning() {

    }

    public TestNanning(float value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "TestNanning{" +
                "value=" + value +
                '}';
    }
}
