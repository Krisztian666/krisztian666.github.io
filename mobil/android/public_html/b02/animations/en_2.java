public class MainActivity extends Activity {
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView)findViewById(R.id.textViewHelloWord);//{speak:"Find the Hello world! text"}
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//{speak:"Call AddNewActivity"}
/**
 * Calls the AddNewActivity if the user clicks the 'Hello world!' text.
 */             
                Intent myIntent = new Intent();
                myIntent.setClassName("com.example.todo", 
                        "com.example.todo.AddNewActivity");
                startActivity(myIntent);
            }
        });

    }