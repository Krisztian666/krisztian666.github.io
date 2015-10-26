package com.example.todo;

import android.annotation.TargetApi;
import android.app.Application;
import android.os.Build;
import android.test.ApplicationTestCase;

import com.example.todo.data.ToDo;
import com.example.todo.datahandler.json.ToDoJSONParser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    // Test the JSON parser
    ToDoJSONParser jsonParserToTest;
    InputStream is;

    String json = "[\n" +
            "  {\n" +
            "    \"name\": \"Todo 1 from JSON\",\n" +
            "    \"priority\": \"LOW\",\n" +
            "    \"date\": 1446338090170,\n" +
            "    \"allday\": true,\n" +
            "    \"description\": \"Description 1\",\n" +
            "    \"url\": \"http://android.com\",\n" +
            "    \"contact\": {\n" +
            "      \"name\": \"John Smith\",\n" +
            "      \"phone\": \"0036301234567\",\n" +
            "      \"email\": \"\"\n" +
            "    }\n" +
            "  }\n" +
            "]";

    @TargetApi(Build.VERSION_CODES.KITKAT)
    protected void setUp() throws Exception{
        jsonParserToTest = new ToDoJSONParser();
        is = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testJson() throws Exception{
        ArrayList<ToDo> todos = ToDoJSONParser.ParseJSON(is);
        assertEquals(true, todos.get(0).isAllDay());
    }
}