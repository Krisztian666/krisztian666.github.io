        pen = (ImageView)findViewById(R.id.penImageView); //{img:"scr-pen.gif", speak:"Load frame-by-frame animation"}
        pen.setBackgroundResource(R.drawable.animation_pen);
        penAnimation = (AnimationDrawable)pen.getBackground(););

        ...
    }

    @Override
     public void onWindowFocusChanged(boolean r) {
        super.onWindowFocusChanged(r);
        penAnimation.start();//{img:"scr-pen.gif", speak:"Start animation"}

    } 

