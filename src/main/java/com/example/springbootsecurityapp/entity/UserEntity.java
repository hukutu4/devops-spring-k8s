package com.example.springbootsecurityapp.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Table(name = "users")
@Entity
public class UserEntity {
  @Id
  @Column(columnDefinition = "TEXT")
  private String login;
  @Column(nullable = false, columnDefinition = "TEXT")
  private String password;
}
