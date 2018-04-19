package by.epam.training.bin;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DockTest {

    int initialContainersInDock= 20;
    Dock dock = Dock.createDock(initialContainersInDock);

    @Test
    public void testGetPiersInDock() throws Exception {

    }

    @Test
    public void testGetContainersInDock() throws Exception {

    }

    @Test
    public void testCreateValidDock() throws Exception {
        Dock dock = Dock.createDock(initialContainersInDock);
        Assert.assertNotNull(dock);
        Assert.assertEquals(initialContainersInDock, 20);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateDockWithInvalidParameters() throws Exception {
        int initialContainersInDockInvalid = 200;
        Dock dock1 = Dock.createDock(initialContainersInDockInvalid);
        Assert.assertNull(dock1);
    }

    @Test
    public void testReturnPier() throws Exception {

   }

    @Test
    public void testGetPier() throws Exception {
        Pier pier = dock.getPier();
        Assert.assertEquals(dock.SEMAPHORE.availablePermits(), 2);
        Assert.assertEquals(dock.getPiersInDock().size(), 2);
    }

    @Test
    public void testRemoveContainerFromDock() throws Exception {
        Dock dock = Dock.createDock(initialContainersInDock);
        dock.removeContainerFromDock();
        Assert.assertEquals(19,dock);

    }

    @Test
    public void testAddContainerToDock() throws Exception {
    }

}