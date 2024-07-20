package com.scm.entities;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class SocialLink {
@Id
    private String id;
    private String link;
    private String title;
    @ManyToOne
    private Contact contact;
}
