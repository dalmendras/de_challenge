package cl.dechallenge.apirest.backend.controller;

import cl.dechallenge.apirest.backend.helper.CSVHelper;
import cl.dechallenge.apirest.backend.message.ResponseMessage;
import cl.dechallenge.apirest.backend.model.HiredEmployees;
import cl.dechallenge.apirest.backend.service.HiredEmployeesService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/v1/hiredemployees")
@ApiResponses(value = {
		@ApiResponse(code=400, message = "This is a bad request, please follow the API documentation for the proper request format."),
		@ApiResponse(code=401, message = "Due to security constraints, your access request cannot be authorized. "),
		@ApiResponse(code=500, message = "The server is down. Please make sure that the Location microservice is running.")
})
public class HiredEmployeesController {

	private HiredEmployeesService hiredEmployeesService;

	public HiredEmployeesController(HiredEmployeesService hiredEmployeesService) {
		super();
		this.hiredEmployeesService = hiredEmployeesService;
	}
	
	// build create hiredemployees REST API
	@PostMapping()
	public ResponseEntity<HiredEmployees> saveHiredEmployees(@RequestBody HiredEmployees hiredEmployees){
		return new ResponseEntity<HiredEmployees>(hiredEmployeesService.saveHiredEmployees(hiredEmployees), HttpStatus.CREATED);
	}

	@PostMapping("/upload")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
		String message = "";

		if (CSVHelper.hasCSVFormat(file)) {
			try {
				hiredEmployeesService.uploadFile(file);

				message = "Uploaded the file successfully: " + file.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			} catch (Exception e) {
				message = "Could not upload the file: " + file.getOriginalFilename() + "!";
				System.out.println(e);
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
			}
		}

		message = "Please upload a csv file!";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	}

	// build get all hiredemployees REST API
	@GetMapping
	public List<HiredEmployees> getAllHiredEmployees(){
		return hiredEmployeesService.getAllHiredEmployees();
	}
	
	// build get hiredemployees by id REST API
	// http://localhost:8080/api/hiredemployees/1
	@GetMapping("{id}")
	public ResponseEntity<HiredEmployees> getHiredEmployeesById(@PathVariable("id") Integer hiredEmployeesId){
		return new ResponseEntity<HiredEmployees>(hiredEmployeesService.getHiredEmployeesById(hiredEmployeesId), HttpStatus.OK);
	}
	
	// build update hiredemployees REST API
	// http://localhost:8080/api/hiredemployees/1
	@PutMapping("{id}")
	public ResponseEntity<HiredEmployees> updateHiredEmployees(@PathVariable("id") Integer id
												  ,@RequestBody HiredEmployees hiredEmployees){
		return new ResponseEntity<HiredEmployees>(hiredEmployeesService.updateHiredEmployees(hiredEmployees, id), HttpStatus.OK);
	}
	
	// build delete hiredemployees REST API
	// http://localhost:8080/api/hiredemployees/1
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteHiredEmployees(@PathVariable("id") Integer id){
		
		// delete hiredemployees from DB
		hiredEmployeesService.deleteHiredEmployees(id);
		
		return new ResponseEntity<String>("HiredEmployees successfully!.", HttpStatus.OK);
	}
	
}
