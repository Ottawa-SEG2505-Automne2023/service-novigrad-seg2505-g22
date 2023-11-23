package com.example.segprojet;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class CreateServicesTest {

    @Test
    public void testCreateServices_AccountCreatedSuccessfully() {
        // Arrange
        CreateServices createServices = new CreateServices();

        // Act
        createServices.onCreate(null);  // Simule l'appel de la méthode onCreate


        assertNotNull(createServices.myRef);  // Vérifie si la référence à la base de données n'est pas nulle
    }
}

