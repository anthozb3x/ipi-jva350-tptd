package com.ipi.jva350;

import com.ipi.jva350.model.SalarieAideADomicile;
import com.ipi.jva350.repository.SalarieAideADomicileRepository;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.util.LinkedHashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class SalarieAideDomicileTest {

    @Test
    public void testALegalementDroitADesCongesPayes_SuperieurA10() {
        //Given
        SalarieAideADomicile salarie = new SalarieAideADomicile("Wadi", LocalDate.of(2024, 1, 1),
                LocalDate.now(), 0, 0, 15, 0, 0);

        // When
        boolean result = salarie.aLegalementDroitADesCongesPayes();

        //Then
        assertTrue(result);
    }

    @Test
    public void testALegalementDroitADesCongesPayes_InferieurA10() {
        // Given
        SalarieAideADomicile salarie = new SalarieAideADomicile("Wadi", LocalDate.of(2024, 1, 1),
                LocalDate.now(), 0, 0, 8, 0, 0);

        // When
        boolean result = salarie.aLegalementDroitADesCongesPayes();

        // Then
        assertFalse(result);
    }



    @ParameterizedTest
    @CsvSource({
            "2022-01-01, 2022-12-31, 23",
            "2022-01-01, 2022-12-31, 28"
    })
    public void testCalculeJoursDeCongeDecomptesPourPlage(String dateDebutStr, String dateFinStr, int expectedDays) {
        // Given
        LocalDate dateDebut = LocalDate.parse(dateDebutStr);
        LocalDate dateFin = LocalDate.parse(dateFinStr);


        SalarieAideADomicile salarie = new SalarieAideADomicile("Jane Doe", LocalDate.of(2024, 1, 1),
                LocalDate.now(), 0, 0, 120, 0, 0);

        // When
        LinkedHashSet<LocalDate> joursDeCongeDecomptes = salarie.calculeJoursDeCongeDecomptesPourPlage(dateDebut, dateFin);

        //Then
        assertEquals(expectedDays, joursDeCongeDecomptes.size());
    }


    @Test
    public void testAjouteConge() {
        SalarieAideADomicileRepository repositoryMock = mock(SalarieAideADomicileRepository.class);

    }
}
