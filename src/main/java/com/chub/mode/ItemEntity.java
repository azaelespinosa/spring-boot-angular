package com.chub.mode;

import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Entity
@Table(name = "ITEM")
@Where(clause="SOFT_DELETE = false")
public class ItemEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true)
    private Long id;

    @Column(name = "ITEM_NO")
    private Long itemId;

    @Column(name = "AMOUNT")
    private Long amount;

    @Column(name = "NAME")
    private String name;

    @Column(name = "INVENTORY_CODE")
    private Long invCode;

    @OneToOne(cascade=CascadeType.ALL)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "ITEM_NO" , referencedColumnName = "ITEM_NO" , insertable=false , updatable=false)
    private ItemStockEntity itemStock;

}
