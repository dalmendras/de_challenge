package cl.dechallenge.apirest.backend.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the hired_employees database table.
 *
 */
@Entity
@Table(name="hired_employees")
@NamedQuery(name="HiredEmployees.findAll", query="SELECT c FROM HiredEmployees c")
public class HiredEmployees implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;

	@Column(name="name")
	private String name;

	@Column(name="datetime")
	@Temporal(TemporalType.TIMESTAMP)
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date datetime;

	@Column(name="department_id")
	private Integer departmentId;

	@Column(name="job_id")
	private Integer jobId;

	public HiredEmployees() {
	}

	public HiredEmployees(Integer id, String name, Date datetime, Integer departmentId, Integer jobId) {
		this.id = id;
		this.name = name;
		this.datetime = datetime;
		this.departmentId = departmentId;
		this.jobId = jobId;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDatetime() {
		return this.datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}
}
