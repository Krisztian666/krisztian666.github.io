public class AddNewActivity extends Activity {
    public static final int MODE_INSERT_TODO = 0; //{speak:"Insert new ToDo"}
    public static final int MODE_UPDATE_TODO = 1;  //{speak:"Update existing ToDo"}
...

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager_main);

        long toDoId = getIntent().getLongExtra("id", 0);
        int mode = getIntent().getIntExtra("mode", MODE_INSERT_TODO); //{speak:"Get the MODE, INSERT is the default"}

        / /Pager
        ToDoPagerAdapter tdpAdapter = new ToDoPagerAdapter();
        tdpAdapter.setToDoId(toDoId); //{speak:"Set the ToDo ID"}
        tdpAdapter.setMode(mode); //{speak:"Set the MODE"}
...
    }
...

    /**
     * Add new toDo
     * @param view
     */
    public void saveToDo(View view){
        long toDoId = getIntent().getLongExtra("id", 0);
        int mode = getIntent().getIntExtra("mode", MODE_INSERT_TODO);
        Log.i(TEST_TAG, "Save button pressed. Mode:" + mode + " toDoId:" + toDoId);
...
        Contact contact = new Contact(contactName.getText().toString(),
                contactPhone.getText().toString(),
                "");
        ToDo newToDo = new ToDo(
                name.getText().toString(),
                pPriority,
                calendar.getTimeInMillis(),
                allDay.isChecked(),
                description.getText().toString(),
                url.getText().toString(),
                contact);

        if (mode == MODE_INSERT_TODO) { //{speak:"Add new ToDo"}
            / /Add new ToDo
            DataManager.getInstance().addTodo(newToDo);

        } else if (mode == MODE_UPDATE_TODO) {//{speak:"Update ToDo"}
            / /Update ToDo
            newToDo.setId(toDoId);
            DataManager.getInstance().updateTodo(newToDo);

        }

        / / Close this activity, return to MainActivity
        setResult(RESULT_OK);
        finish();
    }

    
    
