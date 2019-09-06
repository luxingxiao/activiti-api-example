package com.lu.activiti.task.register;

import com.lu.activiti.annotation.Input;
import com.lu.activiti.task.IActivitiTask;
import javax.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

@Slf4j
public class AddUserTask implements IActivitiTask {

  @Input
  private String phoneNumber;

  @Override
  public void execute() {
    log.info("添加用户: {}", phoneNumber);
    String serverUrl = "http://10.0.40.255/auth";
    String realm = "activiti";
    String clientId = "account";
    String clientSecret = "5181e45a-ce29-48f5-acd2-a9d0c1bf1e48";
    Keycloak keycloak = KeycloakBuilder.builder().serverUrl(serverUrl).realm(realm)
        .grantType(OAuth2Constants.CLIENT_CREDENTIALS).clientId(clientId).clientSecret(clientSecret)
        .build();
    UserRepresentation user = new UserRepresentation();
    user.setEnabled(true);
    user.setUsername(phoneNumber);
    RealmResource realmResource = keycloak.realm(realm);
    UsersResource userResource = realmResource.users();

    Response response = userResource.create(user);
    log.info("Repsonse: " + response.getStatusInfo());
    log.info(response.getLocation().toString());
    String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");

    CredentialRepresentation password = new CredentialRepresentation();
    password.setTemporary(false);
    password.setType(CredentialRepresentation.PASSWORD);
    String pwdString = RandomStringUtils.random(6);
    password.setValue(pwdString);
    userResource.get(userId).resetPassword(password);
    log.info("用户{}的密码为{}", phoneNumber, pwdString);
  }
}
