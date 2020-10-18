package com.example.nk.serviceImpl;

import com.example.nk.dto.UserRequest;
import com.example.nk.entities.ConfirmationTokenEntity;
import com.example.nk.entities.UserEntity;
import com.example.nk.repository.UserRepository;
import com.example.nk.service.ConfirmationTokenService;
import com.example.nk.service.UserService;
import com.github.dozermapper.core.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
@Service
public class UserServiceImpl implements UserService {
  @Autowired
  UserRepository userRepository;
  @Autowired
  ConfirmationTokenService confirmationTokenService;
  @Autowired
  EmailSenderService emailSenderService;
  @Autowired
  Mapper mapper;

  @Override
  public UserEntity findUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  @Override
  public void saveNewUser(UserRequest userRequest) {
    final String encryptedPassword = DigestUtils.md5DigestAsHex(userRequest.getPassword().getBytes());
    userRequest.setPassword(encryptedPassword);
    UserEntity userEntity = mapper.map(userRequest,UserEntity.class);
    final UserEntity createdUser = userRepository.save(userEntity);

    final ConfirmationTokenEntity confirmationToken = new ConfirmationTokenEntity(userEntity);
    confirmationTokenService.saveConfirmationToken(confirmationToken);
  }

  @Override
  public void confirmUser(ConfirmationTokenEntity confirmationToken) {
    final UserEntity userEntity = confirmationToken.getUser();
    userEntity.setEnabled(true);
    userRepository.save(userEntity);
    confirmationTokenService.deleteConfirmationToken(confirmationToken.getId());

  }

  @Override
  public void sendConfirmationMail(String userMail, String token) {

    final SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setTo(userMail);
    mailMessage.setSubject("Mail Confirmation Link!");
    mailMessage.setFrom("<MAIL>");
    mailMessage.setText(
        "Thank you for registering. Please click on the below link to activate your account." + "http://localhost:8080/sign-up/confirm?token="
            + token);

    emailSenderService.sendEmail(mailMessage);
  }
}
