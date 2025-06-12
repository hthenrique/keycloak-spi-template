package org.henrique;

import org.henrique.helper.Screen;
import org.henrique.util.GoScreen;
import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.Authenticator;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;

public class TemplateAuthenticator implements Authenticator {
    private static final String SCREEN = "screen";

    @Override
    public void authenticate(AuthenticationFlowContext context) {
        if (context.getAuthenticationSession().getUserSessionNotes().containsKey(SCREEN)){
            return;
        }
        GoScreen.template(context, Screen.MAIN);
    }

    @Override
    public void action(AuthenticationFlowContext context) {}

    @Override
    public boolean requiresUser() {
        return false;
    }

    @Override
    public boolean configuredFor(KeycloakSession session, RealmModel realm, UserModel user) {
        return true;
    }

    @Override
    public void setRequiredActions(KeycloakSession session, RealmModel realm, UserModel user) {}

    @Override
    public void close() {}
}
