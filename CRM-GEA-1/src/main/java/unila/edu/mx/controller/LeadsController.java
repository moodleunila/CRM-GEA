package unila.edu.mx.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import unila.edu.mx.dao.VtigerCf858Repository;
import unila.edu.mx.dao.VtigerCf864Repository;
import unila.edu.mx.dao.VtigerContactdetailsRepository;
import unila.edu.mx.dao.VtigerContactscfRepository;
import unila.edu.mx.dao.VtigerContactsubdetailsRepository;
import unila.edu.mx.enums.CompuestoNombres;
import unila.edu.mx.model.ContactoGEA;
import unila.edu.mx.model.Importacion;
import unila.edu.mx.model.VtigerCf858;
import unila.edu.mx.model.VtigerContactdetails;
import unila.edu.mx.model.VtigerContactscf;
import unila.edu.mx.model.VtigerContactsubdetails;

@RestController
public class LeadsController {
	
	@Autowired VtigerContactdetailsRepository vtigerContactdetailsRepository;
	@Autowired VtigerCf864Repository vtigerCf864Repository;
	@Autowired VtigerContactsubdetailsRepository vtigerContactsubdetailsRepository;
	@Autowired VtigerContactscfRepository vtigerContactscfRepository; 
	@Autowired VtigerCf858Repository vtigerCf858Repository;
	
	private List<VtigerContactdetails> leads = new ArrayList<VtigerContactdetails>(0);	
	private List<Importacion> importaciones = new ArrayList<Importacion>(0);
	
	
	@RequestMapping(value= "/lead/estatus/", method = RequestMethod.PUT)
	public ResponseEntity<ContactoGEA> actualizarEstatusGEA(@RequestBody ContactoGEA contactoGEA){
		VtigerContactdetails contacto = vtigerContactdetailsRepository.findByContactNo(contactoGEA.getContactNo());
		if (contacto != null) {
			VtigerContactscf scf = vtigerContactscfRepository.findByContactid(contacto.getContactid());
			if(scf != null) {
				
				System.out.println("scf.getContactid(): " + scf.getContactid());
				System.out.println("scf.getContactid(): " + scf.getContactid());
				int estatus = vtigerContactscfRepository.actualizaEstatusGEA(scf.getContactid(), contactoGEA.getEstatusGEA());
				System.out.println("Estatus: " + estatus);
				return new ResponseEntity<>(contactoGEA, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/lead/nombre/{name}", method = RequestMethod.GET)
	public List<Importacion> getLeadByNombre(@PathVariable String name) {	
		assert !name.isEmpty();
		this.vaciar();					
		String nombreCompuesto[] = name.split("-");		
		if(nombreCompuesto.length > 0) {
			if(nombreCompuesto.length == 1)			
					leads = vtigerContactdetailsRepository.findByLikeFirstname(nombreCompuesto[CompuestoNombres.nombre.ordinal()]);		
			else
			{				
				if(nombreCompuesto[CompuestoNombres.nombre.ordinal()].equals("")) {
					String apellidoCompuesto[] = nombreCompuesto[CompuestoNombres.aPaterno.ordinal()].split(",");
					if(apellidoCompuesto.length > 0) {
						if(apellidoCompuesto.length == 1)			
							leads = vtigerContactdetailsRepository.findByLikeLastname(apellidoCompuesto[0]);		
						else
						{
							if(apellidoCompuesto[CompuestoNombres.nombre.ordinal()].equals(""))
								leads = vtigerContactdetailsRepository.findByLikeLastname(apellidoCompuesto[1]);
							else
								leads = vtigerContactdetailsRepository.findByLikeLastname(apellidoCompuesto[0], apellidoCompuesto[1]);
						}
					}
					else
						leads = vtigerContactdetailsRepository.findByLikeFirstname("");	
				}
				else {
					String apellidoCompuesto[] = nombreCompuesto[CompuestoNombres.aPaterno.ordinal()].split(",");
					if(apellidoCompuesto.length > 0) {
						if(apellidoCompuesto.length == 1)			
							leads = vtigerContactdetailsRepository.findByLikeFirstnameAndLastname(nombreCompuesto[CompuestoNombres.nombre.ordinal()], apellidoCompuesto[0]);		
						else
						{
							if(apellidoCompuesto[0].equals(""))
								leads = vtigerContactdetailsRepository.findByLikeFirstnameAndLastname(nombreCompuesto[CompuestoNombres.nombre.ordinal()], apellidoCompuesto[1]);
							else
								leads = vtigerContactdetailsRepository.findByLikeFirstnameAndLastname(nombreCompuesto[CompuestoNombres.nombre.ordinal()], apellidoCompuesto[0], apellidoCompuesto[1]);
						}
					}
					else
						leads = vtigerContactdetailsRepository.findByLikeFirstname(nombreCompuesto[CompuestoNombres.nombre.ordinal()]);
				}
			}
		}
		else {			
			leads = vtigerContactdetailsRepository.findByLikeFirstname("");				
		}
		this.generarImpotacion(leads);
		return importaciones;			
	}
	
	
	public void generarImpotacion(List<VtigerContactdetails> leads) {
		for(VtigerContactdetails contacto : leads) {
			VtigerContactsubdetails detalles = vtigerContactsubdetailsRepository.findByContactsubscriptionid(contacto.getContactid());
			VtigerContactscf scf = vtigerContactscfRepository.findByContactid(contacto.getContactid());
			VtigerCf858 campus = vtigerCf858Repository.findByCampus(scf.getCf868());							  
			importaciones.add(new Importacion(
					contacto.getContactNo(),
					contacto.getFirstname(), 
					contacto.getLastname(), 
					contacto.getPhone(), 
					contacto.getMobile(),
					contacto.getEmail(), 
					detalles.getLeadsource(),
					campus.getCf858id(),
					scf.getCf872()));
		}
	}
	
	
	public void vaciar() {
		this.leads.clear();
		this.importaciones.clear();
	}
}
