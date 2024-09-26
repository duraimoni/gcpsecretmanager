package com.rkay.cloud.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.google.cloud.secretmanager.v1.AccessSecretVersionResponse;
import com.google.cloud.secretmanager.v1.SecretManagerServiceClient;
import com.google.cloud.secretmanager.v1.SecretVersionName;

@Configuration
public class SecretManagerConfig {
	@Value("${spring.cloud.gcp.project-id}")
	private String projectId;
	
	@Value("${application.secretlist}")
	private String secrets;
	
	@PostConstruct
	public String loadSecrets() throws Exception {
		System.out.println("loading secret");
		SecretManagerServiceClient secretManagerServiceClient = SecretManagerServiceClient.create();
		String secretIds[] = secrets.split(",");
		for (String secretId : secretIds) {
			SecretVersionName secretVersionName = SecretVersionName.of(projectId, secretId, "1");
			AccessSecretVersionResponse response = secretManagerServiceClient.accessSecretVersion(secretVersionName);
			System.setProperty(secretId, response.getPayload().getData().toStringUtf8());
		}
		return "pass";
	}
}