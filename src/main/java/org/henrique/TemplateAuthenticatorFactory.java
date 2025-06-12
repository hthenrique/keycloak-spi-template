package org.henrique;

import org.henrique.util.ParamsConst;
import org.jboss.logging.Logger;
import org.keycloak.authentication.Authenticator;
import org.keycloak.authentication.AuthenticatorFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.models.credential.PasswordCredentialModel;
import org.keycloak.provider.ProviderConfigProperty;


import java.util.ArrayList;
import java.util.List;

import static org.keycloak.models.AuthenticationExecutionModel.*;

public class TemplateAuthenticatorFactory implements AuthenticatorFactory {
    private static final Logger LOGGER = Logger.getLogger(TemplateAuthenticatorFactory.class);

    private static final TemplateAuthenticator SINGLETON = new TemplateAuthenticator();
    private static final List<ProviderConfigProperty> configProperties;

    static {
        configProperties = new ArrayList<>();

        ProviderConfigProperty uri = new ProviderConfigProperty();
        uri.setType(ProviderConfigProperty.STRING_TYPE);
        uri.setName(ParamsConst.CONFIG_API_URL);
        uri.setLabel("API URL");
        uri.setHelpText("URL da API Spring Boot para autenticação");
        configProperties.add(uri);
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        return List.copyOf(configProperties);
    }

    @Override
    public String getId() {
        return ParamsConst.PROVIDER_ID;
    }

    @Override
    public String getHelpText() {
        return "Autentica usuários consultando uma API externa Spring Boot";
    }

    @Override
    public String getDisplayType() {
        return "Custom Spring Boot Authenticator";
    }

    @Override
    public Authenticator create(KeycloakSession session) {
        return SINGLETON;
    }

    @Override
    public Requirement[] getRequirementChoices() {
        return REQUIREMENT_CHOICES.clone();
    }

    @Override
    public void init(org.keycloak.Config.Scope scope) {
        LOGGER.info("Registring factory " + ParamsConst.PROVIDER_NAME);
    }

    @Override
    public void postInit(KeycloakSessionFactory keycloakSessionFactory) {
        LOGGER.info("Registring factory " + ParamsConst.PROVIDER_NAME);
    }

    @Override
    public void close() {
        LOGGER.info("close() not implemented for " + ParamsConst.PROVIDER_NAME);
    }

    @Override
    public String getReferenceCategory() {
        return PasswordCredentialModel.TYPE;
    }

    @Override
    public boolean isConfigurable() {
        return true;
    }

    @Override
    public boolean isUserSetupAllowed() {
        return false;
    }




}
