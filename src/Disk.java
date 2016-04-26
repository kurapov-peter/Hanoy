/**
 * Created by Карим on 26.04.2016.
 */
import gl4java.GLFunc;

public class Disk {
    private float radius;
    private float height = 2.0f;
    private GLFunc gl;
    private float x;
    private float y;
    private float z;

    public Disk(float radius, float x, float y, float z, GLFunc gl) {
        this.radius = radius;
        this.gl = gl;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void draw() {
        gl.glPolygonMode(gl.GL_FRONT_AND_BACK,gl.GL_FILL);
        gl.glBegin(gl.GL_TRIANGLES);
        for (int i = 0; i < 6; i++) {
            // Top & Red
            gl.glColor3f(1.0f, .82421875f, .9375f);
            gl.glVertex3f(x, y, z);

            // Right & Green
            gl.glColor3f(.371f, .82421875f, .9375f);
            gl.glVertex3f(
                    x + (float)(radius * Math.sin(Math.toRadians(60.0f * i))),
                    y + (float)(radius * Math.cos(Math.toRadians(60.0f * i))),
                    z
            );

            // Left & Blue
            gl.glColor3f(.371f, .82421875f, .9375f);
            gl.glVertex3f(
                    x + (float)(radius * Math.sin(Math.toRadians(60.0f * i + 60.0f))),
                    y + (float)(radius * Math.cos(Math.toRadians(60.0f * i + 60.0f))),
                    z
            );
        }
        gl.glEnd();

        gl.glBegin(gl.GL_QUADS);
        for(int i = 0; i < 6; i++) {
            gl.glColor3f(.371f, .82421875f, .9375f);
            gl.glVertex3f(
                    x + (float) (radius * Math.sin(Math.toRadians(60.0f * i))),
                    y + (float) (radius * Math.cos(Math.toRadians(60.0f * i))),
                    z
            );

            gl.glVertex3f(
                    x + (float) (radius * Math.sin(Math.toRadians(60.0f * i + 60.0f))),
                    y + (float) (radius * Math.cos(Math.toRadians(60.0f * i + 60.0f))),
                    z
            );

            gl.glVertex3f(
                    x + (float) (radius * Math.sin(Math.toRadians(60.0f * i + 60.0f))),
                    y + (float) (radius * Math.cos(Math.toRadians(60.0f * i + 60.0f))),
                    z - height
            );

            gl.glVertex3f(
                    x + (float) (radius * Math.sin(Math.toRadians(60.0f * i))),
                    y + (float) (radius * Math.cos(Math.toRadians(60.0f * i))),
                    z - height
            );
        }
        gl.glEnd();

        gl.glPolygonMode(gl.GL_FRONT_AND_BACK, gl.GL_LINE);
        gl.glLineWidth(1.5f);
        gl.glBegin(gl.GL_QUADS);

        for(int i = 0; i < 6; i++) {
            gl.glColor3f(0.5f, 0.5f, 0.5f);
            gl.glVertex3f(
                    x + (float) (radius * Math.sin(Math.toRadians(60.0f * i))),
                    y + (float) (radius * Math.cos(Math.toRadians(60.0f * i))),
                    z
            );

            // Left & Blue
            gl.glVertex3f(
                    x + (float) (radius * Math.sin(Math.toRadians(60.0f * i + 60.0f))),
                    y + (float) (radius * Math.cos(Math.toRadians(60.0f * i + 60.0f))),
                    z
            );

            // Left & Blue
            gl.glVertex3f(
                    x + (float) (radius * Math.sin(Math.toRadians(60.0f * i + 60.0f))),
                    y + (float) (radius * Math.cos(Math.toRadians(60.0f * i + 60.0f))),
                    z - height
            );

            gl.glVertex3f(
                    x + (float) (radius * Math.sin(Math.toRadians(60.0f * i))),
                    y + (float) (radius * Math.cos(Math.toRadians(60.0f * i))),
                    z - height
            );
        }
        gl.glEnd();
    }
}
