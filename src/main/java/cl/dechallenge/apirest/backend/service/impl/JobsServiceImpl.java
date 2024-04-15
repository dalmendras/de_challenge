package cl.dechallenge.apirest.backend.service.impl;

import cl.dechallenge.apirest.backend.exception.ResourceNotFoundException;
import cl.dechallenge.apirest.backend.helper.CSVHelper;
import cl.dechallenge.apirest.backend.model.Jobs;
import cl.dechallenge.apirest.backend.repository.JobsRepository;
import cl.dechallenge.apirest.backend.service.JobsService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class JobsServiceImpl implements JobsService {

	private JobsRepository jobsRepository;

	public JobsServiceImpl(JobsRepository jobsRepository) {
		super();
		this.jobsRepository = jobsRepository;
	}

	@Override
	public Jobs saveJobs(Jobs jobs) {
		return jobsRepository.save(jobs);
	}

	@Override
	public List<Jobs> getAllJobs() {
		return (List<Jobs>) jobsRepository.findAll();
	}

	@Override
	public Jobs getJobsById(Integer id) {
		return jobsRepository.findById(id).orElseThrow(() ->
						new ResourceNotFoundException("Jobs", "Id", id));
		
	}

	@Override
	public Jobs updateJobs(Jobs jobs, Integer id) {

		Jobs existingJobs = jobsRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Jobs", "Id", id));

		existingJobs.setJob(jobs.getJob());

		jobsRepository.save(existingJobs);
		return existingJobs;
	}

	@Override
	public void deleteJobs(Integer id) {

		jobsRepository.findById(id).orElseThrow(() ->
								new ResourceNotFoundException("Jobs", "Id", id));
		jobsRepository.deleteById(id);
	}

	@Override
	public void uploadFile(MultipartFile file) {
		try {
			List<Jobs> jobs = CSVHelper.csvToJobs(file.getInputStream());
			jobsRepository.saveAll(jobs);
		} catch (IOException e) {
			throw new RuntimeException("fail to store csv data: " + e.getMessage());
		}
	}
}
