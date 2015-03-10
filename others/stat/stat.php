<?php

class Stat {

	private $db;
	
	function __construct($db) {
		$this->db = $db;
		date_default_timezone_set('Asia/Shanghai');
	}
	
	//返回命中率的所有数据
	function hitRate($token) {
		$names = ['history', 'day', 'hour'];
		$now = time() * 1000;
		$froms = [
			0,
			self::today(),
			$now - self::hours(1)
		];
		
		$result = [];
		
		foreach($froms as $i => $from) {
			$hit = $this->db->$token->count(['hit' => true, 'time' => ['$gte' => $from]]);
			$miss = $this->db->$token->count(['hit' => false, 'time' => ['$gte' => $from]]);
			$rate = ($hit + $miss == 0)?0:($hit / ($hit + $miss));
			$result[$names[$i]] = ['hit' => $hit, 'miss' => $miss, 'rate' => $rate];
		}
		
		return $result;
	}
	
	//这个返回最火的那个key
	function top1($token, $minutes = 1) {
		$names = ['history', 'day', 'hour', 'minute'];
		$now = time() * 1000;
		$froms = [
			0,
			self::today(),
			$now - self::hours(1),
			$now - self::minutes($minutes)
		];
		
		$result = [];
		
		foreach($froms as $i => $from) {
			$list = $this->top($token, $from, 1);
			$top = count($list) > 0 ? $list[0] : null;
			$result[$names[$i]] = $top;
		}
		
		return $result;
	}
	
	//这个返回最火的10个key
	function top10($token, $from) {
		return $this->top($token, $from, 10);
	}
	
	function top($token, $from, $limit) {
		$result = $this->db->$token->aggregate(
			['$match' => ['time' => ['$gte' => $from]]], 
			['$group' => ['_id' => '$key', 'count' => ['$sum' => 1]]],
			['$sort' => ['count' => -1]],
			['$limit' => $limit]
		);
		$list = $result['result'];
		$list1 = [];
		foreach($list as $item) {
			$list1[] = ['key' => $item['_id'], 'count' => $item['count']];	
		}
		
		return $list1;
	}
	
	static function hours($hours) {
		return $hours * 60 * 60 * 1000;
	}
	
	static function minutes($minutes) {
		return $minutes * 60 * 1000;
	}
	
	static function today() {
		return strtotime('today') * 1000;
	}
	
}
