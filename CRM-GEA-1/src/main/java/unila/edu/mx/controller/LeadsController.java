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
import unila.edu.mx.enums.CompuestoNombres;
import unila.edu.mx.model.Importacion;
import unila.edu.mx.model.VtigerContactdetails;

@RestController
public class LeadsController {
	
	@Autowired VtigerContactdetailsRepository vtigerContactdetailsRepository;
	@Autowired VtigerCf864Repository vtigerCf864Repository;
	
	private List<VtigerContactdetails> leads = new ArrayList<VtigerContactdetails>(0);	
	private List<Importacion> importaciones = new ArrayList<Importacion>(0);
	
	
	@RequestMapping(value = "/lead/nombre/{name}", method = RequestMethod.GET)
	public List<Importacion> getLeadByNombre(@PathVariable String name) {		 
		this.vaciar();					
		String nombreCompuesto[] = name.split("-");		
		if(nombreCompuesto.length > 0) {
			if(nombreCompuesto.length == 1)			
					leads = vtigerContactdetailsRepository.findByLikeFirstname(nombreCompuesto[CompuestoNombres.nombre.ordinal()]);		
			else
			{
				if(nombreCompuesto[CompuestoNombres.nombre.ordinal()].equals(""))
					leads = vtigerContactdetailsRepository.findByLikeLastname(nombreCompuesto[CompuestoNombres.aPaterno.ordinal()]);
				else
					leads = vtigerContactdetailsRepository.findByLikeFirstnameAndLastname(nombreCompuesto[CompuestoNombres.nombre.ordinal()], nombreCompuesto[CompuestoNombres.aPaterno.ordinal()]);
			}
		}
		else {			
			leads = vtigerContactdetailsRepository.findAll();			
		}
		this.generarImpotacion(leads);
		return importaciones;
			
	}
	
	
	@RequestMapping(value = "/lead/email/{email}", method = RequestMethod.GET)
	public List<Importacion> getLeadByEmail(@PathVariable String email) {
		this.vaciar();
		leads = vtigerContactdetailsRepository.findByLikeEmail(email);
		this.generarImpotacion(leads);
		return importaciones;
	}
	
	public void generarImpotacion(List<VtigerContactdetails> leads) {
		for(VtigerContactdetails contacto : leads)
			importaciones.add(new Importacion(contacto, vtigerCf864Repository.findByPrograma(contacto.getVtigerContactscf().getCf872())));
	}
	
	public void vaciar() {
		this.leads.clear();
		this.importaciones.clear();
	}
}
