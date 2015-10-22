public class AddNewActivity extends Activity {
    private DatePicker date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);

        final Calendar today = Calendar.getInstance(); //{speak:"get today"}

        date = (DatePicker)findViewById(R.id.date); //{speak:"Find the DatePicker"}
        date.setMinDate(today.getTimeInMillis()-10000);//{img:"scr-add-validdate.png", speak:"Sets the minimal date in ms."}

    }
    
    
    