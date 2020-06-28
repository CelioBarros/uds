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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "acai_id", nullable = false, insertable = true, updatable = true)
    private Acai acai;

    @Column(name = "setup_time")
    @NonNull
    private Integer setup_time;

    @Column(name = "value")
    @NonNull
    private Integer value;
}
