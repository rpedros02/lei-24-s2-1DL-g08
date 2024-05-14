package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillsRepository;

public class RegisterSkillController {

    private final SkillsRepository skillsRepository;


    public RegisterSkillController() {
        this.skillsRepository = Repositories.getInstance().getSkillsRepository();

    }

    public boolean registerSkill(int skillId, String name) {
        return skillsRepository.add(skillId, name);
    }

}
