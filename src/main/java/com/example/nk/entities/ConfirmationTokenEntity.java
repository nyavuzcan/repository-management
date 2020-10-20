      package com.example.nk.entities;


      import javax.persistence.*;
      import java.time.LocalDate;
      import java.util.UUID;

      @Entity
      public class ConfirmationTokenEntity {
        public ConfirmationTokenEntity() {
        }


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String confirmationToken;

        private LocalDate createdDate;

        private UserEntity userEntity;

        public ConfirmationTokenEntity(UserEntity user) {
          this.userEntity = user;
          this.createdDate = LocalDate.now();
          this.confirmationToken = UUID.randomUUID().toString();
        }

        public Long getId() {
          return id;
        }

        public void setId(Long id) {
          this.id = id;
        }

        public String getConfirmationToken() {
          return confirmationToken;
        }

        public void setConfirmationToken(String confirmationToken) {
          this.confirmationToken = confirmationToken;
        }

        public LocalDate getCreatedDate() {
          return createdDate;
        }

        public void setCreatedDate(LocalDate createdDate) {
          this.createdDate = createdDate;
        }

        @OneToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER)
        @JoinColumn(nullable = false, name = "user_id")
        public UserEntity getUser() {
          return userEntity;
        }

        public void setUser(UserEntity user) {
          this.userEntity = user;
        }
      }
