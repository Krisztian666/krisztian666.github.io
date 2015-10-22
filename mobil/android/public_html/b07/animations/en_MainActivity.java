    public class MainActivity extends ListActivity {
    final Activity activity = this;
    private ImageButton button;
    private TextView about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.i("TestTag", "Creating MainActivity...");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (ImageButton)findViewById(R.id.addButton);//{speak:"Find the button"}
        
        button.setOnClickListener(new View.OnClickListener() {//{speak:"Set on click Listener"}
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent();{//{img:"scr-add.png", speak:"Call AddNewActivity"}
                myIntent.setClassName("com.example.todo", "com.example.todo.AddNewActivity");
                startActivity(myIntent);
            }
        });       
        
/* Create About dialog, if the aboutTextView is clicked*/
        about = (TextView)findViewById(R.id.aboutTewtView); //{img:"scr-new-main-img.png", speak:"Find the TextView"}
        about.setOnClickListener(new View.OnClickListener() { //{speak:"Set on click Listener"}
            @Override
            public void onClick(View v) {
                Log.i("TestTag", "Creating About AlertDialog...");
                AlertDialog.Builder builder =new AlertDialog.Builder(activity);//{img:"scr-dialog.png", speak:"Build the dialog"}
                builder.setMessage("This is a demo ToDo application.");
                builder.setTitle("About");
                builder.setNeutralButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i("TestTag", "About AlertDialog Button clicked.");
                    }
                });
                builder.show();
            }
        });        