package com.test.uds.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "acai_has_customization",
            joinColumns = {@JoinColumn(name = "acai_id")},
            inverseJoinColumns = {@JoinColumn(name = "customization_id")})
    private Set<Customization> customizations = new HashSet<>();

}
