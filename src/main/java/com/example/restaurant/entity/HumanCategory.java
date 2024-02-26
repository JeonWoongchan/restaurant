package com.example.restaurant.entity;


import jakarta.persistence.*;
import lombok.*;
import org.yaml.snakeyaml.events.Event;

@Table
@Entity
@Getter  @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"category_id", "name"})
public class HumanCategory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long category_id;

  @Column(name = "name")
  private String name;


}
