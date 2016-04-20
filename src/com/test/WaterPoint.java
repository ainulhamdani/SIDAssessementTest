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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WaterPoint {
    @JsonProperty("communities_villages")
    private String communitiesVillages;
    @JsonProperty("water_functioning")
    private String waterFunctioning;
    public String getCommunitiesVillages() {
        return communitiesVillages;
    }
    public void setCommunitiesVillages(String communitiesVillages) {
        this.communitiesVillages = communitiesVillages;
    }
    public String getWaterFunctioning() {
        return waterFunctioning;
    }
    public void setWaterFunctioning(String waterFunctioning) {
        this.waterFunctioning = waterFunctioning;
    }
}
