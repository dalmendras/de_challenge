package cl.dechallenge.apirest.backend.service;

import cl.dechallenge.apirest.backend.model.Jobs;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface JobsService {
	Jobs saveJobs(Jobs departments);
	List<Jobs> getAllJobs();
	Jobs getJobsById(Integer id);
	Jobs updateJobs(Jobs departments, Integer id);
	void deleteJobs(Integer id);
	void uploadFile(MultipartFile file);
}
