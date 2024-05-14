package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class

TeamTest {
    @Test
    void ensureMinAndMaxDiffZero () {
        assertThrows(IllegalArgumentException.class, () -> new Team(0,0,null));
    }

}
