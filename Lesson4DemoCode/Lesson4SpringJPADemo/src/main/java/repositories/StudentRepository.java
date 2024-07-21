package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import domain.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findByName(String name);

    Student findByPhone(String phone);

    @Query("select s from Student s where s.address.city = :city")
    List<Student> findByCity(@Param("city") String city);

}
