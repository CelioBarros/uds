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
public class Size {

    @Id
    private Long id;

    @Column(name = "name")
    @NonNull
    private String name;

    @Column(name = "value")
    @NonNull
    private Integer value;

    @Column(name = "time")
    @NonNull
    private Integer time;

}
