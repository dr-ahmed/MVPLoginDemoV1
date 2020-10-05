package com.loginapp.presenter;

import android.widget.EditText;

import com.loginapp.model.LoginAsyncTask;
import com.loginapp.model.Constants;
import com.loginapp.view.LoginActivity;

public class LoginController {

    private LoginActivity view;

    public LoginController(LoginActivity view) {
        this.view = view;
    }

    public void onClickConnectButtonController() {
        view.showProgressDialog();
        verifyUser();
    }

    private void verifyUser() {
        String login = view.getLoginEdt().getText().toString(), password = view.getPasswordEdt().getText().toString();
        LoginAsyncTask loginAsyncTask = new LoginAsyncTask(this);
        loginAsyncTask.execute(login, password);
    }

    public void onLoginResponse(String result, boolean exceptionOccurred, boolean userIsConfirmed) {
        view.dismissProgressDialog();

        if (exceptionOccurred)
            view.showMessage("Exception", "Désolé, une erreur s'est produite !\n" + "DETAILS :\n" + result);
        else {
            if (userIsConfirmed)
                view.showMessage("Bienvenue ", "Vous êtes désormais connecté ...");
            else {
                if (result.isEmpty())
                    view.showMessage("Informations incorrectes", "Login ou mot de passe incorrect !");
                else
                    view.showMessage("Erreur ", "L'entête de JSON est invalide");
            }
        }
    }
}
