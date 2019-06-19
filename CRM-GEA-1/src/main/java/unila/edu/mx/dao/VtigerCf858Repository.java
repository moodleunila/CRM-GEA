package unila.edu.mx.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import unila.edu.mx.model.VtigerCf858;

public interface VtigerCf858Repository extends JpaRepository<VtigerCf858, Long>{
	
	@Query("Select c from VtigerCf858 c where c.cf858 =:cf858")
	VtigerCf858 findByCampus(@Param("cf858")String cf858);
}
