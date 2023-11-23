package com.example.segprojet;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

    public class ManageServicesTest {

        private ManageServices manageServices;

        @Before
        public void setUp() {
            manageServices = new ManageServices();
            manageServices.listOfServices = new ArrayList<>();
            manageServices.confirm = true;
        }

        @Test
        public void testServiceDeletion() {
            // Arrange
            Services service = new Services("serviceName", "firstName", "lastName", "dob", "address", "license", "addressProof", "proofStatus", "photo");
            manageServices.listOfServices.add("serviceName \n First Name: firstName \n Last Name lastName \n Date Of Birth: dob \n Adress: address \n License Type license \n Adress Proof: addressProof \n Photo: photo \n Proof of Status: proofStatus");

            // Act
            manageServices.onItemLongClick(/* Simuler les paramètres nécessaires */);

          
            // Vérifier que la liste des services est vide après la suppression
            assertEquals(0, manageServices.listOfServices.size());
        }
    }


