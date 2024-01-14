package com.scarlxrd.challengePicpay.services;

import com.scarlxrd.challengePicpay.domain.user.User;
import com.scarlxrd.challengePicpay.dtos.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {
    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(User user, String message) throws Exception {
        String email = user.getEmail();
        NotificationDTO notificationRequest = new NotificationDTO(email,message);
//     ResponseEntity<String> notificationResponse= restTemplate.postForEntity("(https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6", notificationRequest, String.class);
//     if(!(notificationResponse.getStatusCode() == HttpStatus.OK)){
//         System.out.println("Serviço de notificação está fora do ar");
//         throw new Exception("Serviço de notificação está fora do ar");
//     }
        System.out.println("Notificação enviada para usuário");
    }
}
