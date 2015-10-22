package com.example.todo.datahandler.xml;

import android.util.Log;
import com.example.todo.data.Contact;
import com.example.todo.data.ToDo;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * parse todo xml
 */
public class ToDoXmlPullParser {
private static String TAG = "ToDoXmlPullParser";

/**
 * Parse todos from xml stream
 * @param is
 * @return
 * @throws Exception
 */
public static ArrayList<ToDo> ParseXml(InputStream is) throws Exception{

XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
factory.setNamespaceAware(true);
XmlPullParser parser = factory.newPullParser();

parser.setInput(is, null);

ArrayList<ToDo> list = new ArrayList<ToDo>();
ToDo todo = new ToDo();
Contact contact = null;
String text = "";

int eventType = parser.getEventType();
while (eventType != XmlPullParser.END_DOCUMENT) {
    String tagname = parser.getName();
    switch (eventType) {
        case XmlPullParser.START_TAG:
            if (tagname.equalsIgnoreCase("todo")) {
                / / create a new instance of todo
                todo = new ToDo();
            } else if (tagname.equalsIgnoreCase("contact")) {
                contact = new Contact();
            } else if (tagname.equalsIgnoreCase("name")) {
                if (parser.getAttributeCount() > 0) {
                    Log.d(TAG, "    attribute count:" + parser.getAttributeCount());
                    try {
                        String sPriority = parser.getAttributeValue(null, "priority");
                        ToDo.Priority pr = ToDo.Priority.HIGH;
                        if ("LOW".equals(sPriority)) {
                            pr = ToDo.Priority.LOW;
                        } else if ("MED".equals(sPriority)) {
                            pr = ToDo.Priority.MED;
                        }
                        todo.setPriority(pr);
                    } catch (Exception e) {
                        Log.d(TAG, "could not get priority");
                    }
                    try {
                        String sDate = parser.getAttributeValue(null, "date");
                        todo.setDate(Long.parseLong(sDate));
                    } catch (Exception e) {
                        Log.d(TAG, "could not get date");
                    }
                    try {
                        String sAllday = parser.getAttributeValue(null, "allday");
                        todo.setAllDay(Boolean.parseBoolean(sAllday));
                    } catch (Exception e) {
                        Log.d(TAG, "could not get allday");
                    }
                }
            }
            break;

        case XmlPullParser.TEXT:
            text = parser.getText();
            break;


        case XmlPullParser.END_TAG:
            if (tagname.equalsIgnoreCase("todo")) {
                / / add ToDo object to list
                list.add(todo);
            } else if (tagname.equalsIgnoreCase("name")) {
                Log.d(TAG, "name:" + text);
                if (contact == null) {
                    todo.setName(text);
                } else {
                    contact.setContactName(text);
                }
            } else if (tagname.equalsIgnoreCase("date")) {
                Log.d(TAG, "date:" + text);
                todo.setDate(Long.parseLong(text));
            } else if (tagname.equalsIgnoreCase("description")) {
                Log.d(TAG, "description:" + text);
                todo.setDescription(text);
            } else if (tagname.equalsIgnoreCase("url")) {
                Log.d(TAG, "url:" + text);
                todo.setUrl(text);
            } else if (tagname.equalsIgnoreCase("phone")) {
                Log.d(TAG, "phone:" + text);
                contact.setPhoneNumber(text);
            } else if (tagname.equalsIgnoreCase("email")) {
                Log.d(TAG, "email:" + text);
                contact.setEmail(text);
            } else if (tagname.equalsIgnoreCase("contact")) {
                Log.d(TAG, "Contact end found");
                todo.setContact(contact);
                contact = null;
            }
            break;

        default:
            break;
    }
    eventType = parser.next();
}

return list;
}

}
