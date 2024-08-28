package com.scm.entities;

import com.scm.helpers.Helper;
import jakarta.annotation.Generated;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class SocialLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String link;
    private String title;

    @ManyToOne
    private Contact contact;

    private Date modifiedDate;

    private String modifiedBy;
    private Date createdDate;
    private String createdBy;

    @PreUpdate
    @PrePersist
    public void updateTimeStamps()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = Helper.getEmailOfLoggedInUser(authentication);
        this.modifiedDate = new Date();
        this.modifiedBy = username;
        if(this.createdDate == null) {
            this.createdDate = new Date();
            this.createdBy = username;
        }
    }
}
