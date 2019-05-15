package unila.edu.mx.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import unila.edu.mx.model.VtigerContactdetails;

public interface VtigerContactdetailsRepository extends JpaRepository<VtigerContactdetails, Long>{
	
	List<VtigerContactdetails> findByFirstname (String firstname);
	List<VtigerContactdetails> findByLastname (String lastname);
	List<VtigerContactdetails> findByContactid (int contactid);
	
	@Query(value="Select * from vtiger_contactdetails c where c.firstname like %:firstname%", nativeQuery = true)
	List<VtigerContactdetails> findByLikeFirstname(@Param("firstname")String firstname);
	
	@Query(value="Select * from vtiger_contactdetails c where c.firstname like %:firstname% AND c.lastname like %:lastname%", nativeQuery = true)
	List<VtigerContactdetails> findByLikeFirstnameAndLastname(@Param("firstname")String firstname, @Param("lastname") String lastname);
	
	@Query(value="Select * from vtiger_contactdetails c where c.lastname like %:lastname%", nativeQuery = true)
	List<VtigerContactdetails> findByLikeLastname(@Param("lastname") String lastname);
	
	@Query(value="Select * from vtiger_contactdetails c where c.email like %:email%", nativeQuery = true)
	List<VtigerContactdetails> findByLikeEmail(@Param("email")String email);
	
}

