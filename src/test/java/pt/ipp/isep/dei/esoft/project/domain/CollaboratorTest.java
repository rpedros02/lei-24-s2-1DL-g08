//package pt.ipp.isep.dei.esoft.project.domain;
//
//import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import pt.ipp.isep.dei.esoft.project.domain.Enums.IdDocType;
//import pt.isep.lei.esoft.auth.domain.model.Email;
//
//import java.util.Date;
//
//public class CollaboratorTest {
//    private Date birthDate;
//    private Date admissionDate;
//    private Collaborator fullCollaborator;
//    private Collaborator taxOnlyCollaborator;
//    private Collaborator emailCollaborator;
//    private Address address;
//    private Job job;
//
//
//    @BeforeEach
//    public void setUp() {
//        birthDate = new Date(100, 5, 20); // 20 June 2000
//        admissionDate = new Date(120, 0, 15); // 15 January 2020
//        address = new Address("Main Street", 10, "1234-567", "City", "Country");
//        job = new Job("Developer");
//        fullCollaborator = new Collaborator("John Doe", birthDate, admissionDate, 912345678, "john.doe@example.com", 123456789, IdDocType.PASSPORT, "A12345678", address, job);
//        taxOnlyCollaborator = new Collaborator(123456789);
//        emailCollaborator = new Collaborator(new Email("john.doe@example.com"));
//    }
//
//    @Test
//    public void testFullConstructor() {
//        assertEquals("John Doe", fullCollaborator.getName());
//        assertEquals(birthDate, fullCollaborator.getBirthDate());
//        assertEquals(admissionDate, fullCollaborator.getAdmissionDate());
//        assertEquals(912345678, fullCollaborator.getMobileNumber());
//        assertEquals("john.doe@example.com", fullCollaborator.getEmail());
//        assertEquals(123456789, fullCollaborator.getTaxPayerNumber());
//        assertEquals(IdDocType.PASSPORT, fullCollaborator.getIdDocType());
//        assertEquals("A12345678", fullCollaborator.getIdNumber());
//        assertEquals(address, fullCollaborator.getAddress());
//        assertEquals(job, fullCollaborator.getJob());
//    }
//
//    @Test
//    public void testTaxOnlyConstructor() {
//        assertEquals(123456789, taxOnlyCollaborator.getTaxPayerNumber());
//        assertEquals("no name", taxOnlyCollaborator.getName());
//        assertEquals("nomail@mail.com", taxOnlyCollaborator.getEmail());
//    }
//
//    @Test
//    public void testEmailConstructor() {
//        assertEquals("john.doe@example.com", emailCollaborator.getEmail());
//        assertEquals("no name", emailCollaborator.getName());
//    }
//
//    @Test
//    public void testNameValidation() {
//        assertTrue(Collaborator.isValidName("John Doe"));
//        assertFalse(Collaborator.isValidName("John123"));
//        assertThrows(IllegalArgumentException.class, () -> new Collaborator("", birthDate, admissionDate, 912345678, "john.doe@example.com", 123456789, IdDocType.PASSPORT, "A12345678", address, job));
//    }
//
//    @Test
//    public void testBirthDateValidation() {
//        assertThrows(IllegalArgumentException.class, () -> new Collaborator("John Doe", new Date(120, 5, 20), admissionDate, 912345678, "john.doe@example.com", 123456789, IdDocType.PASSPORT, "A12345678", address, job));
//    }
//
//    @Test
//    public void testMobileNumberValidation() {
//        assertTrue(Collaborator.isValidMobileNumber(912345678));
//        assertFalse(Collaborator.isValidMobileNumber(123));
//        assertThrows(IllegalArgumentException.class, () -> new Collaborator("John Doe", birthDate, admissionDate, 123, "john.doe@example.com", 123456789, IdDocType.PASSPORT, "A12345678", address, job));
//    }
//
//    @Test
//    public void testEmailValidation() {
//        assertTrue(Collaborator.isValidEmail("john.doe@example.com"));
//        assertFalse(Collaborator.isValidEmail("john.doe"));
//        assertThrows(IllegalArgumentException.class, () -> new Collaborator("John Doe", birthDate, admissionDate, 912345678, "john.doe", 123456789, IdDocType.PASSPORT, "A12345678", address, job));
//    }
//
//    @Test
//    public void testTaxPayerNumberValidation() {
//        assertTrue(Collaborator.isValidTaxPayerNumber("123456789"));
//        assertFalse(Collaborator.isValidTaxPayerNumber("123"));
//        assertThrows(IllegalArgumentException.class, () -> new Collaborator("John Doe", birthDate, admissionDate, 912345678, "john.doe@example.com", 123, IdDocType.PASSPORT, "A12345678", address, job));
//    }
//
//    @Test
//    public void testSkillManagement() {
//        Skill skill = new Skill("Java");
//        assertFalse(fullCollaborator.hasSkill(skill));
//        fullCollaborator.addSkill(skill);
//        assertTrue(fullCollaborator.hasSkill(skill));
//        assertTrue(fullCollaborator.hasSkill("Java"));
//    }
//
//    @Test
//    public void testEqualsAndHashCode() {
//        Collaborator another = new Collaborator("John Doe", birthDate, admissionDate, 912345678, "john.doe@example.com", 123456789, IdDocType.PASSPORT, "A12345678", address, job);
//        assertEquals(fullCollaborator, another);
//        assertEquals(fullCollaborator.hashCode(), another.hashCode());
//    }
//
//    @Test
//    public void testClone() {
//        Collaborator clone = fullCollaborator.clone();
//        assertEquals(fullCollaborator, clone);
//        assertNotSame(fullCollaborator, clone);
//    }
//
//    @Test
//    public void testToString() {
//        String expected = "Collaborator{name='John Doe', birthDate=" + birthDate + ", admissionDate=" + admissionDate + ", mobileNumber=912345678, email='john.doe@example.com', taxPayerNumber=123456789, idDocType=PASSPORT, idNumber='A12345678', address=" + address + ", job=" + job + "}";
//        assertEquals(expected, fullCollaborator.toString());
//    }
//}
