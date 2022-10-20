package com.skilltracker.query.api.repositories;


import com.skilltracker.skill.Skill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends CrudRepository<Skill, Integer> {
}
