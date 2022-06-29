package com.example.springbootsecurityapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "cards")
@Entity
public class CardEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(nullable = false, updatable = false, columnDefinition = "TEXT")
  private String owner;
  @Column(nullable = false, updatable = false, columnDefinition = "TEXT")
  private String number;
  private long balance;
}
