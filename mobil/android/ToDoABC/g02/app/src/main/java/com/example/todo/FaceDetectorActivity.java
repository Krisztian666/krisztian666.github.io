package com.example.todo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.media.FaceDetector;
import android.media.FaceDetector.Face;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.example.todo.datahandler.DataManager;

import java.io.File;

public class FaceDetectorActivity extends Activity {
    private static String TAG = "FaceDetectorActivity";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
    }

    private class MyView extends View
    {
        private Bitmap myBitmap;
        private FaceDetector.Face[] detectedFaces;
        //maximum number of faces
        private int NUMBER_OF_FACES=5;
        private FaceDetector faceDetector;
        private int NUMBER_OF_FACE_detected;
        private float eyeDistance;

        public MyView(Context context)
        {
            super(context);

            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);

            BitmapFactory.Options bitmapFatoryOptions=new BitmapFactory.Options();
            bitmapFatoryOptions.inPreferredConfig=Bitmap.Config.RGB_565;
            Bitmap bitmapOrg;

            long toDoId = getIntent().getLongExtra("id", -1);
            File img = new File(DataManager.getToDoDir(toDoId) + "image.jpg");
            if(img.exists()) {//load the todo image
                bitmapOrg = BitmapFactory.decodeFile(img.getAbsolutePath(),
                        bitmapFatoryOptions);
            } else {// if image not exists, load Einstein
                bitmapOrg = BitmapFactory.decodeResource(getResources(),
                        R.drawable.einsteink, bitmapFatoryOptions);
            }
            Log.d(TAG,"SCREEN width="+metrics.widthPixels+" height="+metrics.heightPixels);

            myBitmap= resize(bitmapOrg,metrics.widthPixels,metrics.heightPixels);

            int width=myBitmap.getWidth();
            int height=myBitmap.getHeight();
            Log.d(TAG,"IMAGE width="+width+" height="+height);
            detectedFaces=new FaceDetector.Face[NUMBER_OF_FACES];
            faceDetector=new FaceDetector(width,height,NUMBER_OF_FACES);
            NUMBER_OF_FACE_detected =faceDetector.findFaces(myBitmap, detectedFaces);
        }

        /**
         * Resize image
         * @param image
         * @param maxWidth
         * @param maxHeight
         * @return
         */
        private Bitmap resize(Bitmap image, int maxWidth, int maxHeight) {
            if (maxHeight > 0 && maxWidth > 0) {
                int width = image.getWidth();
                int height = image.getHeight();
                float ratioBitmap = (float) width / (float) height;
                float ratioMax = (float) maxWidth / (float) maxHeight;

                int finalWidth = maxWidth;
                int finalHeight = maxHeight;
                if (ratioMax > 1) {
                    finalWidth = (int) ((float)maxHeight * ratioBitmap);
                } else {
                    finalHeight = (int) ((float)maxWidth / ratioBitmap);
                }
                if ( ( finalWidth % 2 ) == 0 ) {
                    //Is even
                } else {
                    //Is odd, but width must be even!
                    finalWidth = finalWidth - 1;
                }
                image = Bitmap.createScaledBitmap(image, finalWidth, finalHeight, true);
                return image;
            } else {
                return image;
            }
        }


        @Override
        protected void onDraw(Canvas canvas)
        {
            canvas.drawBitmap(myBitmap, 0,0, null);
            Paint myPaint = new Paint();
            myPaint.setColor(Color.RED);
            myPaint.setStyle(Paint.Style.STROKE);
            myPaint.setStrokeWidth(5);

            for(int count=0;count< NUMBER_OF_FACE_detected;count++)
            {
                Face face=detectedFaces[count];
                PointF midPoint=new PointF();
                face.getMidPoint(midPoint);

                eyeDistance=face.eyesDistance();
                canvas.drawRect(midPoint.x-eyeDistance,
                        midPoint.y-eyeDistance,
                        midPoint.x+eyeDistance,
                        midPoint.y+eyeDistance,
                        myPaint);
            }
        }

    }

}