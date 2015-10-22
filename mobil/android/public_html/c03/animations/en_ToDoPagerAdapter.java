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
                date.setMinDate(today.getTimeInMillis() - 10000);

                TimePicker timePicker = (TimePicker)view.findViewById(R.id.time);//{speak:"Set TimePicker"}
                timePicker.setIs24HourView(true);

                break;
 ...