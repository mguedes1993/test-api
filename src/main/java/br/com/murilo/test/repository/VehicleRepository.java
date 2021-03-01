package br.com.murilo.test.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.murilo.test.abstraction.BaseRepository;
import br.com.murilo.test.entity.Vehicle;
import br.com.murilo.test.vo.VehiclesByBrandVo;
import br.com.murilo.test.vo.VehiclesByDecadeVo;

@Repository
public interface VehicleRepository extends BaseRepository<Vehicle> {
	@Query(""
			+ " SELECT new br.com.murilo.test.vo.VehiclesByBrandVo(brand.name, COUNT(vehicle.id)) "
			+ " FROM Brand brand "
			+ " LEFT JOIN brand.models model "
			+ " LEFT JOIN model.vehicles vehicle "
			+ " GROUP BY brand.name "
			+ " ORDER BY COUNT(vehicle.id) ASC ")
	List<VehiclesByBrandVo> vehiclesByBrand();
	
	@Query(""
			+ " SELECT new br.com.murilo.test.vo.VehiclesByDecadeVo(FLOOR(vehicle.year/10) * 10, COUNT(vehicle.id)) "
			+ " FROM Vehicle vehicle "
			+ " GROUP BY FLOOR(vehicle.year/10) * 10 "
			+ " ORDER BY FLOOR(vehicle.year/10) * 10 ASC ")
	List<VehiclesByDecadeVo> vehiclesByDecade();
	
	@Query("SELECT count(vehicle.id) FROM Vehicle vehicle WHERE vehicle.created >= :since")
	Long totalVehiclesSinceDate(@Param("since") Date since);
}
