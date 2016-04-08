<?php
	include_once "func.php";
	$url = 'https://raw.githubusercontent.com/onaio/ona-tech/master/data/water_points.json';
	echo get_statistic($url);