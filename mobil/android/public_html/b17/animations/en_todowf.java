package com.example.todo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.service.wallpaper.WallpaperService;
import android.text.format.DateFormat;
import android.view.SurfaceHolder;

/**
 * ToDoWallpaper
 */
public class ToDoWallpaper extends WallpaperService{
    /**
     * Must be implemented to return a new instance of the wallpaper's engine.
     * Note that multiple instances may be active at the same time, such as
     * when the wallpaper is currently set as the active wallpaper and the user
     * is in the wallpaper picker viewing a preview of it as well.
     */
    @Override
    public Engine onCreateEngine() {
        return new ToDoEngine();
    }

    @Override
    public void onCreate(){
        super.onCreate();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }




    class ToDoEngine extends WallpaperService.Engine { //{speak:"Create the Engine."}

        private boolean mVisible = false;
        private final Handler mHandler = new Handler();
        private final Runnable mUpdateDisplay = new Runnable() {
            @Override
            public void run() {
                draw();
            }
        };

        private void draw() { { //{speak:"Create the Content."}
            SurfaceHolder holder = getSurfaceHolder();
            Canvas c = null;
            try {
                c = holder.lockCanvas();
                if (c != null) {
                    Paint p = new Paint();
                    p.setTextSize(20);
                    p.setAntiAlias(true);
                    String text = getResources().getString(R.string.todo) + ". "
                            + getResources().getString(R.string.time) + ": "
                            + DateFormat.format("hh:mm:ss", System.currentTimeMillis());
                    float w = p.measureText(text, 0, text.length());
                    int offset = (int) w / 2;
                    int x = c.getWidth()/2 - offset;
                    int y = c.getHeight()/2;
                    p.setColor(Color.BLACK);
                    c.drawRect(0, 0, c.getWidth(), c.getHeight(), p);
                    p.setColor(Color.WHITE);
                    c.drawText(text, x, y, p);
                }
            } finally {
                if (c != null)
                    holder.unlockCanvasAndPost(c);
            }
            mHandler.removeCallbacks(mUpdateDisplay);
            if (mVisible) {
                mHandler.postDelayed(mUpdateDisplay, 1000);{ //{speak:"Delay 1s before refresh"}
            }
        }

        @Override
        public void onVisibilityChanged(boolean visible) {
            mVisible = visible;
            if (visible) {
                draw();
            } else {
                mHandler.removeCallbacks(mUpdateDisplay);
            }
        }

        @Override
        public void onSurfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            draw();
        }

        @Override
        public void onSurfaceDestroyed(SurfaceHolder holder) {
            super.onSurfaceDestroyed(holder);
            mVisible = false;
            mHandler.removeCallbacks(mUpdateDisplay);
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            mVisible = false;
            mHandler.removeCallbacks(mUpdateDisplay);
        }
    }



}