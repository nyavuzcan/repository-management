package com.example.nk.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
  void uploadFile(MultipartFile file);
}
