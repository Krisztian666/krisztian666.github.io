<menu xmlns:android="http:/ /schemas.android.com/apk/res/android"
    xmlns:tools="http:/ /schemas.android.com/tools" 
    tools:context=".MainActivity">

    <item android:id="@+id/action_addnew" android:title="Add New ToDo" />//{img:"scr-main-menu1.png", speak:"Add menu items."}
    <item android:id="@+id/action_settings" android:title="Settings" >
        <menu>//{img:"scr-main-menu2.png", speak:"Add submenu."}
            <item android:id="@+id/action_settings_display" 
                  android:title="Display" />
            <item android:id="@+id/action_settings_system" 
                  android:title="System" />
        </menu>
    </item>
    <item android:id="@+id/action_about" android:title="About" />

</menu>
