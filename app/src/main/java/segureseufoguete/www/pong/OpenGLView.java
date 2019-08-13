package segureseufoguete.www.pong;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by ian on 01/12/17.
 */

public class OpenGLView extends GLSurfaceView {


    private final OpenGLRenderer mRenderer;
//
//
    public OpenGLView(Context context){
        super(context);

//        setEGLContextClientVersion(2);
//        setPreserveEGLContextOnPause(true);
        mRenderer = new OpenGLRenderer();
        setRenderer(mRenderer);

//        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }


    public OpenGLView(Context context, AttributeSet  attrs){
        super(context,attrs);
//        setEGLContextClientVersion(2);
//        setPreserveEGLContextOnPause(true);
        mRenderer = new OpenGLRenderer();
        setRenderer(mRenderer);

    }



    private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
    private float mPreviousX;
    private float mPreviousY;


    public boolean onTouchEvent(MotionEvent e) {
        // MotionEvent reports input details from the touch screen
        // and other input controls. In this case, you are only
        // interested in events where the touch position changed.

        float x = e.getX();
        float y = e.getY();

        switch (e.getAction()) {
            case MotionEvent.ACTION_MOVE:

                float dx = x - mPreviousX;
                float dy = y - mPreviousY;

                // reverse direction of rotation above the mid-line
                if (y > getHeight() / 2) {
                    dx = dx * -1 ;
                }

                // reverse direction of rotation to left of the mid-line
                if (x < getWidth() / 2) {
                    dy = dy * -1 ;
                }

                mRenderer.setAngle(
                        mRenderer.getAngle() +
                                ((dx + dy) * TOUCH_SCALE_FACTOR));  // = 180.0f / 320
                requestRender();
        }


        if(mPreviousX> 500 && mPreviousY < 1000 && OpenGLRenderer.xBarra1+1 < 2.5)
            OpenGLRenderer.xBarra1 = (mPreviousX-500)/250 ;
        if(mPreviousX < 500 && mPreviousY < 1000 && OpenGLRenderer.xBarra1-1 > -2.5)
            OpenGLRenderer.xBarra1 = (mPreviousX)/250 -2;

        if(mPreviousX> 500 && mPreviousY > 1000 && OpenGLRenderer.xBarra2+1 < 2.5)
            OpenGLRenderer.xBarra2 = (mPreviousX-500)/250;

        if(mPreviousX < 500 && mPreviousY > 1000 && OpenGLRenderer.xBarra2-1 > -2.5)
            OpenGLRenderer.xBarra2 = (mPreviousX)/250 -2;

        if(OpenGLRenderer.yBall > 4 || OpenGLRenderer.yBall < -4){
            OpenGLRenderer.xBall = 0;
            OpenGLRenderer.yBall = 0;
            OpenGLRenderer.yStep *= -1;
        }


        //Log.i("test","x = "+mPreviousX+ "  y = "+mPreviousY);

        mPreviousX = x;
        mPreviousY = y;
        return true;
    }
}


