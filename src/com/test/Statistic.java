/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.test;

/**
 *
 * @author dhan
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Statistic {
    private int functionalWaterPoints;
    private Map<String, Integer> numberOfWaterPointsPerCommunity = new HashMap<String, Integer>();
    private Map<String, Float> communityRanking = new HashMap<String, Float>();
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("The number of water points that are functional : " + functionalWaterPoints + "\n");
        sb.append("The number of water points per community : \n");
        
        for (Map.Entry<String, Integer> entry : numberOfWaterPointsPerCommunity.entrySet()) {
            String community = entry.getKey();
            Integer numberOfWaterPoints = entry.getValue();
            sb.append(community +" : " + String.valueOf(numberOfWaterPoints) + "\n");
        }
        
        sb.append("The rank for each community by the percentage of broken water points: \n");
        for (Map.Entry<String, Float> entry : communityRanking.entrySet()) {
            String community = entry.getKey();
            Float percentageOfBrokenWaterPoints = entry.getValue();
            sb.append(community +" : " + percentageOfBrokenWaterPoints + "\n");
        }
        
        return sb.toString();
    }
    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }
    
    public int getFunctionalWaterPoints() {
        return functionalWaterPoints;
    }
    
    public void setFunctionalWaterPoints(int functionalWaterPoints) {
        this.functionalWaterPoints = functionalWaterPoints;
    }
    
    public Map<String, Integer> getNumberOfWaterPointsPerCommunity() {
        return numberOfWaterPointsPerCommunity;
    }
    
    public void setNumberOfWaterPointsPerCommunity(Map<String, Integer> numberOfWaterPointsPerCommunity) {
        this.numberOfWaterPointsPerCommunity = numberOfWaterPointsPerCommunity;
    }
    
    public Map<String, Float> getCommunityRanking() {
        return communityRanking;
    }
    
    public void setCommunityRanking(Map<String, Float> communityRanking) {
        this.communityRanking = communityRanking;
    }
}
