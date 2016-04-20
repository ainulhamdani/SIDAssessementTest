/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.test;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 *
 * @author dhan
 */
public class Main {

    private static final String URL = "https://raw.githubusercontent.com/onaio/ona-tech/master/data/water_points.json";
    
    
    public static void main(String[] args) throws JsonProcessingException {
        Mapper mapper = new Mapper();
        Statistic statistics = mapper.map(URL);
        System.out.println(statistics.toJson());
    }
    
}
