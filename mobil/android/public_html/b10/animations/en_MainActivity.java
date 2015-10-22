public class MainActivity extends ListActivity {
...
    private TextView about;
    private ImageView pen;
    private AnimationDrawable penAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
...


        Animation pushAnim = AnimationUtils.loadAnimation(//{img:"scr-rotate.gif", speak:"Start tweened animation"}
                getApplicationContext(), R.anim.animation_rotate);
        about.startAnimation(pushAnim);

