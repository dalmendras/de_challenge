package cl.dechallenge.apirest.backend.service.impl;

import cl.dechallenge.apirest.backend.exception.ResourceNotFoundException;
import cl.dechallenge.apirest.backend.helper.CSVHelper;
import cl.dechallenge.apirest.backend.model.HiredEmployees;
import cl.dechallenge.apirest.backend.repository.HiredEmployeesRepository;
import cl.dechallenge.apirest.backend.service.HiredEmployeesService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class HiredEmployeesServiceImpl implements HiredEmployeesService {

	private HiredEmployeesRepository hiredEmployeesRepository;

	public HiredEmployeesServiceImpl(HiredEmployeesRepository hiredEmployeesRepository) {
		super();
		this.hiredEmployeesRepository = hiredEmployeesRepository;
	}

	@Override
	public HiredEmployees saveHiredEmployees(HiredEmployees hiredEmployees) {
		return hiredEmployeesRepository.save(hiredEmployees);
	}

	@Override
	public List<HiredEmployees> getAllHiredEmployees() {
		return (List<HiredEmployees>) hiredEmployeesRepository.findAll();
	}

	@Override
	public HiredEmployees getHiredEmployeesById(Integer id) {
		return hiredEmployeesRepository.findById(id).orElseThrow(() ->
						new ResourceNotFoundException("HiredEmployees", "Id", id));
		
	}

	@Override
	public HiredEmployees updateHiredEmployees(HiredEmployees hiredEmployees, Integer id) {

		HiredEmployees existingHiredEmployees = hiredEmployeesRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("HiredEmployees", "Id", id));

		existingHiredEmployees.setName(existingHiredEmployees.getName());
		existingHiredEmployees.setDatetime(existingHiredEmployees.getDatetime());
		existingHiredEmployees.setDepartmentId(existingHiredEmployees.getDepartmentId());
		existingHiredEmployees.setJobId(existingHiredEmployees.getJobId());

		hiredEmployeesRepository.save(existingHiredEmployees);
		return existingHiredEmployees;
	}

	@Override
	public void deleteHiredEmployees(Integer id) {

		hiredEmployeesRepository.findById(id).orElseThrow(() ->
								new ResourceNotFoundException("HiredEmployees", "Id", id));
		hiredEmployeesRepository.deleteById(id);
	}

	@Override
	public void uploadFile(MultipartFile file) {
		try {
			List<HiredEmployees> hiredEmployees = CSVHelper.csvToHiredEmployees(file.getInputStream());
			hiredEmployeesRepository.saveAll(hiredEmployees);
		} catch (IOException e) {
			throw new RuntimeException("fail to store csv data: " + e.getMessage());
		}
	}
}
