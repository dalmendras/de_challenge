package cl.dechallenge.apirest.backend.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The persistent class for the departments database table.
 *
 */
@Entity
@Table(name="departments")
@NamedQuery(name="Departments.findAll", query="SELECT c FROM Departments c")
public class Departments implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;

	@Column(name="department")
	private String department;

	public Departments() {
	}

	public Departments(Integer id, String department) {
		this.id = id;
		this.department = department;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDepartments() {
		return this.department;
	}

	public void setDepartments(String department) {
		this.department = department;
	}

}
