import gl4java.GLFunc;

import java.util.ArrayList;
import java.util.List;


public class DiskManager {
    private List<Disk> diskList;
    private List<Disk> stickList;
    
    private float stickHeight = 0;
    
    public DiskManager() {
        diskList = new ArrayList<>();
    }

    // Create all disks before sticks so that disk manager knows what height to create
    private void createDisk(float radius, float x, float y, float z, float R, float G, float B, float height, int onStick, GLFunc gl) {
        diskList.add(new Disk(radius, x, y, z, R, G, B, height, onStick, gl));
    }

    public void createDisks(int numberOfDisk, float height, GLFunc gl) {
        for (int i = numberOfDisk - 1; i >= 0; i--) {
            createDisk( (numberOfDisk - i)*2.0f, 20, 0, i*2.0f, .371f, .82421875f, .9375f, height, 0, gl);
        }
    }

    public void createSticks(GLFunc gl) {
        stickList = new ArrayList<>();
        stickHeight = diskList.size() * 4;
        float stickWidth = diskList.get(0).getRadius() * 2.1f + 20;
        // Sticks
        stickList.add(new Disk(.2f, 20, 0, 0, 0.5f, 0.5f, 0.5f, stickHeight, -1, gl));
        stickList.add(new Disk(.2f, 0, 0, 0, 0.5f, 0.5f, 0.5f, stickHeight, -1, gl));
        stickList.add(new Disk(.2f, -20, 0, 0, 0.5f, 0.5f, 0.5f, stickHeight, -1, gl));
    }

    private void moveDisk(int diskNumber, float x, float y, float z) {
        Disk disk = diskList.get(diskNumber);
        disk.setCoords(x, y, z);
    }

    public boolean moveDiskToStick(int diskNumber, int stickNumber, GLFunc gl, MainGL maingl) {
        int velocity = 1;
        Disk disk = diskList.get(diskNumber);
        // move up
        System.out.println("UP");
        for (float i = disk.getZ(); i < stickHeight + 1; i+=velocity) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            moveDisk(diskNumber, disk.getX(), disk.getY(), i);
            maingl.repaint();
        }
        //move side

        System.out.println("SIDE");
        velocity = disk.getX() > stickList.get(stickNumber).getX() ? velocity : -velocity;
        for (float i = disk.getX(); i != stickList.get(stickNumber).getX(); i-=velocity) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            moveDisk(diskNumber, i, disk.getY(), disk.getZ());
            maingl.repaint();
        }
        moveDisk(diskNumber, stickList.get(stickNumber).getX(), disk.getY(), disk.getZ());

        //move down
        System.out.println("DOWN");
        float maxHeight = -2.0f;
        // max height on stick
        for (Disk disk1: diskList) {
            if (disk1.getStick() == stickNumber && maxHeight < disk1.getZ()) {
                maxHeight = disk1.getZ();
            }
        }

        velocity = 1;
        for (float i = disk.getZ(); i >= maxHeight + 2.0f; i-=velocity) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            moveDisk(diskNumber, disk.getX(), disk.getY(), i);
            maingl.repaint();
        }
        moveDisk(diskNumber, stickList.get(stickNumber).getX(), disk.getY(), disk.getZ());

        disk.toStick(stickNumber);
        return true;
    }


    public void draw() {
        diskList.forEach(Disk::draw);
        stickList.forEach(Disk::draw);
    }
}
