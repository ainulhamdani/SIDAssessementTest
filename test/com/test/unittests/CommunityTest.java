/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.test.unittests;

/**
 *
 * @author dhan
 */

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.test.Community;
import com.test.WaterPoint;

public class CommunityTest {
    @Test
    public void testGetFunctionalPoints(){
        Community community = new Community();
        community.setName("Test Commuity");

        WaterPoint waterPoint1 = new WaterPoint();
        waterPoint1.setWaterFunctioning("yes");
        WaterPoint waterPoint2 = new WaterPoint();
        waterPoint2.setWaterFunctioning("yes");
        WaterPoint waterPoint3 = new WaterPoint();
        waterPoint3.setWaterFunctioning("yes");
        WaterPoint waterPoint4 = new WaterPoint();
        waterPoint4.setWaterFunctioning("no");

        community.addWaterPoint(waterPoint1);
        community.addWaterPoint(waterPoint2);
        community.addWaterPoint(waterPoint3);
        community.addWaterPoint(waterPoint4);

        assertEquals(community.getFunctionalPoints(), 3);
    }
    @Test
    public void testGetBrokenWaterPointsPercentage(){
        Community community = new Community();
        community.setName("Test Commuity");

        WaterPoint waterPoint1 = new WaterPoint();
        waterPoint1.setWaterFunctioning("yes");
        WaterPoint waterPoint2 = new WaterPoint();
        waterPoint2.setWaterFunctioning("yes");
        WaterPoint waterPoint3 = new WaterPoint();
        waterPoint3.setWaterFunctioning("yes");
        WaterPoint waterPoint4 = new WaterPoint();
        waterPoint4.setWaterFunctioning("no");

        community.addWaterPoint(waterPoint1);
        community.addWaterPoint(waterPoint2);
        community.addWaterPoint(waterPoint3);
        community.addWaterPoint(waterPoint4);

        assertEquals(community.getBrokenWaterPointsPercentage(), Float.valueOf(25));
    }

    @Test
    public void testAddWaterPoint_WaterGetsAdded(){
        Community community = new Community();
        community.setName("Test Commuity");

        WaterPoint waterPoint1 = new WaterPoint();
        waterPoint1.setWaterFunctioning("yes");

        assertEquals(community.getWaterPoints().size(), 0);

        community.addWaterPoint(waterPoint1);

        assertEquals(community.getWaterPoints().size(), 1);
    }

    @Test
    public void testAddWaterPoint_BrokenWaterGetsAdded(){
        Community community = new Community();
        community.setName("Test Commuity");

        WaterPoint waterPoint1 = new WaterPoint();
        waterPoint1.setWaterFunctioning("no");

        assertEquals(community.getBrokenWaterPoints(), 0);

        community.addWaterPoint(waterPoint1);

        assertEquals(community.getBrokenWaterPoints(), 1);
    }

    @Test
    public void testAddWaterPoint_NonBrokenWaterGetsAdded(){
        Community community = new Community();
        community.setName("Test Commuity");

        WaterPoint waterPoint1 = new WaterPoint();
        waterPoint1.setWaterFunctioning("yes");

        assertEquals(community.getBrokenWaterPoints(), 0);

        community.addWaterPoint(waterPoint1);

        assertEquals(community.getBrokenWaterPoints(), 0);
    }
}
