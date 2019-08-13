package segureseufoguete.www.pong;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLU;
import android.opengl.GLSurfaceView.Renderer;
import android.view.MotionEvent;


public class OpenGLRenderer implements Renderer {
    // Initialize our square.

    private Square aa, bb, mSquare, fiel,pause;

    Square square;
    private float angle; // Don't forget to add this.
    private float mAngle;

    static float xBarra1 = 0f, yBarra1 = 3.5f;
    static float xBarra2 = 0f, yBarra2 = -3.5f;
    static  float xBall = 0f, yBall = 0f, xStep =0.05f,yStep = 0.05f;


    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // Set the background color to black ( rgba ).
        gl.glClearColor(0.043f, 0.152f, 0.247f, 1f);
        // Enable Smooth Shading, default not really needed.
        gl.glShadeModel(GL10.GL_SMOOTH);
        // Depth buffer setup.
        gl.glClearDepthf(1.0f);
        // Enables depth testing.
        gl.glEnable(GL10.GL_DEPTH_TEST);
        // The type of depth testing to do.
        gl.glDepthFunc(GL10.GL_LEQUAL);
        // Really nice perspective calculations.
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT,
                GL10.GL_NICEST);

        mSquare   = new Square(new float[]{
                -0.5f, 0.0f, 0.0f,   // top left
                -0.5f, -0.5f, 0.0f,   // bottom left
                0.0f, -0.5f, 0.0f,   // bottom right
                0.0f, 0.0f, 0.0f});

        aa = new Square(new float[] {
                -1f, 0.3f, 0.0f,   // top left
                -1f, 0.0f, 0.0f,   // bottom left
                1f, 0.0f, 0.0f,   // bottom right
                1f,  0.3f, 0.0f });

        bb = new Square(new float[] {
                -1f, 0.f, 0.0f,   // top left
                -1f, -0.3f, 0.0f,   // bottom left
                1f, -0.3f, 0.0f,   // bottom right
                1f,  0.0f, 0.0f });
        fiel = new Square( new float[] {
                -2f, 0.05f, 0.0f,   // top left
                -2f, -0.05f, 0.0f,   // bottom left
                2f, -0.05f, 0.0f,   // bottom right
                2f,  0.05f, 0.0f});
        pause = new Square( new float[]{
                2.3f, 0.3f, 0.0f,   // top left
                2.f, 0.3f, 0.0f,   // bottom left
                2.15f, -0.3f, 0.0f,   // bottom right
                2.15f,  -0.3f, 0.0f
        });
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * android.opengl.GLSurfaceView.Renderer#onDrawFrame(javax.
         * microedition.khronos.opengles.GL10)
     */



    public void onDrawFrame(GL10 gl) {


        // Clears the screen and depth buffer.
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        // Replace the current matrix with the identity matrix
        gl.glLoadIdentity();
        // Translates 10 units into the screen.
        gl.glTranslatef(0, 0, -10);
        gl.glColor4f(1f,1f,1f,1f);

        fiel.draw(gl);
      //  pause.draw(gl);
        // SQUARE A
        gl.glPushMatrix();
            gl.glTranslatef(xBarra1,yBarra1,0);
            gl.glColor4f(0.1490f,1f,0.6509f,1f);
            aa.draw(gl);
        gl.glPopMatrix();

//        gl.glLineWidth(1);
//        gl.glTexImage2D();

        // SQUARE B
        // Save the current matrix
        gl.glPushMatrix();
            gl.glTranslatef(xBarra2, yBarra2, 0);
            gl.glColor4f(0.839f,0.384f,0.058f,1f);
            bb.draw(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
            gl.glTranslatef(xBall, yBall, 0);
            gl.glColor4f(1f,1f,1f,1f);
            mSquare.draw(gl);
        gl.glPopMatrix();

        if(xBall<=-1.9 || xBall>=2.5)
            xStep*= -1;

        if(yBall >= yBarra1 && yBall <= yBarra1+0.3 && xBall+0.5 >= xBarra1-1 && xBall <= xBarra1+1) {
            yStep *= -1;
            yBall -= 0.2;
        }

        if(yBall <= yBarra2+0.5 && yBall >=yBarra2 && xBall >= xBarra2-1 && xBall <= xBarra2+1) {
            yStep *= -1;
            yBall += 0.2;
        }

        xBall += xStep;
        yBall += yStep;


        // Increse the angle.
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * android.opengl.GLSurfaceView.Renderer#onSurfaceChanged(javax.
         * microedition.khronos.opengles.GL10, int, int)
     */
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        // Sets the current view port to the new size.
        gl.glViewport(0, 0, width, height);
        // Select the projection matrix
        gl.glMatrixMode(GL10.GL_PROJECTION);
        // Reset the projection matrix
        gl.glLoadIdentity();
        // Calculate the aspect ratio of the window
        GLU.gluPerspective(gl, 45.0f,
                (float) width / (float) height,
                0.1f, 100.0f);
        // Select the modelview matrix
        gl.glMatrixMode(GL10.GL_MODELVIEW);

        // Reset the modelview matrix
        gl.glLoadIdentity();
    }

    public float getAngle() {
        return mAngle;
    }

    /**
     * Sets the rotation angle of the triangle shape (mTriangle).
     */
    public void setAngle(float angle) {
        mAngle = angle;
    }


}