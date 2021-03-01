package br.com.murilo.test.dto;

import lombok.Data;

@Data
public class VehiclesByBrandDto {
	private String brandName;
	private long totalVehicles;
}
