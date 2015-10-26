package com.example.todo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.todo.data.ToDo;
import com.example.todo.datahandler.DataManager;

import java.util.Calendar;

/**
 * todo pager
 */
public class ToDoPagerAdapter extends PagerAdapter {
    private static final String TAG = "ToDoPagerAdapter";

    private int mode = AddNewActivity.MODE_INSERT_TODO;
    private long toDoId = 0;

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return 3;
    }

    /**
     * Determines whether a page View is associated with a specific key object
     * as returned by {@link #instantiateItem(ViewGroup, int)}. This method is
     * required for a PagerAdapter to function properly.
     *
     * @param view   Page View to check for association with <code>object</code>
     * @param object Object to check for association with <code>view</code>
     * @return true if <code>view</code> is associated with the key object <code>object</code>
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((View) object);
    }

    public Object instantiateItem(ViewGroup collection, int position){
        View view = null;
        LayoutInflater inflater = (LayoutInflater) collection.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int pageId = 0;
        switch (position) {
            case 0:
                pageId = R.layout.view_pager_left;
                view = inflater.inflate(pageId, null);

                break;
            case 1:
                pageId = R.layout.activity_add_new;
                view = inflater.inflate(pageId, null);

                final Calendar today = Calendar.getInstance();
                DatePicker date = (DatePicker)view.findViewById(R.id.date);
                date.setMinDate(today.getTimeInMillis() - 1000);


                SharedPreferences sharedPref = view.getContext().getSharedPreferences(ConfigureActivity.PREF_FILE, Context.MODE_PRIVATE);
                boolean defaultValue = true;
                boolean bh24 = sharedPref.getBoolean(ConfigureActivity.PREF_H24, defaultValue);

                TimePicker timePicker = (TimePicker)view.findViewById(R.id.time);
                timePicker.setIs24HourView(bh24);

                Log.i(TAG, "instantiateItem details: Mode:" + mode  +" toDoId:" + toDoId);
                if (mode == AddNewActivity.MODE_UPDATE_TODO) {
                    // set the saved todo
                    ToDo todo =  DataManager.getInstance().getTodo(toDoId);
                    EditText name = (EditText)view.findViewById(R.id.toDoNameEditText);
                    name.setText(todo.getName());

                    RadioGroup priority = (RadioGroup)view.findViewById(R.id.priorityRadioGroup);
                    if (ToDo.Priority.LOW.equals(todo.getPriority())){
                        priority.check(R.id.pLowRadio);
                    } else if (ToDo.Priority.MED.equals(todo.getPriority())){
                        priority.check(R.id.pMedRadio);
                    } else if (ToDo.Priority.HIGH.equals(todo.getPriority())){
                        priority.check(R.id.pHighRadio);
                    }

                    CheckBox alladay = (CheckBox)view.findViewById(R.id.allDayCheckBox);
                    alladay.setChecked(todo.isAllDay());

                    EditText description = (EditText)view.findViewById(R.id.descriptionEditText);
                    description.setText(todo.getDescription());

                    EditText url = (EditText)view.findViewById(R.id.urlEditText);
                    url.setText(todo.getUrl());

                    TextView contactName = (TextView)view.findViewById(R.id.contact_name);
                    contactName.setText(todo.getContact().getContactName());

                    TextView contactPhone = (TextView) view.findViewById(R.id.contact_phone);
                    contactPhone.setText(todo.getContact().getPhoneNumber());
                }

                break;
            case 2:
                pageId = R.layout.view_pager_right;
                view = inflater.inflate(pageId, null);

                break;
        }

        ((ViewPager) collection).addView(view, 0);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup viewPager, int position, Object currentView) {
        ((ViewPager) viewPager).removeView((View) currentView);
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public long getToDoId() {
        return toDoId;
    }

    public void setToDoId(long toDoId) {
        this.toDoId = toDoId;
    }
}
