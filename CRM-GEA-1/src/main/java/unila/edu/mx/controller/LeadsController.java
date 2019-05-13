package unila.edu.mx.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import unila.edu.mx.dao.VtigerCf864Repository;
import unila.edu.mx.dao.VtigerContactdetailsRepository;
import unila.edu.mx.model.Importacion;
import unila.edu.mx.model.VtigerContactdetails;

@RestController
public class LeadsController {
	
	@Autowired VtigerContactdetailsRepository vtigerContactdetailsRepository;
	@Autowired VtigerCf864Repository vtigerCf864Repository;
	
	private List<VtigerContactdetails> leads = new ArrayList<VtigerContactdetails>(0);	
	private List<Importacion> importaciones = new ArrayList<Importacion>(0);
	
	
	@RequestMapping(value = "/lead/nombre/{firstname}", method = RequestMethod.GET)
	public List<Importacion> getLeadByNombre(@PathVariable String firstname) {
		leads = vtigerContactdetailsRepository.findByLikeFirstname(firstname);
		this.generarImpotacion(leads);
		return importaciones;
	}
	
	@RequestMapping(value = "/lead/nombre/{firstname}/{lastname}", method = RequestMethod.GET)
	public List<Importacion> getLeadByNombre(@PathVariable String firstname, @PathVariable String lastname) {
		leads = vtigerContactdetailsRepository.findByLikeFirstnameAndLastname(firstname, lastname);
		this.generarImpotacion(leads);
		return importaciones;
	}	
	
	
	@RequestMapping(value = "/lead/email/{email}", method = RequestMethod.GET)
	public List<Importacion> getLeadByEmail(@PathVariable String email) {
		leads = vtigerContactdetailsRepository.findByLikeEmail(email);
		this.generarImpotacion(leads);
		return importaciones;
	}
	
	public void generarImpotacion(List<VtigerContactdetails> leads) {
		for(VtigerContactdetails contacto : leads)
			importaciones.add(new Importacion(contacto, vtigerCf864Repository.findByPrograma(contacto.getVtigerContactscf().getCf872())));
	}
}
