package com.example.nk.serviceImpl;

import com.example.nk.entities.ConfirmationTokenEntity;
import com.example.nk.repository.ConfirmationTokenRepository;
import com.example.nk.service.ConfirmationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {

  @Autowired
  ConfirmationTokenRepository confirmationTokenRepository;

  @Override
  public void saveConfirmationToken(ConfirmationTokenEntity confirmationToken) {
    confirmationTokenRepository.save(confirmationToken);
  }

  @Override
  public void deleteConfirmationToken(Long id) {
    confirmationTokenRepository.deleteById(id);
  }

  @Override
  public ConfirmationTokenEntity findConfirmationTokenByTokenEntity(String token) {
    return confirmationTokenRepository.findByConfirmationToken(token);
  }


}
