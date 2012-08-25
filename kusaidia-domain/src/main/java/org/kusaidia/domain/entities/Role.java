package org.kusaidia.domain.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Role Persistent entity. This object represents all the possible roles a
 * {@linkplain User} can have. This is a read only entity.
 *
 * @author Angel L. Villalain
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity(name = "Role")
@Table(name = "role", uniqueConstraints =
@UniqueConstraint(name = "UK_role_type", columnNames = "type"))
@AttributeOverrides({
        @AttributeOverride(name = "type",
                column = @Column(name = "type", updatable = false,
                        nullable = false, unique = false,
                        length = Role.TYPE_LENGTH)),
        @AttributeOverride(name = "description",
                column = @Column(name = "description", updatable = false,
                        nullable = false, unique = false,
                        length = Role.DESCRIPTION_LENGTH))
})
public class Role extends EnumEntity<Role.Type> {
    /**
     * the length of the {@code Type} database field
     */
    public static final int TYPE_LENGTH = 25;
    /**
     * the length of the {@code Description} database field
     */
    public static final int DESCRIPTION_LENGTH = 45;

    /**
     * Enum that represents the basic type of Roles an {@linkplain User} can
     * have. Each new role defined in the database must require that this object
     * is updated and its related logic be added into the system.
     *
     * @author Angel L. Villalain
     * @version 1.0.0
     * @since 1.0.0
     */
    public enum Type {
        /**
         * Role for administrator user privileges
         */
        ROLE_ADMINISTRATOR,
        /**
         * Role for incentive payer user privileges
         */
        ROLE_USER
    }
}
