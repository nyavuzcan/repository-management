package com.example.nk.serviceImpl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {

  private JavaMailSender javaMailSender;

  @Override
  @Async
  public void sendEmail(SimpleMailMessage email) {
    javaMailSender.send(email);
  }
}
