package com.example.todo;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ScrollView;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * todo pager
 */
public class ToDoPagerAdapter extends PagerAdapter {
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
                date.setMinDate(today.getTimeInMillis() - 100);

                TimePicker timePicker = (TimePicker)view.findViewById(R.id.time);
                timePicker.setIs24HourView(true);

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
}
