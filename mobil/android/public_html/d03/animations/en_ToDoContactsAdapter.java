package com.example.todo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.todo.data.Contact;

import java.util.ArrayList;

/**
 * Handle the contacts
 */
public class ToDoContactsAdapter extends BaseAdapter {
private static String TEST_TAG = "ToDoContacts";
/ /private Context context;
private ArrayList<Contact> contactsList = new ArrayList<Contact>();


public ToDoContactsAdapter(final Context pContext, 
        ArrayList<Contact> pContactsList) {
    / /this.context = pContext;
    this.contactsList = pContactsList;
}

/**
 * How many items are in the data set represented by this Adapter.
 *
 * @return Count of items.
 */
@Override
public int getCount() {
    Log.d(TEST_TAG, "getCount " + contactsList.size());
    return contactsList.size();
}

/**
 * Get the data item associated with the specified 
 * position in the data set.
 *
 * @param position Position of the item whose data we want 
 * within the adapter's data set.
 * @return The data at the specified position.
 */
@Override
public Object getItem(int position) {
    Log.d(TEST_TAG, "getItem " + position +" "+ contactsList.get(position));
    return contactsList.get(position);
}

/**
 * Get the row id associated with the specified position in the list.
 *
 * @param position The position of the item within the 
 * adapter's data set whose row id we want.
 * @return The id of the item at the specified position.
 */
@Override
public long getItemId(int position) {
    Log.d(TEST_TAG, "getItemId " + position);
    return position;
}

/**
 * Get a View that displays the data at the specified position in the data set. You can either
 * create a View manually or inflate it from an XML layout file. When the View is inflated, the
 * parent View (GridView, ListView...) will apply default layout parameters unless you use
 * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
 * to specify a root view and to prevent attachment to the root.
 *
 * @param position    The position of the item within the adapter's data set of the item whose view
 *                    we want.
 * @param convertView The old view to reuse, if possible. Note: You should check that this view
 *                    is non-null and of an appropriate type before using. If it is not possible to convert
 *                    this view to display the correct data, this method can create a new view.
 *                    Heterogeneous lists can specify their number of view types, so that this View is
 *                    always of the right type (see {@link #getViewTypeCount()} and
 *                    {@link #getItemViewType(int)}).
 * @param parent      The parent that this view will eventually be attached to
 * @return A View corresponding to the data at the specified position.
 */
@Override
public View getView(int position, View convertView, ViewGroup parent) {
    Log.d(TEST_TAG, "getView " + position);
    LayoutInflater inflater = (LayoutInflater) parent.getContext()
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View itemView = inflater.inflate(R.layout.todo_contacts_row, null);

    Contact contact = contactsList.get(position);
    TextView textViewName = 
            (TextView) itemView.findViewById(R.id.contactTextView);
    textViewName.setText(//{speak:"Show the contact"}
            contact.getContactName() + "\n" + contact.getPhoneNumber());

    return itemView;
}
}
