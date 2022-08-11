package cn.kangyifan.parser;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class DemoDataParserTest {

    static DemoDataParser demoDataParser;

    @BeforeAll
    static void beforeAll() {
        demoDataParser = new DemoDataParser();
    }

    @Test
    void readAll() {
        demoDataParser.readAll();
    }

    @Test
    void readFirst() {
        demoDataParser.readFirst();
    }
}