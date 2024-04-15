package cl.dechallenge.apirest.backend.controller;

import cl.dechallenge.apirest.backend.helper.CSVHelper;
import cl.dechallenge.apirest.backend.message.ResponseMessage;
import cl.dechallenge.apirest.backend.model.Departments;
import cl.dechallenge.apirest.backend.service.DepartmentsService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/v1/departments")
@ApiResponses(value = {
		@ApiResponse(code=400, message = "This is a bad request, please follow the API documentation for the proper request format."),
		@ApiResponse(code=401, message = "Due to security constraints, your access request cannot be authorized. "),
		@ApiResponse(code=500, message = "The server is down. Please make sure that the Location microservice is running.")
})
public class DepartmentsController {

	private DepartmentsService departmentsService;

	public DepartmentsController(DepartmentsService departmentsService) {
		super();
		this.departmentsService = departmentsService;
	}
	
	// build create departments REST API
	@PostMapping()
	public ResponseEntity<Departments> saveDepartments(@RequestBody Departments departments){
		return new ResponseEntity<Departments>(departmentsService.saveDepartments(departments), HttpStatus.CREATED);
	}

	@PostMapping("/upload")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
		String message = "";

		if (CSVHelper.hasCSVFormat(file)) {
			try {
				departmentsService.uploadFile(file);

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
	
	// build get all departments REST API
	@GetMapping
	public List<Departments> getAllDepartments(){
		return departmentsService.getAllDepartments();
	}
	
	// build get departments by id REST API
	// http://localhost:8080/api/departments/1
	@GetMapping("{id}")
	public ResponseEntity<Departments> getDepartmentsById(@PathVariable("id") Integer deparmentsId){
		return new ResponseEntity<Departments>(departmentsService.getDepartmentsById(deparmentsId), HttpStatus.OK);
	}
	
	// build update departments REST API
	// http://localhost:8080/api/departments/1
	@PutMapping("{id}")
	public ResponseEntity<Departments> updateDepartments(@PathVariable("id") Integer id
												  ,@RequestBody Departments departments){
		return new ResponseEntity<Departments>(departmentsService.updateDepartments(departments, id), HttpStatus.OK);
	}
	
	// build delete departments REST API
	// http://localhost:8080/api/departments/1
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteDepartments(@PathVariable("id") Integer id){
		
		// delete departments from DB
		departmentsService.deleteDepartments(id);
		
		return new ResponseEntity<String>("Departments successfully!.", HttpStatus.OK);
	}
	
}
