public Object instantiateItem(ViewGroup collection, int position){
    View view = null;
    LayoutInflater inflater = (LayoutInflater) collection.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    int pageId = 0;
    switch (position) {
        case 0:
...
            break;
        case 1:
            pageId = R.layout.activity_add_new;
            view = inflater.inflate(pageId, null);

            final Calendar today = Calendar.getInstance();
            DatePicker date = (DatePicker)view.findViewById(R.id.date);
            date.setMinDate(today.getTimeInMillis() - 100);


            SharedPreferences sharedPref = //{speak:"Read from SharedPreferences"}
                view.getContext().getSharedPreferences(
                            ConfigureActivity.PREF_FILE, 
                            Context.MODE_PRIVATE);
            boolean defaultValue = true;
            boolean bh24 = sharedPref.getBoolean(
                            ConfigureActivity.PREF_H24, 
                            defaultValue);

            TimePicker timePicker = (TimePicker)view.findViewById(R.id.time);
            timePicker.setIs24HourView(bh24);//{img:"scr-addnew2.png", speak:"Set TimePicker format"}

            break;
        case 2:
            pageId = R.layout.view_pager_right;
            view = inflater.inflate(pageId, null);

            break;
    }

    ((ViewPager) collection).addView(view, 0);

    return view;
}
