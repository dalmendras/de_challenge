package cl.dechallenge.apirest.backend.service;

import cl.dechallenge.apirest.backend.model.Departments;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DepartmentsService {
	Departments saveDepartments(Departments departments);
	List<Departments> getAllDepartments();
	Departments getDepartmentsById(Integer id);
	Departments updateDepartments(Departments departments, Integer id);
	void deleteDepartments(Integer id);
	void uploadFile(MultipartFile file);
}
