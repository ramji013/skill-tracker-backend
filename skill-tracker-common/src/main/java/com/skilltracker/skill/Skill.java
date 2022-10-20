package com.skilltracker.skill;

import com.skilltracker.cqrs.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Service
public class Skill{

    @Id
    private int id;
    private String skill;
    private String isTechSkill;

}
