package unila.edu.mx.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import unila.edu.mx.model.VtigerContactdetails;

public interface VtigerContactdetailsRepository extends JpaRepository<VtigerContactdetails, Long>{
	
	VtigerContactdetails findByContactNo(String contactNo);
	
	List<VtigerContactdetails> findByFirstname (String firstname);
	List<VtigerContactdetails> findByLastname (String lastname);
	List<VtigerContactdetails> findByContactid (int contactid);
	
	@Query(value="SELECT contacto.* FROM vtiger_contactdetails AS contacto INNER JOIN vtiger_contactsubdetails as sub ON sub.contactsubscriptionid = contacto.contactid INNER JOIN vtiger_contactscf as scf ON scf.contactid = contacto.contactid INNER JOIN vtiger_cf_858 as campus ON campus.cf_858 = scf.cf_868  INNER JOIN vtiger_crmentity as crm ON crm.crmid = contacto.contactid WHERE scf.cf_874 = '' AND crm.deleted = 0 AND  contacto.firstname like %:firstname%  AND scf.cf_866 IN ('Cita',  'Lead', 'En Seguimiento', 'Preinscrito sin pago')", nativeQuery = true)
	List<VtigerContactdetails> findByLikeFirstname(@Param("firstname")String firstname);
	
	@Query(value="SELECT contacto.* FROM vtiger_contactdetails AS contacto INNER JOIN vtiger_contactsubdetails as sub ON sub.contactsubscriptionid = contacto.contactid INNER JOIN vtiger_contactscf as scf ON scf.contactid = contacto.contactid INNER JOIN vtiger_cf_858 as campus ON campus.cf_858 = scf.cf_868  INNER JOIN vtiger_crmentity as crm ON crm.crmid = contacto.contactid WHERE scf.cf_874 = '' AND crm.deleted = 0 AND  contacto.lastname like %:lastname% AND scf.cf_866 IN ('Cita',  'Lead', 'En Seguimiento', 'Preinscrito sin pago')", nativeQuery = true)
	List<VtigerContactdetails> findByLikeLastname(@Param("lastname") String lastname);
	
	@Query(value="SELECT contacto.* FROM vtiger_contactdetails AS contacto INNER JOIN vtiger_contactsubdetails as sub ON sub.contactsubscriptionid = contacto.contactid INNER JOIN vtiger_contactscf as scf ON scf.contactid = contacto.contactid INNER JOIN vtiger_cf_858 as campus ON campus.cf_858 = scf.cf_868  INNER JOIN vtiger_crmentity as crm ON crm.crmid = contacto.contactid WHERE scf.cf_874 = '' AND crm.deleted = 0 AND  contacto.lastname like %:paterno% AND contacto.lastname like %:materno%  AND scf.cf_866 IN ('Cita',  'Lead', 'En Seguimiento', 'Preinscrito sin pago')", nativeQuery = true)
	List<VtigerContactdetails> findByLikeLastname(@Param("paterno")String paterno, @Param("materno") String materno);
	
	@Query(value="SELECT contacto.* FROM vtiger_contactdetails AS contacto INNER JOIN vtiger_contactsubdetails as sub ON sub.contactsubscriptionid = contacto.contactid INNER JOIN vtiger_contactscf as scf ON scf.contactid = contacto.contactid INNER JOIN vtiger_cf_858 as campus ON campus.cf_858 = scf.cf_868  INNER JOIN vtiger_crmentity as crm ON crm.crmid = contacto.contactid WHERE scf.cf_874 = '' AND crm.deleted = 0 AND  contacto.firstname like %:firstname% AND contacto.lastname like %:lastname% AND scf.cf_866 IN ('Cita',  'Lead', 'En Seguimiento', 'Preinscrito sin pago')", nativeQuery = true)
	List<VtigerContactdetails> findByLikeFirstnameAndLastname(@Param("firstname")String firstname, @Param("lastname") String lastname);
	
	@Query(value="SELECT contacto.* FROM vtiger_contactdetails AS contacto INNER JOIN vtiger_contactsubdetails as sub ON sub.contactsubscriptionid = contacto.contactid INNER JOIN vtiger_contactscf as scf ON scf.contactid = contacto.contactid INNER JOIN vtiger_cf_858 as campus ON campus.cf_858 = scf.cf_868  INNER JOIN vtiger_crmentity as crm ON crm.crmid = contacto.contactid WHERE scf.cf_874 = '' AND crm.deleted = 0 AND  contacto.firstname like %:firstname% AND contacto.lastname like %:paterno% AND contacto.lastname like %:materno% AND scf.cf_866 IN ('Cita',  'Lead', 'En Seguimiento', 'Preinscrito sin pago')" , nativeQuery = true)
	List<VtigerContactdetails> findByLikeFirstnameAndLastname(@Param("firstname")String firstname, @Param("paterno") String paterno, @Param("materno") String materno);	
}

