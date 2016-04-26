import java.applet.*;
import java.awt.*;


import gl4java.GLContext;
import gl4java.GLFunc;
import gl4java.GLUFunc;
import gl4java.awt.GLCanvas;
import gl4java.awt.GLAnimCanvas;

// Подключаем стандартные библиотеки Java
import java.awt.*;
import java.awt.event.*;
import java.lang.Math;
import java.applet.*;

import java.lang.Math;


class MainGL extends GLAnimCanvas
{
    public MainGL(int w, int h) {
        super(w, h);
    }

    public void preInit() {
        doubleBuffer = true;
    }

    public void init() {
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
    }

    private void setCamera(GLFunc gl, GLUFunc glu, float distance) {
        // Change to projection matrix.
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glLoadIdentity();

        // Perspective.
        float widthHeightRatio = (float) getWidth() / (float) getHeight();
        glu.gluPerspective(45, widthHeightRatio, 1, 1000);
        glu.gluLookAt(20, 20, distance, 0, 0, 0, 0, 0, 1);

        // Change back to model view matrix.
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void display() {
        System.out.println("display");
        System.out.println(getFps());
        float radius = 5.0f;

        if (!glj.gljMakeCurrent()) return;



        gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        gl.glBegin(gl.GL_LINES);
            gl.glColor3f(1.0f, 0.0f, 0.0f);
            gl.glVertex3f(0.0f, 0.0f, 0.0f);
            gl.glVertex3f(5.0f, 0.0f, 0.0f);

            gl.glColor3f(0.0f, 1.0f, 0.0f);
            gl.glVertex3f(0.0f, 0.0f, 0.0f);
            gl.glVertex3f(0.0f, 5.0f, 0.0f);

            gl.glColor3f(0.0f, 0.0f, 1.0f);
            gl.glVertex3f(0.0f, 0.0f, 0.0f);
            gl.glVertex3f(0.0f, 0.0f, 5.0f);
        gl.glEnd();


        System.out.println("start draw");


        DiskManager diskManager = new DiskManager();
        diskManager.createDisk(10.0f, 0, 1, 0, gl);
        diskManager.createDisk(radius, 0, 1, 3, gl);
        diskManager.createDisk(1.0f, 0, 1, 6, gl);
        diskManager.draw();


        setCamera(gl, glu, 20);
//        gl.glTranslatef(0,1,0);

        glj.gljSwap();
    }
}


public class Main extends Applet
{
    private MainGL glmain = null;

    public void init() {
        setLayout(null);
        setSize(640,480);

        glmain = new MainGL(640, 480);

        glmain.setBounds(0,0,640,480);

        add(glmain);
    }
    
    public void start()
    {
        glmain.start();
    }

    public void stop()
    {
        glmain.stop();
    }

    public void destroy() {
        glmain.stop();
        glmain.cvsDispose();
    }
}



//public class Main extends Applet{
//    private void solve(int number, int from, int to, int buf, Graphics g) {
//        if (number != 0) {
//            solve(number - 1, from, buf, to, g);
//            System.out.println("Снимаем " + number + "-й диск с " + from + "-го стержня и одеваем его на " + to + "-й");
//            solve(number - 1, buf, to, from, g);
//        }
//    }
//
//    private void drawDisk(int size, int pos) {
//
//    }
//
//    public void paint(Graphics g) {
//        g.drawRect(10, 5, 40, 10);
//        g.drawRect(50, 50, 10, 50);
//        solve(3, 1, 3 ,2, g);
//    }
//
//    public void update(Graphics g) {
//
//    }
//
//}
