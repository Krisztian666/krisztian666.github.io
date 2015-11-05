package com.example.todo;

import java.io.IOException;
import android.app.Activity;
import android.content.res.Configuration;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.view.Surface;
import android.view.Display;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.hardware.Camera;
import android.widget.RelativeLayout;

public class ARSurface extends SurfaceView implements SurfaceHolder.Callback {
	protected Activity activity;
    protected SurfaceHolder surfaceHolder;
    protected Camera camera;
    protected double latitude, longitude, direction;
    protected double radius;
    protected PointF center;
    protected Path pathCircle, pathEastWest, pathCompass, pathRedTriangle, pathWhiteTriangle;
    protected Paint paintWhiteFill, paintRedFill, paintWhideFrame;
    protected boolean surfaceConfiguring;
    
	public ARSurface(Activity context) {
		super(context);
		activity = context;
		
		// Opening the camera and setting up the preview surface
		surfaceConfiguring = false;
		surfaceHolder = getHolder();
		surfaceHolder.addCallback(this);
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		camera = Camera.open(0);
		
		// Creating Paint instances for compass.
		// Each part of compass are transparent therefore it seems like
		// a part of the ambient. 
		paintWhideFrame = new Paint();
		paintWhideFrame.setARGB(196, 196, 196, 196);
		paintWhideFrame.setStyle(Paint.Style.STROKE);
		paintWhiteFill = new Paint();
		paintWhiteFill.setARGB(196, 255, 255, 255);
		paintWhiteFill.setStyle(Paint.Style.FILL);
		paintRedFill = new Paint();
		paintRedFill.setARGB(196, 255, 0, 0);
		paintRedFill.setStyle(Paint.Style.FILL);
		
		setPosition(0, 0, 0);
		
		// Enable to draw onto the camera preview
		setWillNotDraw(false);
	}
	
	/**
	 * This method calculates a simple perspective projection.
     * All of parts compass is on the ground/floor,
	 * therefore the Z coordinate is always 0.
     * The camera tilt angle is 30 degrees.
     * The compass size (radius),
	 * focus distance and object distance depends
     * on actual width of preview component.
	 */
	protected PointF perspectiveTransform(double x, double y) {
		double tilt = Math.PI / 6;
		double viewCenterDistance = 2*radius;
		double focusDistance = radius;
		tilt += Math.PI / 2;
		double tz = y * Math.cos(tilt);
		double ty = y * Math.sin(tilt);
		double objectDistance = viewCenterDistance - ty + focusDistance;
		if (Math.abs(objectDistance) < 0.001) {
			return center;
		}else{
			return new PointF((float)(center.x + focusDistance * x / objectDistance), (float)(center.y + focusDistance * -tz / objectDistance));
		}
	}
	
	/**
	 * Refreshing GPS coordinates and direction angle
	 */
	public void setPosition(double pLatitude, double pLongitude, double pDirection) {
		latitude = pLatitude;
		longitude = pLongitude;
		direction = -Math.PI / 2 - pDirection;
		renderCompass();
	}
	
	
	/**
	 * Calculating the visible elements of compass.
	 * pathCircle - the frame of compass
	 * pathCompass - the main (white) surface of compass
	 * pathEastWest - east - west direction indicator
	 * pathRedTriangle - north indicator triangle
	 * pathWhiteTriangle - south indicator triangle
	 */
	protected void renderCompass() {
		double width = getWidth();
		double height = getHeight();
		radius = (width < height ? width : height) * 0.8;
		center = new PointF((float)width / 2, (float)(height - 0.4*radius));
		pathCircle = new Path();
		pathCompass = new Path();
		pathEastWest = new Path();
		PointF north = perspectiveTransform(radius * Math.cos(direction), radius * Math.sin(direction));
		PointF south = perspectiveTransform(-radius * Math.cos(direction), -radius * Math.sin(direction));
		PointF east = perspectiveTransform(radius * Math.cos(direction - Math.PI / 2), radius * Math.sin(direction - Math.PI / 2));
		PointF west = perspectiveTransform(radius * Math.cos(direction + Math.PI / 2), radius * Math.sin(direction + Math.PI / 2));
		PointF nearEast = perspectiveTransform(0.2 * radius * Math.cos(direction - Math.PI / 2), 0.2 * radius * Math.sin(direction - Math.PI / 2));
		PointF nearWest = perspectiveTransform(0.2 * radius * Math.cos(direction + Math.PI / 2), 0.2 * radius * Math.sin(direction + Math.PI / 2));
		pathCircle.moveTo(north.x, north.y);
		pathCompass.moveTo(north.x, north.y);
		for(int i = 1; i < 32; i++) {
			double angle = direction + i * Math.PI / 16;
			PointF point = perspectiveTransform(radius * Math.cos(angle), radius * Math.sin(angle));
			pathCircle.lineTo(point.x, point.y);
			pathCompass.lineTo(point.x, point.y);
		}
		pathCircle.lineTo(north.x, north.y);
		pathCompass.lineTo(north.x, north.y);
		pathCompass.lineTo(nearEast.x, nearEast.y);
		pathCompass.lineTo(nearWest.x, nearWest.y);
		pathCompass.close();
		pathRedTriangle = new Path();
		pathRedTriangle.moveTo(north.x, north.y);
		pathRedTriangle.lineTo(nearWest.x, nearWest.y);
		pathRedTriangle.lineTo(nearEast.x, nearEast.y);
		pathWhiteTriangle = new Path();
		pathWhiteTriangle.moveTo(south.x, south.y);
		pathWhiteTriangle.lineTo(nearWest.x, nearWest.y);
		pathWhiteTriangle.lineTo(nearEast.x, nearEast.y);
		pathWhiteTriangle.close();
		pathEastWest.moveTo(west.x, west.y);
		pathEastWest.lineTo(east.x, east.y);
		postInvalidate();
	}
	
	/**
	 * Drawing parts of compass. This method augments the reality.
	 * The compass appears on the floor, like part of the ambient.
	 */
	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawPath(pathCompass, paintWhiteFill);
		canvas.drawPath(pathWhiteTriangle, paintWhiteFill);
		canvas.drawPath(pathRedTriangle, paintRedFill);
		canvas.drawPath(pathCircle, paintWhideFrame);
		canvas.drawPath(pathEastWest, paintWhideFrame);
	}
	
	/**
	 * This is part of the SurfaceHolder.
     * Callback interface implementation
	 * Sets the review display
	 */
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		try {
			if (camera != null) {
				camera.setPreviewDisplay(surfaceHolder);
			}
		} catch (IOException e) {
			camera.release();
			camera = null;
		}
	}
	
	/**
	 * This is part of the SurfaceHolder.
     * Callback interface implementation.
	 * If surface size or orientation changed,
     * this method calculates the optimal preview
     * size and orientation
	 */
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		if (camera != null) {
			renderCompass();
			camera.stopPreview();
			RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)getLayoutParams();
			layoutParams.height = height;
			layoutParams.width = width;
			setLayoutParams(layoutParams);
			
			boolean portrait = (activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT);
			
	        if (!surfaceConfiguring) {
	        	// surfaceConfiguring variable helps to prevent an infinite recursion
	        	surfaceConfiguring = true;
	            adjustSurfaceLayoutSize(portrait, width, height);
	            surfaceConfiguring = false;
	        }
	        
            int angle;
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(0, info);
            Display display = activity.getWindowManager().getDefaultDisplay();
            switch (display.getRotation()) {
                case Surface.ROTATION_0: // This is display orientation
                	angle = 0;
                    break;
                case Surface.ROTATION_90:
                	angle = 90;
                    break;
                case Surface.ROTATION_180:
                	angle = 180;
                    break;
                case Surface.ROTATION_270:
                	angle = 270;
                    break;
                default:
                    angle = 0;
                    break;
            }
            camera.setDisplayOrientation((info.orientation - angle + 360) % 360);
			try {
				camera.startPreview();
			}catch(Exception e) {
				e.getCause();
			}
		}
	}
	
	/**
	 * This is part of the SurfaceHolder.
     * Callback interface implementation
	 * Stops the camera preview
	 */
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		stop();
	}
	
	/**
	 * Stop preview and release camera
	 */
	public void stop() {
		if (camera != null) {
			camera.stopPreview();
			camera.release();
			camera = null;
		}
	}
	
	/**
	 * If surface size or orientation changed,
     * this method calculates the optimal preview size
	 */
   protected void adjustSurfaceLayoutSize(boolean portrait,
            int availableWidth, int availableHeight) {
        float tmpLayoutHeight, tmpLayoutWidth;
        
        int reqPreviewWidth; // requested width in terms of camera hardware
        int reqPreviewHeight; // requested height in terms of camera hardware
        if (portrait) {
            reqPreviewWidth = availableHeight;
            reqPreviewHeight = availableWidth;
        } else {
            reqPreviewWidth = availableWidth;
            reqPreviewHeight = availableHeight;
        }
        // Adjust surface size with the closest aspect-ratio
        float reqRatio = ((float) reqPreviewWidth) / reqPreviewHeight;
        float curRatio, deltaRatio;
        float deltaRatioMin = Float.MAX_VALUE;
        Camera.Size retSize = null;
        for (Camera.Size size : camera.getParameters().getSupportedPreviewSizes()) {
            curRatio = ((float) size.width) / size.height;
            deltaRatio = Math.abs(reqRatio - curRatio);
            if (deltaRatio < deltaRatioMin) {
                deltaRatioMin = deltaRatio;
                retSize = size;
            }
        }
        
        if (portrait) {
            tmpLayoutHeight = retSize.width;
            tmpLayoutWidth = retSize.height;
        } else {
            tmpLayoutHeight = retSize.height;
            tmpLayoutWidth = retSize.width;
        }

        float factH, factW, fact;
        factH = availableHeight / tmpLayoutHeight;
        factW = availableWidth / tmpLayoutWidth;
        if (factH < factW) {
            fact = factW;
        } else {
            fact = factH;
        }

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)this.getLayoutParams();

        int layoutHeight = (int) (tmpLayoutHeight * fact);
        int layoutWidth = (int) (tmpLayoutWidth * fact);
        if ((layoutWidth != this.getWidth()) || (layoutHeight != this.getHeight())) {
            layoutParams.height = layoutHeight;
            layoutParams.width = layoutWidth;
            layoutParams.topMargin = (getHeight() - layoutHeight) / 2;
            layoutParams.leftMargin = (getWidth() - layoutWidth) / 2;
            this.setLayoutParams(layoutParams); // this will trigger another surfaceChanged invocation.
        }
   }
    
    
}
