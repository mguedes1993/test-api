package br.com.murilo.test.vo;

import lombok.Data;

@Data
public class VehiclesByDecadeVo {
	private int decade;
	private long totalVehicles;
	
	public VehiclesByDecadeVo(int decade, long totalVehicles) {
		this.decade = decade;
		this.totalVehicles = totalVehicles;
	}
}
