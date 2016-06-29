package com.spectralogic.dsbrowser.gui.components.physicalplacement;


import com.spectralogic.ds3client.models.PhysicalPlacement;
import com.spectralogic.ds3client.models.Pool;
import com.spectralogic.ds3client.models.Tape;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class Ds3PhysicalPlacementTest {

    @Test
    public void getIntializedValue() throws Exception {
        final Ds3PhysicalPlacement value = new Ds3PhysicalPlacement();
        assertEquals(value.getPhysicalPlacement(), null);
        assertEquals(value.getListPools(), null);
        assertEquals(value.getListTapes(), null);
    }

    @Test
    public void getPhysicalPlacement() throws Exception {
        final Ds3PhysicalPlacement value = new Ds3PhysicalPlacement(new PhysicalPlacement(), new ArrayList<Tape>(), new ArrayList<Pool>());
        assertNotEquals(value.getPhysicalPlacement(), null);
    }

    @Test
    public void getListTapes() throws Exception {
        final Ds3PhysicalPlacement value = new Ds3PhysicalPlacement(new PhysicalPlacement(), new ArrayList<Tape>(), new ArrayList<Pool>());
        assertNotEquals(value.getListTapes(), null);

    }

    @Test
    public void getListPools() throws Exception {
        final Ds3PhysicalPlacement value = new Ds3PhysicalPlacement(new PhysicalPlacement(), new ArrayList<Tape>(), new ArrayList<Pool>());
        assertNotEquals(value.getListPools(), null);

    }

}