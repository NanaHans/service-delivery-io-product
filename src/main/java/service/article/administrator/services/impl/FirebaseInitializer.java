package service.article.administrator.services.impl;

import java.io.FileInputStream;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Service
public class FirebaseInitializer {

	@PostConstruct
	private void intDB() throws Exception {

		FileInputStream serviceAccount = new FileInputStream(
				"./smart-shopping-list-876ac-firebase-adminsdk-bzskl-072e890e3e.json");

		FirebaseOptions options;
		options = new FirebaseOptions.Builder().setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.setDatabaseUrl("https://smart-shopping-list-876ac.firebaseio.com").build();
		FirebaseApp.initializeApp(options);

	}

}
