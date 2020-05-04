package com.chub.mode;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Entity
@Table(name = "ITEM_STOCK")
@Where(clause="SOFT_DELETE = false")
public class ItemStockEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true)
    private Long id;

    @Column(name = "ITEM_NO")
    private Long itemId;

    @Column(name = "QUANTITY")
    private Long qty;

    @Column(name = "MIN_STOCK")
    private Long minStock;

    @Column(name = "MAX_STOCK")
    private Long maxStock;


}
