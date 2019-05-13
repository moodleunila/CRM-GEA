package unila.edu.mx.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import unila.edu.mx.model.VtigerCf864;

public interface VtigerCf864Repository extends JpaRepository<VtigerCf864, Long>{
	
	VtigerCf864 findByCf864(String cf864);
	
	@Query("Select c from VtigerCf864 c where c.cf864 =:cf864")
	VtigerCf864 findByPrograma(@Param("cf864")String cf864);

}
