import gl4java.GLFunc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Карим on 26.04.2016.
 */
public class DiskManager {
    private List<Disk> diskList;
    public DiskManager() {
        diskList = new ArrayList<>();
    }

    public void createDisk(float radius, float x, float y, float z, GLFunc gl) {
        diskList.add(new Disk(radius, x, y, z, gl));
    }

    public void draw() {
        diskList.forEach(Disk::draw);
    }
}
