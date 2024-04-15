package cl.dechallenge.apirest.backend.service;

import cl.dechallenge.apirest.backend.model.HiredEmployees;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface HiredEmployeesService {
	HiredEmployees saveHiredEmployees(HiredEmployees hiredEmployees);
	List<HiredEmployees> getAllHiredEmployees();
	HiredEmployees getHiredEmployeesById(Integer id);
	HiredEmployees updateHiredEmployees(HiredEmployees hiredEmployees, Integer id);
	void deleteHiredEmployees(Integer id);
	void uploadFile(MultipartFile file);
}
