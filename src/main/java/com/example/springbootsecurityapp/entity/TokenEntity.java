package com.example.springbootsecurityapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "tokens")
@Entity
public class TokenEntity {
  @Id
  private String token;
  @ManyToOne
  private UserEntity user;
}
