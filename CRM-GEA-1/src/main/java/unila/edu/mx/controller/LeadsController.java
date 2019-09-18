package unila.edu.mx.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import unila.edu.mx.dao.VtigerCf858Repository;
import unila.edu.mx.dao.VtigerCf864Repository;
import unila.edu.mx.dao.VtigerContactdetailsRepository;
import unila.edu.mx.dao.VtigerContactscfRepository;
import unila.edu.mx.dao.VtigerContactsubdetailsRepository;
import unila.edu.mx.enums.CompuestoNombres;
import unila.edu.mx.model.ContactoGEA;
import unila.edu.mx.model.Importacion;
import unila.edu.mx.model.TockenVtiger;
import unila.edu.mx.model.VtigerCf858;
import unila.edu.mx.model.VtigerContactdetails;
import unila.edu.mx.model.VtigerContactscf;
import unila.edu.mx.model.VtigerContactsubdetails;
import unila.edu.mx.model.create.UserLite;
import unila.edu.mx.model.create.VtigerCreate;
import unila.edu.mx.model.login.VtigerLogin;

@RestController
public class LeadsController {
	
	@Autowired VtigerContactdetailsRepository vtigerContactdetailsRepository;
	@Autowired VtigerCf864Repository vtigerCf864Repository;
	@Autowired VtigerContactsubdetailsRepository vtigerContactsubdetailsRepository;
	@Autowired VtigerContactscfRepository vtigerContactscfRepository; 
	@Autowired VtigerCf858Repository vtigerCf858Repository;
	
	private List<VtigerContactdetails> leads = new ArrayList<VtigerContactdetails>(0);
	
	private List<Importacion> importaciones = new ArrayList<Importacion>(0);
	
	private TockenVtiger tokenVtiger = new TockenVtiger();
	
	private final String ACCESSKEY = "gHw7tZuSjJwp9m3j";
	
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
	
	@RequestMapping(value="/vtiger/tocken/", method = RequestMethod.GET)
	public TockenVtiger getTocken() {
		TockenVtiger tokenVtiger2 = this.obtenerTocken();		
		return tokenVtiger2;
	}
	
	
	@PostMapping(path="/vtiger/login")
	public VtigerLogin getLogin() throws NoSuchAlgorithmException {		
		VtigerLogin login = new VtigerLogin();
		TockenVtiger token;
		token = this.getTocken();
		login = this.obtenerLogin(token);
		return login;
	}
	
	@PostMapping(path="/vtiger/create")
	@ResponseStatus(HttpStatus.CREATED)
	public VtigerCreate createLead(@RequestBody UserLite userLite) throws NoSuchAlgorithmException{		
		VtigerCreate create = new VtigerCreate();
		TockenVtiger tocken = new TockenVtiger();
		tocken = this.obtenerTocken();
		VtigerLogin logueo = this.obtenerLogin(tocken);
		
		//Operación
		RestTemplate plantilla = new RestTemplate();		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("operation","create");	
		map.add("sessionName", logueo.getResult().getSessionName());		
		map.add("element", this.crearCadenaJSON(userLite));		
		map.add("elementType", "Leads");		
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);		
		create = plantilla.postForObject("http://save.unila.edu.mx/vtigercrm/webservice.php", entity , VtigerCreate.class);		
		return create;
	}
	
	public TockenVtiger obtenerTocken() {
		RestTemplate plantilla = new RestTemplate();
		TockenVtiger tokenVtiger2 = plantilla.getForObject("http://save.unila.edu.mx/vtigercrm/webservice.php?operation=getchallenge&username=jgonzalez", TockenVtiger.class);		
		return tokenVtiger2;		
	}
	
	public VtigerLogin obtenerLogin(TockenVtiger token) throws NoSuchAlgorithmException {
		VtigerLogin login = new VtigerLogin();
		RestTemplate plantilla = new RestTemplate();		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("operation","login");	
		map.add("username", "jgonzalez");		
		map.add("accessKey", md5X(token.getResult().getToken() + this.ACCESSKEY));		
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);		
		login = plantilla.postForObject("http://save.unila.edu.mx/vtigercrm/webservice.php", entity , VtigerLogin.class);
		return login;
	}
	
	public void vaciar() {
		this.leads.clear();
		this.importaciones.clear();
	}
	
	public String md5X(String plaintext) throws NoSuchAlgorithmException {
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.reset();
		m.update(plaintext.getBytes());
		byte[] digest = m.digest();
		BigInteger bigInt = new BigInteger(1,digest);
		String hashtext = bigInt.toString(16);
		// Now we need to zero pad it if you actually want the full 32 chars.
		while(hashtext.length() < 32 ){
		  hashtext = "0"+hashtext;
		}
		return hashtext;
	}	
	
	private String crearCadenaJSON(UserLite userLite) {
		StringBuilder json = new StringBuilder();
		json.append("{\"firstname\": \"");
		json.append(userLite.getNombre()).append("\",");
		json.append("\"lastname\": \"");
		if(!userLite.getaPaterno().equals(""))
			json.append(userLite.getaPaterno()).append("\",");
		else
			json.append("preguntar nombre\",");
		json.append("\"email\": \"");
		json.append(userLite.getCorreo()).append("\",");
		json.append("\"phone\": \"");
		if(!userLite.getTelefono().equals(""))
			json.append(userLite.getTelefono()).append("\",");
		else
			json.append("0000000000\",");
		json.append("\"leadsource\": \"Facebook\",");
		json.append("\"leadstatus\": \"Lead\",");
		json.append("\"cf_864\": \"");
		json.append(userLite.getCarrera()).append("\",");
		json.append("\"cf_858\": \"");
		json.append(userLite.getCampus()).append("\",");
		json.append("\"cf_860\": \"");
		json.append(userLite.getNivel()).append("\",");
		json.append("\"cf_860\": \"ABC\",");
		json.append("\"assigned_user_id\": \"20x8\"}");
		return json.toString();
	}
}
