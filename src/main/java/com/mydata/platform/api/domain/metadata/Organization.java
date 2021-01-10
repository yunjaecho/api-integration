package com.mydata.platform.api.domain.metadata;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "ORGANIZATION")
@Data
public class Organization {
    @Id
    @Column(name = "ORG_CODE")
    private Long orgCode;

    @Column(name = "ORG_NMAE")
    private String orgName;

    @Column(name = "ORG_TYPE")
    private String orgType;

}
