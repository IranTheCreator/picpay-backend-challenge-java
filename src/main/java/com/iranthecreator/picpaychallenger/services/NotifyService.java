package com.iranthecreator.picpaychallenger.services;

import com.iranthecreator.picpaychallenger.domain.user.User;
import com.iranthecreator.picpaychallenger.dto.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.awt.*;

@Service
public class NotifyService {
    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(User user, String message) throws Exception {
        String email = user.getEmail();
        NotificationDTO notificationRequest = new NotificationDTO(email, message);
//
//        ResponseEntity<String> notifyResponse = restTemplate.postForEntity("https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6", notificationRequest, String.class);
//
//        if (!(notifyResponse.getStatusCode() == HttpStatus.OK)) {
//            System.out.println("Error to send notify");
//            throw new Exception("Notify Service is Down");
//
//        }
    }
}
