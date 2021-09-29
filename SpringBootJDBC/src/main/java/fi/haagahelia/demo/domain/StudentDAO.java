package fi.haagahelia.demo.domain;

import java.util.List;


public interface StudentDAO {


	public List<Student> findAll();

	public Student findOne(int id); 
	
	public void save(Student student);

}
