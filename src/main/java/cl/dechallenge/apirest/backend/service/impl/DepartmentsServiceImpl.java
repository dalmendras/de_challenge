package cl.dechallenge.apirest.backend.service.impl;

import cl.dechallenge.apirest.backend.exception.ResourceNotFoundException;
import cl.dechallenge.apirest.backend.helper.CSVHelper;
import cl.dechallenge.apirest.backend.model.Departments;
import cl.dechallenge.apirest.backend.repository.DepartamentsRepository;
import cl.dechallenge.apirest.backend.service.DepartmentsService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class DepartmentsServiceImpl implements DepartmentsService{

	private DepartamentsRepository departmentsRepository;

	public DepartmentsServiceImpl(DepartamentsRepository departmentsRepository) {
		super();
		this.departmentsRepository = departmentsRepository;
	}

	@Override
	public Departments saveDepartments(Departments departments) {
		return departmentsRepository.save(departments);
	}

	@Override
	public List<Departments> getAllDepartments() {
		return (List<Departments>) departmentsRepository.findAll();
	}

	@Override
	public Departments getDepartmentsById(Integer id) {
		return departmentsRepository.findById(id).orElseThrow(() ->
						new ResourceNotFoundException("Departments", "Id", id));
		
	}

	@Override
	public Departments updateDepartments(Departments departments, Integer id) {

		Departments existingDepartments = departmentsRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Departments", "Id", id));

		existingDepartments.setDepartments(departments.getDepartments());

		departmentsRepository.save(existingDepartments);
		return existingDepartments;
	}

	@Override
	public void deleteDepartments(Integer id) {

		departmentsRepository.findById(id).orElseThrow(() ->
								new ResourceNotFoundException("Departments", "Id", id));
		departmentsRepository.deleteById(id);
	}

	@Override
	public void uploadFile(MultipartFile file) {
		try {
			List<Departments> departments = CSVHelper.csvToDepartments(file.getInputStream());
			departmentsRepository.saveAll(departments);
		} catch (IOException e) {
			throw new RuntimeException("fail to store csv data: " + e.getMessage());
		}
	}
	
}
