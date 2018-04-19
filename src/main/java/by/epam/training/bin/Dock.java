package by.epam.training.bin;

import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Semaphore;

public class Dock {
    public static final Logger LOG = Logger.getLogger(Ship.class);
    public static final int PIER_NUMBER = 3;
    public static final Semaphore SEMAPHORE = new Semaphore(PIER_NUMBER, true);
    public static final int MAX_DOCK_CAPACITY = 40;
    private static List<Container> containersInDock;
    private static Queue<Pier> piersInDock = new LinkedList<Pier>();

    public static Queue<Pier> getPiersInDock() {
        return piersInDock;
    }

    public static List<Container> getContainersInDock() {
        return containersInDock;
    }

    public static Dock createDock(int PIER_NUMBER, int initialContainersInDock) {
        Dock dock = new Dock();
        if (initialContainersInDock > MAX_DOCK_CAPACITY || initialContainersInDock < 0) {
            LOG.error("Attempting to create an object with invalid parameters");
            throw new IllegalArgumentException();
        } else {
            containersInDock = new CopyOnWriteArrayList<Container>();
            for (int i = 0; i < initialContainersInDock; i++) {
                containersInDock.add(new Container());
            }
            for (int i = 0; i < PIER_NUMBER; i++) {
                piersInDock.add(new Pier(i));
            }
            return dock;
        }
    }

    public void returnResource(Pier pier) {
        Dock.getPiersInDock().add(pier);
        System.out.println("Pier #" + pier.getPierNumber()+ " is released");
        Dock.SEMAPHORE.release();
        System.out.println("Containers in dock: " + Dock.getContainersInDock().size());
    }

    public Pier getPier() {
        try {
            Dock.SEMAPHORE.acquire();
        } catch (InterruptedException e) {
            LOG.error( e.getMessage());
        }
        Pier pier = Dock.getPiersInDock().poll();
            return pier;

    }
        public Container removeContainerFromDock() {
        if (!containersInDock.isEmpty()) {
            return containersInDock.remove(0);
        } else
            return null;
    }

    public boolean addContainerToDock(Container container) {
        return containersInDock.add(container);
    }
}
