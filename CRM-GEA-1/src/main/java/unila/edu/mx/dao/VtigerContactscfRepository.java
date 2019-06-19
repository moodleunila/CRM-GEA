package unila.edu.mx.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import unila.edu.mx.model.VtigerContactscf;

public interface VtigerContactscfRepository extends JpaRepository<VtigerContactscf, Long>{	
	
	VtigerContactscf findByContactid(int contactid);
	
	
	@Transactional
	@Modifying
	@Query("UPDATE VtigerContactscf c SET c.cf874 = :estatusGea WHERE c.contactid = :scfId")
	int actualizaEstatusGEA(@Param("scfId") int contactid, @Param("estatusGea") String estatusGEA);
}
