package com.test.uds.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customization {

    @Id
    private Long id;

    @Column(name = "name")
    @NonNull
    private String name;

    @Column(name = "extra_time")
    @NonNull
    private Integer extra_time;

    @Column(name = "extra_value")
    @NonNull
    private Integer extra_value;

}
