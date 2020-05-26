package com.nhy.store.waiter.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "T_CUSTOMER")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer extends BaseEntity {
    private String name;
    private String tel;
    private boolean isMemebr;
}
