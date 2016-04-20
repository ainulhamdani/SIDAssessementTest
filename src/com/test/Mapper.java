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

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Mapper {
    public Statistic map(String url){
        List<WaterPoint> waterPoints = this.readInputStream(url);
        List<Community> waterPointsList = this.getInfrastructureResources(waterPoints);
        Statistic stats = this.getWaterPointsStatistics(waterPointsList);
        return stats;
    } 
    public List<WaterPoint> readInputStream(String urlString){
        List<WaterPoint> response = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, false);
            URL url = new URL(urlString);
            response = mapper.readValue(url, mapper.getTypeFactory().constructCollectionType(List.class, WaterPoint.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
    public List<Community> getInfrastructureResources(List<WaterPoint> waterPoints){
        List<Community> communities = new ArrayList<Community>();
        Map<String, Community> communityWaterPointsMap = new HashMap<String, Community>();
        
        for(WaterPoint waterPoint : waterPoints) {
            String key = waterPoint.getCommunitiesVillages();
            if (communityWaterPointsMap.containsKey(key)) {
                Community community = communityWaterPointsMap.get(key);
                community.addWaterPoint(waterPoint);
            }else {
                Community community = new Community();
                community.setName(key);
                community.addWaterPoint(waterPoint);
                communityWaterPointsMap.put(key, community);
            }
        }
        communities.addAll(communityWaterPointsMap.values());
        
        Collections.sort(communities);
        return communities;
    }
    public Statistic getWaterPointsStatistics(List<Community> waterPointsList){
        Statistic waterPointStatistics = new Statistic();
        
        int functionalWaterPoints = 0;
        Map<String, Integer> numberOfWaterPointsPerCommunity = new HashMap<String, Integer>();
        Map<String, Float> communityRanking = new HashMap<String, Float>();
        
        for (Community community : waterPointsList) {
            functionalWaterPoints += community.getFunctionalPoints();
            numberOfWaterPointsPerCommunity.put(community.getName(), community.getWaterPoints().size());
            communityRanking.put(community.getName(),community.getBrokenWaterPointsPercentage());
            //System.out.println(community.getName() + " : " + community.getBrokenWaterPointsPercentage() + "%" );
        }
        waterPointStatistics.setFunctionalWaterPoints(functionalWaterPoints);
        waterPointStatistics.setNumberOfWaterPointsPerCommunity(numberOfWaterPointsPerCommunity);
        waterPointStatistics.setCommunityRanking(Mapper.sortByValue(communityRanking));
        return waterPointStatistics;
    }
    public static boolean waterPointIsBroken(WaterPoint waterPoint){
        return !waterPoint.getWaterFunctioning().equalsIgnoreCase("yes");
    }
    
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue( Map<K, V> map ){
        List<Map.Entry<K, V>> list = new LinkedList<>( map.entrySet() );
        Collections.sort( list, new Comparator<Map.Entry<K, V>>()
        {
            @Override
            public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 )
            {
                return ( o2.getValue() ).compareTo( o1.getValue() );
            }
        });
        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list)
        {
            result.put( entry.getKey(), entry.getValue() );
        }
        return result;
    }
}
