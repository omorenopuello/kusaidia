package org.kusaidia.domain.entities;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Base class for all entities based on a set of predefined values, like enum,
 * to represented a fixed set of entities. Such entities are fixed, cannot be
 * modified, and cannot be deleted.
 *
 * @author Angel L. Villalain
 * @version 1.0.0
 * @since 1.0.0
 */
@MappedSuperclass
public abstract class EnumEntity<T extends Enum> {
    /**
     * the {@linkplain T} enum associated to this {@linkplain EnumEntity}
     */
    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "type", insertable = false, updatable = false,
            nullable = false)
    protected T type;

    /**
     * the description associated to this {@linkplain EnumEntity}
     */
    @Column(name = "description")
    private String description;

    /**
     * Returns the {@linkplain T} associated to this {@linkplain EnumEntity}
     *
     * @return the {@linkplain T} associated to this {@linkplain EnumEntity}
     */
    public T getType() {
        return this.type;
    }

    /**
     * Returns the description associated to this {@linkplain EnumEntity}
     *
     * @return the description associated to this {@linkplain EnumEntity}
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(3,5).append(this.type).toHashCode();
    }

    /**
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EnumEntity)) {
            return false;
        }
        EnumEntity o = (EnumEntity) obj;
        return new EqualsBuilder().
                append(this.type, o.getType()).isEquals();
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
