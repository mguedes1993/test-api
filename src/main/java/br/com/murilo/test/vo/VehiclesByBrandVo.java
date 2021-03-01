package br.com.murilo.test.vo;

import lombok.Data;

@Data
public class VehiclesByBrandVo {
	private String brandName;
	private long totalVehicles;
	
	public VehiclesByBrandVo(String brandName, long totalVehicles) {
		this.brandName = brandName;
		this.totalVehicles = totalVehicles;
	}
}
