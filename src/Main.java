import java.applet.*;


import gl4java.GLFunc;
import gl4java.GLUFunc;
import gl4java.awt.GLAnimCanvas;


import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;


class MainGL extends GLAnimCanvas implements MouseMotionListener, MouseWheelListener
{
    private DiskManager diskManager = new DiskManager();
    private int numberOfDisks;
    private float alpha = 0, height = 50, dist = 50;
    private int Xpos = -1;
    private int Ypos = -1;

    public MainGL(int w, int h, int numberOfDisks) {
        super(w, h);
        this.numberOfDisks = numberOfDisks;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        System.out.println("wheeeeel");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (Xpos != -1) {
            this.moveCamera(e.getX() - Xpos, e.getY() - Ypos);
        }
        Xpos = e.getX();
        Ypos = e.getY();
        e.consume();
        System.out.println("moved");
        display();
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    public void preInit() {
        doubleBuffer = true;
    }

    public void init() {
        addMouseMotionListener(this);

//        float mat_specular[] = { 1.0f, 1.0f, 1.0f, 1.0f };
//        float mat_shininess[] = { 50.0f };
        gl.glMatrixMode(GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(-5.0, 5.0, -5.0, 5.0, 0.0, 10.0);
        gl.glMatrixMode(GL_MODELVIEW);
        gl.glTranslatef(0, 0, -5);
        glj.gljCheckGL();
        glj.gljMakeCurrent(false);
        gl.glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
        gl.glDepthRange(-200.0f, 200.0f);
        gl.glEnable(GL_DEPTH_TEST);
        gl.glDepthFunc(GL_LESS);

        gl.glShadeModel (GL_SMOOTH);
//        gl.glMaterialfv(GL_FRONT_AND_BACK, GL_SPECULAR, mat_specular);
//        gl.glMaterialfv(GL_FRONT_AND_BACK, GL_SHININESS, mat_shininess);
        gl.glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);

//        gl.glEnable(GL_LIGHTING);
//        float pos[] = {30.0f, 30.0f, 30.0f, 1.0f };
//        gl.glLightfv(gl.GL_LIGHT0, gl.GL_POSITION, pos);
//        float dif[] = {.0f,.0f,.0f,.0f};
//        gl.glLightfv(gl.GL_LIGHT0, gl.GL_DIFFUSE, dif);
//        float amb[] = {.371f, .82421875f, .9375f, 1.0f};
//        gl.glLightfv(gl.GL_LIGHT0, gl.GL_AMBIENT, amb);
//
//        gl.glEnable(GL_LIGHT0);

        diskManager.createDisks(numberOfDisks, 1.5f, gl);
        diskManager.createSticks(gl);
    }

    public void restart() {
        this.numberOfDisks += 1;
        diskManager = new DiskManager();
        diskManager.createDisks(numberOfDisks, 1.5f, gl);
        diskManager.createSticks(gl);
    }

    public void moveCamera(float side, float up) {
        this.alpha += side;
        this.height += up;
    }

    private void setCamera(GLFunc gl, GLUFunc glu) {
        // Change to projection matrix.
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glLoadIdentity();

        // Perspective.
        float widthHeightRatio = (float) getWidth() / (float) getHeight();
        glu.gluPerspective(45, widthHeightRatio, 1, 1000);
        glu.gluLookAt(dist * Math.sin(Math.toRadians(alpha)), dist * Math.cos(Math.toRadians(alpha)), height, 0, 0, 5, 0, 0, 1);

        // Change back to model view matrix.
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void display() {
        if (!glj.gljMakeCurrent()) return;

        gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        gl.glBegin(gl.GL_LINES);
            gl.glColor3f(1.0f, 0.0f, 0.0f);
            gl.glVertex3f(0.0f, 0.0f, 0.0f);
            gl.glVertex3f(20.0f, 0.0f, 0.0f);

            gl.glColor3f(0.0f, 1.0f, 0.0f);
            gl.glVertex3f(0.0f, 0.0f, 0.0f);
            gl.glVertex3f(0.0f, 20.0f, 0.0f);

            gl.glColor3f(0.0f, 0.0f, 1.0f);
            gl.glVertex3f(0.0f, 0.0f, 0.0f);
            gl.glVertex3f(0.0f, 0.0f, 20.0f);
        gl.glEnd();


        this.diskManager.draw();

        setCamera(gl, glu);

        glj.gljSwap();
    }


    public boolean diskMove(int diskNumber, int stickNumber, MainGL _this) {
        return this.diskManager.moveDiskToStick(diskNumber, stickNumber, gl, this);
    }
}


public class Main extends Applet
{
    private MainGL glmain = null;

    private Timer timer = new Timer();
    private RedrawTask redrawTask = new RedrawTask();

    private int numberOfDisks = 2;

    public void init() {
        setLayout(null);
        setSize(640,480);

        glmain = new MainGL(640, 480, numberOfDisks);

        glmain.setBounds(0,0,640,480);

        add(glmain);

    }

    private void solve(int number, int from, int to, int buf) {
        if (number != 0) {
            solve(number - 1, from, buf, to);
            System.out.println("Снимаем " + number + "-й диск с " + from + "-го стержня и одеваем его на " + to + "-й");
            glmain.diskMove(number - 1, to, glmain);
            solve(number - 1, buf, to, from);
        }
    }

    public void start()
    {
        glmain.start();
        timer.schedule(redrawTask, 1000, 50);
    }

    public void stop()
    {
        glmain.stop();
    }

    public void destroy() {
        glmain.stop();
        glmain.cvsDispose();
    }

    class RedrawTask extends TimerTask {
        @Override
        public void run() {
            solve(numberOfDisks++, 0, 2, 1);
            glmain.repaint();
            glmain.restart();
//            cancel();
        }
    }

}