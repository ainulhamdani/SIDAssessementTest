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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.test.Mapper;
import com.test.WaterPoint;

public class MapperTest {
    @Test
    public void testWaterPointIsBrokenTrue() {
            WaterPoint waterPoint = new WaterPoint();
            waterPoint.setWaterFunctioning("no");
            assertTrue(Mapper.waterPointIsBroken(waterPoint));
    }

    @Test
    public void testWaterPointIsBrokenFalse() {
            WaterPoint waterPoint = new WaterPoint();
            waterPoint.setWaterFunctioning("yes");
            assertFalse(Mapper.waterPointIsBroken(waterPoint));
    }
}
