package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SkillTest {

    @Test(expected = IllegalArgumentException.class) // test should return true if verification is working
    public void ensureNullIsNotAllowed() {
        Skill skill = new Skill(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSkillCreationWithInvalidName() { // test should return true if verification is working
        String invalidName = "Skill#1";

        Skill skill = new Skill(invalidName);
    }

    @Test
    public void testSkillCreationWithValidName() { // test should return true because it's a valid name
        String ValidName = "Skill la";

        Skill skill = new Skill(ValidName);
    }

    @Test
    public void testAddSKill() {
        String ValidName = "Skill";
        Collaborator collaborator = new Collaborator(123456789);
        Skill skill = new Skill(ValidName);
        collaborator.addSkill(skill);
        assertTrue(collaborator.hasSkill("Skill"));
    }

}

