<?php
	function get_statistic($url){
		$json = file_get_contents($url);
		$obj = json_decode($json);
		$number_functional = 0;
		$number_water_point = 0;
		$community = array();
		$community_percentage = array();
		$number_water_points = array();
		foreach($obj as $wp){
			if (in_array($wp->communities_villages, $community)) {
				$water_point = $number_water_points[$wp->communities_villages];
				if($wp->water_functioning=="yes"){
					$water_point['functioning']++;
				} else {
					$water_point['not_functioning']++;
				}
				$community_percentage[$wp->communities_villages] = (float)($water_point['not_functioning']*100)/($water_point['not_functioning']+$water_point['functioning']);
				$number_water_points[$wp->communities_villages] = $water_point;
			} else {
				array_push($community,$wp->communities_villages);
				$number_water_points[$wp->communities_villages] = array();
				$water_point = array();
				if($wp->water_functioning=="yes"){
					$water_point['functioning'] = 1;
					$water_point['not_functioning'] = 0;
				} else {
					$water_point['functioning'] = 0;
					$water_point['not_functioning'] = 1;
				}
				$community_percentage[$wp->communities_villages] = (float)($water_point['not_functioning']*100)/($water_point['not_functioning']+$water_point['functioning']);
				$number_water_points[$wp->communities_villages] = $water_point;
			}
			if($wp->water_functioning=="yes"){
				$number_functional++;
			}
			$number_water_point++;
		}
		asort($community_percentage);
		
		$output = array();
		$output['number_functional'] = $number_functional;
		$output['number_water_points'] = array();
		
		foreach($community as $comm){
			$wp_num = $number_water_points[$comm];
			$data = array();
			$data[$comm] = $wp_num['functioning']+$wp_num['not_functioning'];
			array_push($output['number_water_points'],$data);
		}
		$output['community_ranking'] = array();
		
		foreach($community_percentage as $comm => $percentage){
			array_push($output['community_ranking'],$comm);
		}
		return json_encode($output);
	}