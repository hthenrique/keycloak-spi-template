package org.henrique.util;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.henrique.TemplateAuthenticatorFactory;
import org.henrique.helper.Screen;
import org.jboss.logging.Logger;
import org.keycloak.authentication.AuthenticationFlowContext;

public class GoScreen {
    private static final Logger LOGGER = Logger.getLogger(GoScreen.class);
    private static final String SCREEN = "screen";

    public static void template(AuthenticationFlowContext context, Screen screen){
        try {
            context.challenge(context.form().createForm(screen.template));
            context.getAuthenticationSession().setUserSessionNote(SCREEN, screen.toString());
        }catch (Exception e){
            String s = ExceptionUtils.getStackTrace(e);
            if (s.contains("org.keycloak.theme.DefaultThemeManager.loadTheme")) {
                LOGGER.error("[goScreen] no theme found for " + screen.template, e);
                context.getEvent().error("theme not found");
            } else {
                LOGGER.error("[goScreen] unexpected error while loading screen " + screen.template, e);
                context.getEvent().error("unexpected error: " + s);
            }
        }

    }
}
