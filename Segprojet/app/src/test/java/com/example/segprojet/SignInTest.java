package com.example.segprojet;
import android.widget.Button;
import android.widget.EditText;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.example.segprojet.SignIn;

@RunWith(RobolectricTestRunner.class)
public class SignInTest {

    @Test
    public void signInButtonClicked_shouldStartActivity() {
        // Crée une instance de l'activité SignIn
        SignIn signInActivity = Robolectric.setupActivity(SignIn.class);

        // Obtient les références des vues (EditText et Button)
        EditText usernameEditText = signInActivity.findViewById(R.id.enteredUsername);
        EditText passwordEditText = signInActivity.findViewById(R.id.enteredPassword);
        Button signInButton = signInActivity.findViewById(R.id.SignInBtn);

        // Vérifie que les vues ne sont pas nulles
        assertNotNull(usernameEditText);
        assertNotNull(passwordEditText);
        assertNotNull(signInButton);

        // Effectue une action simulée (dans ce cas, cliquer sur le bouton de connexion)
        signInButton.performClick();

        // Ici, vous pouvez ajouter des assertions supplémentaires en fonction du comportement que vous attendez
        // par exemple, vérifier si une nouvelle activité est démarrée après le clic sur le bouton de connexion
        // ...

        // Assurez-vous de vérifier les éléments que vous souhaitez après l'action simulée.
    }
}
