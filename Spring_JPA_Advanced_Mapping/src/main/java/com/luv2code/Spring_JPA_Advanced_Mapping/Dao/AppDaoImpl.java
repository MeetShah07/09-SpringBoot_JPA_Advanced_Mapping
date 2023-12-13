package com.luv2code.Spring_JPA_Advanced_Mapping.Dao;

import com.luv2code.Spring_JPA_Advanced_Mapping.Entity.Course;
import com.luv2code.Spring_JPA_Advanced_Mapping.Entity.Instructor;
import com.luv2code.Spring_JPA_Advanced_Mapping.Entity.InstructorDetails;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppDaoImpl implements AppDao {

    private EntityManager entityManager;

    public AppDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);

    }

    @Override
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        Instructor tempInstructor = entityManager.find(Instructor.class, theId);
        entityManager.remove(tempInstructor);
    }

    @Override
    public InstructorDetails findInstructorDetailsById(int theId) {
        return entityManager.find(InstructorDetails.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailsById(int theId) {
        InstructorDetails tempInstructorDetails = entityManager.find(InstructorDetails.class, theId);
        tempInstructorDetails.getInstructor().setInstructorDetails(null);
        entityManager.remove(tempInstructorDetails);
    }

    @Override
    public List<Course> findCourseByInstructorId(int theId) {
        TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id= :data", Course.class);
        query.setParameter("data", theId);
        List<Course> courses = query.getResultList();

        return courses;
    }
}
