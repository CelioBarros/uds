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
public class Acai {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "flavor_id", unique = false, nullable = true, insertable = true, updatable = true)
    private Flavor flavor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "size_id", unique = false, nullable = true, insertable = true, updatable = true)
    private Size size;
}
