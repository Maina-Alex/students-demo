package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * ${NAME}
 * @Since 1.0
 * @Version 1.0
 * @Author munialo.roy@ekenya.co.ke
 */
@MappedSuperclass
@Getter
@Setter
public class BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "created_on",updatable = false)
    @CreationTimestamp
    public LocalDateTime createdOn;
    @Column(name = "softDelete", columnDefinition = "char(1) default 0")
    public boolean softDelete;

    @PrePersist
    public void addData() {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        ZoneId zoneId = ZoneId.of("Africa/Nairobi");
        ZonedDateTime lusaka = zonedDateTime.withZoneSameInstant(zoneId);
        this.createdOn = lusaka.toLocalDateTime();
        this.softDelete = false;
    }
}
