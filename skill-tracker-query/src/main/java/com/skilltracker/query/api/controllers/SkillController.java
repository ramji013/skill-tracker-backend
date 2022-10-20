package com.skilltracker.query.api.controllers;

import com.skilltracker.query.api.repositories.SkillRepository;
import com.skilltracker.skill.Skill;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.GET;
import java.util.List;

@RestController
@RequestMapping(path = "/skill-tracker/api/v1/skills")
@RequiredArgsConstructor
public class SkillController {

    private final SkillRepository skillRepository;

    @GetMapping()
    public ResponseEntity<List<Skill>> getAllSkill(){
        List<Skill> skillList = (List<Skill>) skillRepository.findAll();
        return ResponseEntity.ok(skillList);
    }

}
