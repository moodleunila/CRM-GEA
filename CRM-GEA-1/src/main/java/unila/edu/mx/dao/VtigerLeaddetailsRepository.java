package unila.edu.mx.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import unila.edu.mx.model.VtigerLeaddetails;

public interface VtigerLeaddetailsRepository extends JpaRepository<VtigerLeaddetails, Long>{

	List<VtigerLeaddetails> findByFirstname (String firstname);
	List<VtigerLeaddetails> findByLastname (String lastname);
}
