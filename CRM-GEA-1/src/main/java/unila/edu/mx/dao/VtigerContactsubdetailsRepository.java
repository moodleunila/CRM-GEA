package unila.edu.mx.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import unila.edu.mx.model.VtigerContactsubdetails;


public interface VtigerContactsubdetailsRepository extends JpaRepository<VtigerContactsubdetails, Long>{
	
	VtigerContactsubdetails findByContactsubscriptionid (int contactsubscriptionid);

}
