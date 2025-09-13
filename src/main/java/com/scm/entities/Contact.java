package com.scm.entities;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.scm.helpers.Helper;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    @Id
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String picture;
    @Column(length = 1000)
    private String description;
    private boolean favorite = false;
    private String websiteLink;
    private String linkedInLink;
    // private List<String> socialLinks=new ArrayList<>();
    private String cloudinaryImagePublicId;

    @ManyToOne
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<SocialLink> links = new ArrayList<>();

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
