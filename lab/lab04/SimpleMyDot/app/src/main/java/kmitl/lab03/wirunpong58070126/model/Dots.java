package kmitl.lab03.wirunpong58070126.model;

import java.util.ArrayList;

/**
 * Created by BellKunG on 9/11/2017 AD.
 */

public class Dots {

    private ArrayList<Dot> allDot = new ArrayList<>();
    private onDotsChangeListener listener;

    public void addDot(Dot dot) {
        allDot.add(dot);
        this.listener.onDotsChanged(this);
    }

    public void clearAllDot() {
        allDot.clear();
        this.listener.onDotsChanged(this);
    }

    public int findDot(int x, int y) {
        for (int i = 0; i < allDot.size(); i++) {
            double distance = Math.sqrt(Math.pow(allDot.get(i).getCenterX() - x, 2) +
                    Math.pow(allDot.get(i).getCenterY() - y, 2));
            if (distance <= allDot.get(i).getRadius()) {
                return i;
            }
        }
        return -1;
    }

    public void removeByPosition(int index) {
        allDot.remove(index);
        this.listener.onDotsChanged(this);
    }

    public interface onDotsChangeListener {
        void onDotsChanged(Dots dots);
    }

    public void setListener(onDotsChangeListener listener) {
        this.listener = listener;
    }

    public ArrayList<Dot> getAllDot() {
        return allDot;
    }


}
