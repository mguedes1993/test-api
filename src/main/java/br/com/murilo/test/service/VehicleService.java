package br.com.murilo.test.service;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.murilo.test.abstraction.BaseRepository;
import br.com.murilo.test.abstraction.BaseService;
import br.com.murilo.test.entity.Vehicle;
import br.com.murilo.test.repository.VehicleRepository;
import br.com.murilo.test.vo.VehiclesByBrandVo;
import br.com.murilo.test.vo.VehiclesByDecadeVo;

@Service
public class VehicleService extends BaseService<Vehicle> {
	
	@Autowired
	private VehicleRepository vehicleRepository;

	@Override
	public BaseRepository<Vehicle> getRepository() {
		return vehicleRepository;
	}
	
	public List<VehiclesByBrandVo> vehiclesByBrand(){
		return vehicleRepository.vehiclesByBrand();
	}
	
	public List<VehiclesByDecadeVo> vehiclesByDecade(){
		return vehicleRepository.vehiclesByDecade();
	}
	
	public Long totalVehiclesLastWeek(){
		var nowMinus1Week = Calendar.getInstance();
		nowMinus1Week = DateUtils.truncate(nowMinus1Week, Calendar.DATE);
		nowMinus1Week.add(Calendar.WEEK_OF_YEAR, -1);
		return vehicleRepository.totalVehiclesSinceDate(nowMinus1Week.getTime());
	}
}
