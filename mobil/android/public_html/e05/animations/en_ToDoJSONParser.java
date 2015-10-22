package com.example.todo.datahandler.json;

import android.util.JsonReader;
import com.example.todo.data.Contact;
import com.example.todo.data.ToDo;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Parse JSON
 */
public class ToDoJSONParser {

/**
 * Parse ToDos from JSON stream
 * @param is
 * @return
 * @throws Exception
 */
public static ArrayList<ToDo> ParseJSON(InputStream is) throws Exception{
    JsonReader reader = new JsonReader(new InputStreamReader(is, "UTF-8"));
    try {
        return readToDosArray(reader);
    } finally {
        reader.close();
    }
}

private static ArrayList<ToDo> readToDosArray(JsonReader reader) throws IOException {
    ArrayList<ToDo> list = new ArrayList<ToDo>();

    reader.beginArray();
    while (reader.hasNext()) {
        list.add(readToDo(reader));
    }
    reader.endArray();
    return list;
}

private static ToDo readToDo(JsonReader reader) throws IOException {
    ToDo todo = new ToDo();

    reader.beginObject();
    while (reader.hasNext()) {
        String name = reader.nextName();
        if (name.equals("name")) {
            todo.setName(reader.nextString());
        } else if (name.equals("priority")) {
            String sPriority = reader.nextString();
            ToDo.Priority pr = ToDo.Priority.HIGH;
            if ("LOW".equals(sPriority)) {
                pr = ToDo.Priority.LOW;
            } else if ("MED".equals(sPriority)) {
                pr = ToDo.Priority.MED;
            }
            todo.setPriority(pr);
        } else if (name.equals("date")) {
            todo.setDate(reader.nextLong());
        } else if (name.equals("allday")) {
            todo.setAllDay(reader.nextBoolean());
        } else if (name.equals("description")) {
            todo.setDescription(reader.nextString());
        } else if (name.equals("url")) {
            todo.setUrl(reader.nextString());
        } else if (name.equals("contact")) {
            todo.setContact(readContact(reader));
        } else {
            reader.skipValue();
        }
    }
    reader.endObject();
    return todo;
}

private static Contact readContact(JsonReader reader) throws IOException {
    Contact contact = new Contact();

    reader.beginObject();
    while (reader.hasNext()) {
        String name = reader.nextName();
        if (name.equals("name")) {
            contact.setContactName(reader.nextString());
        } else if (name.equals("phone")) {
            contact.setPhoneNumber(reader.nextString());
        } else if (name.equals("email")) {
            contact.setEmail(reader.nextString());
        } else {
            reader.skipValue();
        }
    }
    reader.endObject();

    return contact;
}

}
