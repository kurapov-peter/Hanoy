import java.applet.*;
import java.awt.*;


// Подключаем библиотеки GL4Java
import gl4java.GLContext;
import gl4java.awt.GLCanvas;
import gl4java.awt.GLAnimCanvas;

// Подключаем стандартные библиотеки Java
import java.awt.*;
import java.awt.event.*;
import java.lang.Math;
import java.applet.*;

// Наш основной класс GL
class MainGL extends GLAnimCanvas
{
    // Конструктор
    public MainGL(int w, int h)
    {
        super(w, h);
    }

    // Преинициализация
    public void preInit()
    {
        // Устанавливаем двойную буфферизацию
        doubleBuffer = true;
    }

    // Инициализация. Устанавливаем OpenGL проекции
    public void init()
    {
        gl.glMatrixMode(GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(-5.0, 5.0, -5.0, 5.0, 2.0, 50.0);
        gl.glMatrixMode(GL_MODELVIEW);
        gl.glTranslatef(0, 0, -5);
        glj.gljCheckGL();
        glj.gljMakeCurrent(false);
        gl.glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
        gl.
    }

    // Функция рисования.
    public void display()
    {
        // Если есть какие то проблемы с OpenGL, то выходим
        if (!glj.gljMakeCurrent()) return;

        // Тут идет стандартный поток OpenGL команд

        gl.glClear(GL_COLOR_BUFFER_BIT);

        // ... OpenGL команды ...
        // Отображаем буффер на экран
        // (при двойной буфферизации)
        glj.gljSwap();
    }
}

// Основной класс апплета
public class Main extends Applet
{
    private MainGL glmain = null;
    // Инициализация апплета
    public void init()
    {
        setLayout(null);
        setSize(640,480); // Размер апплета

        // Создаем наш GL объект
        glmain = new MainGL(640, 480);
        // Устанавливаем параметры для него
        glmain.setBounds(0,0,640,480);
        // И добавляем его к нашему апплету
        add(glmain);
    }

    // Эта функция вызывается тогда,
    // когда апплет стартует
    public void start()
    {
        glmain.start();
    }

    // Эта функция вызывается тогда,
    // когда апплет останавливается
    public void stop()
    {
        glmain.stop();
    }

    // Эта функция вызывается тогда,
    // когда апплет уничтожается
    public void destroy()
    {
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
