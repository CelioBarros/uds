package com.test.uds.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "acai")
    @NonNull
    private Acai acai;

    @Column(name = "setup_time")
    @NonNull
    private Integer setup_time;

    @Column(name = "value")
    @NonNull
    private Integer value;
}
