package by.epam.training.bin;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ShipTest {

    private Dock dock1 = Dock.createDock(20);
    @Test
    public void testShipConstructor() throws Exception {
        Ship ship = new Ship(2, dock1, 1);
        Assert.assertNotNull(ship);

    }

    @Test
    public void testNoAvailableContainers() throws Exception {
    }

    @Test
    public void testGetShipId() throws Exception {
    }

    @Test
    public void testUnloadShip() throws Exception {
    }

    @Test
    public void testLoadShip() throws Exception {
    }

    @Test
    public void testRun() throws Exception {
    }

}