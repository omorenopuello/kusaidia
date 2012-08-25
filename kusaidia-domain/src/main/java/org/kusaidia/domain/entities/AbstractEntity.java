package org.kusaidia.domain.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Base Class for all persistent entities. Any entity that extends this class
 * must be mapped to a table that defines an auto increment fields:
 * <ul>
 *     <li>
 *         <b>Id</b> the surrogate primary key for this entity
 *     </li>
 * </ul>
 *
 * @author Angel L. Villalain-Garcia
 * @since 1.0.0
 * @version 1.0.0
 */
@MappedSuperclass
public abstract class AbstractEntity {
    /**
     * The auto generated identity for this persistent object. This
     * represents the PK.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    /**
     * Default Protected Constructor used by hibernate
     */
    protected AbstractEntity(){}

    /**
     * Return the auto generated identity for this persistent object. This
     * represents the PK.
     *
     * @return the auto generated identity for this persistent object
     */
    public Long getId() {
        return this.id;
    }
}
