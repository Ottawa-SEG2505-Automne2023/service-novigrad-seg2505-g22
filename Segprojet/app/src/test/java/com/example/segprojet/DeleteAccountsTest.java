package com.example.segprojet;
import org.junit.Test;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class DeleteAccountsTest {


    @Test
    public void testDeleteAccounts_UserDeletion() {

        // Arrange
        DeleteAccounts deleteAccounts = new DeleteAccounts();
        deleteAccounts.listOfUsers = new ArrayList<>();
        deleteAccounts.listOfUsers.add("userId1 email1 type1");
        deleteAccounts.listOfUsers.add("userId2 email2 type2");
        deleteAccounts.confirm = true;

        // Act
        deleteAccounts.onItemLongClick(/* Simuler les paramètres nécessaires */);

        // Assert
        // Vérifier que la liste des utilisateurs est vide après la suppression
        assertEquals(0, deleteAccounts.listOfUsers.size());
    }
}
