import gl4java.GLFunc;

public class Disk {


    private float radius;
    private float height = 2.0f;
    private GLFunc gl;


    private float x;
    private float y;
    private float z;
    private float R;
    private float G;
    private float B;
    private float angle = 0;
    private int onStick;

    public Disk(float radius, float x, float y, float z, float R, float G, float B, float height, int onStick, GLFunc gl) {
        this.radius = radius;
        this.gl = gl;
        this.x = x;
        this.y = y;
        this.z = z;
        this.R = R;
        this.G = G;
        this.B = B;
        this.height = height;
        this.onStick = onStick;
    }

    public void setCoords(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public float getRadius() {
        return radius;
    }

    public void toStick(int stickNumber) { this.onStick = stickNumber; }
    public int getStick() { return this.onStick; }

    public void draw() {
        gl.glPolygonMode(gl.GL_FRONT_AND_BACK,gl.GL_FILL);
        // Top edge
        gl.glBegin(gl.GL_TRIANGLES);
        for (int i = 0; i < 360; i++) {
            // Center
            gl.glColor3f(R, G, B);
            gl.glVertex3f(x, y, z);

            // Main color
            gl.glColor3f(R, G, B);
            gl.glVertex3f(
                    x + (float)(radius * Math.sin(Math.toRadians(1.0f * i))),
                    y + (float)(radius * Math.cos(Math.toRadians(1.0f * i))),
                    z
            );

            gl.glColor3f(R, G, B);
            gl.glVertex3f(
                    x + (float)(radius * Math.sin(Math.toRadians(1.0f * i + 1.0f))),
                    y + (float)(radius * Math.cos(Math.toRadians(1.0f * i + 1.0f))),
                    z
            );

            gl.glColor3f(R, G, B);
            gl.glVertex3f(x, y, z + height);

            // Main color
            gl.glColor3f(R, G, B);
            gl.glVertex3f(
                    x + (float)(radius * Math.sin(Math.toRadians(1.0f * i))),
                    y + (float)(radius * Math.cos(Math.toRadians(1.0f * i))),
                    z + height
            );

            gl.glColor3f(R, G, B);
            gl.glVertex3f(
                    x + (float)(radius * Math.sin(Math.toRadians(1.0f * i + 1.0f))),
                    y + (float)(radius * Math.cos(Math.toRadians(1.0f * i + 1.0f))),
                    z + height
            );
        }
        gl.glEnd();
        // Sides
        gl.glBegin(gl.GL_QUADS);
        for(int i = 0; i < 360; i++) {
            gl.glColor3f(R, G, B);
            gl.glVertex3f(
                    x + (float) (radius * Math.sin(Math.toRadians(1.0f * i))),
                    y + (float) (radius * Math.cos(Math.toRadians(1.0f * i))),
                    z
            );

            gl.glVertex3f(
                    x + (float) (radius * Math.sin(Math.toRadians(1.0f * i + 1.0f))),
                    y + (float) (radius * Math.cos(Math.toRadians(1.0f * i + 1.0f))),
                    z
            );

            gl.glVertex3f(
                    x + (float) (radius * Math.sin(Math.toRadians(1.0f * i + 1.0f))),
                    y + (float) (radius * Math.cos(Math.toRadians(1.0f * i + 1.0f))),
                    z + height
            );

            gl.glVertex3f(
                    x + (float) (radius * Math.sin(Math.toRadians(1.0f * i))),
                    y + (float) (radius * Math.cos(Math.toRadians(1.0f * i))),
                    z + height
            );
        }
        gl.glEnd();

        gl.glPolygonMode(gl.GL_FRONT_AND_BACK, gl.GL_LINE);
        gl.glLineWidth(1.f);
        // Edges
        gl.glBegin(gl.GL_LINES);

        for(int i = 0; i < 360; i++) {
            gl.glColor3f(0.5f, 0.5f, 0.5f);
            gl.glVertex3f(
                    x + (float) (radius * Math.sin(Math.toRadians(1.0f * i))),
                    y + (float) (radius * Math.cos(Math.toRadians(1.0f * i))),
                    z
            );

            gl.glVertex3f(
                    x + (float) (radius * Math.sin(Math.toRadians(1.0f * i + 1.0f))),
                    y + (float) (radius * Math.cos(Math.toRadians(1.0f * i + 1.0f))),
                    z
            );

            gl.glVertex3f(
                    x + (float) (radius * Math.sin(Math.toRadians(1.0f * i + 1.0f))),
                    y + (float) (radius * Math.cos(Math.toRadians(1.0f * i + 1.0f))),
                    z + height
            );

            gl.glVertex3f(
                    x + (float) (radius * Math.sin(Math.toRadians(1.0f * i))),
                    y + (float) (radius * Math.cos(Math.toRadians(1.0f * i))),
                    z + height
            );
        }
        gl.glEnd();
    }
}
