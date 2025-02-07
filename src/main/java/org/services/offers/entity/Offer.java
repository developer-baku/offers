package org.services.offers.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "offers")
public class Offer {
  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "description", nullable = false)
  private String description;

  @Column(name = "banner")
  private String banner;

  @Column(name = "creation_date", nullable = false)
  private LocalDate creationDate;

  @Column(name = "status", nullable = false)
  private String status;
}
