...
    private void showAboutDialog(){//{speak:"Send Notification if dialog is called"}
        Log.i("TestTag", "Creating About AlertDialog...");
        AlertDialog.Builder builder =new AlertDialog.Builder(activity);
        builder.setMessage(R.string.about_txt);
        builder.setTitle(R.string.about);
        builder.setNeutralButton(R.string.close, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i("TestTag", "About AlertDialog Button clicked.");
            }
        });
        builder.show();
        createNotification(1, //{img:"scr-main-not1.png", speak:"Call createNotification"}
            getResources().getString(R.string.about), 
            getResources().getString(R.string.about_called));
    }

    /**
     * Send notification
     * @param pNotificationId
     * @param pTitle
     * @param pText
     */
    private void createNotification(int pNotificationId, 
                            String pTitle, String pText) {
        NotificationCompat.Builder builder = 
            new Builder(getApplicationContext());//{speak:"Build Notification"}
        builder.setSmallIcon(R.drawable.todoicon);//{img:"scr-main-not2.png", speak:"Set Icon"}
        builder.setContentTitle(pTitle);//{speak:"Set Title"}
        builder.setContentText(pText);//{speak:"Set Content"}
        Notification notification = builder.build();

        NotificationManager notificationManager = 
            (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(pNotificationId, notification);//{speak:"Send Notification"}

    }

...
