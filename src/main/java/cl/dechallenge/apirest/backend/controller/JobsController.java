package cl.dechallenge.apirest.backend.controller;

import cl.dechallenge.apirest.backend.helper.CSVHelper;
import cl.dechallenge.apirest.backend.message.ResponseMessage;
import cl.dechallenge.apirest.backend.model.Jobs;
import cl.dechallenge.apirest.backend.service.JobsService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/v1/jobs")
@ApiResponses(value = {
		@ApiResponse(code=400, message = "This is a bad request, please follow the API documentation for the proper request format."),
		@ApiResponse(code=401, message = "Due to security constraints, your access request cannot be authorized. "),
		@ApiResponse(code=500, message = "The server is down. Please make sure that the Location microservice is running.")
})
public class JobsController {

	private JobsService jobsService;

	public JobsController(JobsService jobsService) {
		super();
		this.jobsService = jobsService;
	}
	
	// build create jobs REST API
	@PostMapping()
	public ResponseEntity<Jobs> saveJobs(@RequestBody Jobs jobs){
		return new ResponseEntity<Jobs>(jobsService.saveJobs(jobs), HttpStatus.CREATED);
	}

	@PostMapping("/upload")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
		String message = "";

		if (CSVHelper.hasCSVFormat(file)) {
			try {
				jobsService.uploadFile(file);

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

	// build get all jobs REST API
	@GetMapping
	public List<Jobs> getAllJobs(){
		return jobsService.getAllJobs();
	}
	
	// build get jobs by id REST API
	// http://localhost:8080/api/jobs/1
	@GetMapping("{id}")
	public ResponseEntity<Jobs> getJobsById(@PathVariable("id") Integer jobsId){
		return new ResponseEntity<Jobs>(jobsService.getJobsById(jobsId), HttpStatus.OK);
	}
	
	// build update jobs REST API
	// http://localhost:8080/api/jobs/1
	@PutMapping("{id}")
	public ResponseEntity<Jobs> updateJobs(@PathVariable("id") Integer id
												  ,@RequestBody Jobs jobs){
		return new ResponseEntity<Jobs>(jobsService.updateJobs(jobs, id), HttpStatus.OK);
	}
	
	// build delete jobs REST API
	// http://localhost:8080/api/jobs/1
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteJobs(@PathVariable("id") Integer id){
		
		// delete jobs from DB
		jobsService.deleteJobs(id);
		
		return new ResponseEntity<String>("Jobs successfully!.", HttpStatus.OK);
	}
	
}
