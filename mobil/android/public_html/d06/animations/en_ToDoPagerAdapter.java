public class ToDoPagerAdapter extends PagerAdapter {
    private static final String TAG = "ToDoPagerAdapter";

    private int mode = AddNewActivity.MODE_INSERT_TODO; //{speak:"The default mode is: INSERT"}
    private long toDoId = 0;
...


    public Object instantiateItem(ViewGroup collection, int position){
...
        switch (position) {
            case 0:
...
            case 1:
...
                Log.i(TAG, "instantiateItem details: Mode:" + mode  +" toDoId:" + toDoId);
                if (mode == AddNewActivity.MODE_UPDATE_TODO) {//{speak:"In case of UPDATE mode"}
                    / / In case of UPDATE mode load the saved values
                    ToDo todo =  DataManager.getInstance().getTodo(toDoId);
                    EditText name = (EditText)view.findViewById(R.id.toDoNameEditText);//{speak:"load the saved values"}
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
...

    public int getMode() {//{speak:"Getters and setters"}
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


