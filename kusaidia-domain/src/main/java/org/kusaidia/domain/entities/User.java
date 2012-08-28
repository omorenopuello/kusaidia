/*
 * Kusaidia
 *
 * Copyright (c) 2012, third-party contributors as indicated by the
 * @author tags or express copyright attribution statements applied by the authors.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 */

package org.kusaidia.domain.entities;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.jasypt.hibernate4.type.EncryptedStringType;
import org.kusaidia.util.builder.ValidationAwareBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Parent class representing a user in the system. Objects of this class are
 * persisted to the User table in the underlying persistent layer.
 *
 * @author Angel L. Villalain
 * @version 1.0.0
 * @since 1.0.0
 */
@Audited
@Entity(name = "User")
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(name = "UK_user", columnNames = "username")})
@TypeDef(name = "encryptedString",
        typeClass = EncryptedStringType.class,
        parameters = {@Parameter(name = "encryptorRegisteredName",
                value = "strongHibernateStringEncryptor")})
public class User extends AbstractEntity {
    /**
     * the lenght of the {@code username} field in the database
     */
    public static final int USERNAME_LENGTH = 20;
    /**
     * the length of the {@code email} field in the database
     */
    public static final int EMAIL_LENGTH = 255;
    /**
     * the length of the {@code password} field in the database
     */
    public static final int PASSWORD_LENGTH = 100;
    /**
     * The {@linkplain Set} of {@linkplain Role} associated with this
     * {@linkplain User}
     */
    @NotEmpty
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id",
                                      referencedColumnName ="id"),
            inverseJoinColumns = @JoinColumn(name = "role_type",
                                             referencedColumnName = "type"))
    @ForeignKey(name = "FK_user_user_roles",
                inverseName = "FK_role_user_roles")
    private Set<Role> roles;
    /**
     * The user name for this {@linkplain User} persistent entity. This
     * represents an unique key.
     */
    @NotEmpty
    @Column(name = "username", updatable = false, unique = true,
            nullable = false, length = User.USERNAME_LENGTH)
    private String userName;
    /**
     * The email address for this {@linkplain User} persistent entity.
     */
    @NotNull
    @Email(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:" +
            "\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+" +
            "[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")
    @Type(type = "encryptedString")
    @Column(name = "email", updatable = true, nullable = false,
            length = User.EMAIL_LENGTH)
    private String email;
    /**
     * the last activity timestamp for this {@linkplain User}. This must be
     * updated each time the {@linkplain User} logs in or the session times
     * out.
     */
    @NotNull
    @Column(name = "last_activity", nullable = false, updatable = true,
            insertable = true)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date lastActivity;
    /**
     * the password for this {@linkplain User}
     */
    @NotEmpty
    @NotAudited
    @Column(name = "password", nullable = false, updatable = true,
            insertable = true, length = PASSWORD_LENGTH)
    private String password;

    /**
     * Default package private constructor used by hibernate
     */
    protected User(){}

    /**
     * Creates a new {@linkplain User} persistent entity based on the
     * {@linkplain Builder} specified properties.
     *
     * @param b the {@linkplain Builder} used to build this new {@linkplain
     * User}
     */
    private User(Builder b) {
        this.userName = b.userName;
        this.email = b.email;
        this.lastActivity = new Date();
        this.password = b.password;
        this.roles = new HashSet<>(b.roles);
    }

    /**
     * Returns an unmodifiable {@linkplain Set} of {@linkplain Role}
     * associated with this {@linkplain User} persistent entity.
     *
     * @return an unmodifiable  {@linkplain Set} of {@linkplain Role}
     * associated with this {@linkplain User} persistent entity.
     */
    public Set<Role> getRoles() {
        return Collections.unmodifiableSet(this.roles);
    }

//    public User addRole(Role role) {
//
//    }

    /**
     * Returns the user name for this {@linkplain User} persistent entity. This
     * represents an unique key.
     *
     * @return the user name for this {@linkplain User} persistent entity. This
     * represents an unique key.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Returns the email address for this {@linkplain User} persistent entity.
     *
     * @return the email address for this {@linkplain User} persistent entity.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the last activity timestamp for this {@linkplain User}. This
     * must be updated each time the {@linkplain User} logs in or the
     * session times out.
     *
     * @return the last activity timestamp for this {@linkplain User}. This
     * must be updated each time the {@linkplain User} logs in or the
     * session times out.
     */
    public Date getLastActivity() {
        return new Date(this.lastActivity.getTime());
    }

    /**
     * Returns the password for this {@linkplain User} persistent entity.
     *
     * @return the password for this {@linkplain User} persistent entity.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p/>
     * The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
                .append("Username", this.userName)
                .append("Password", this.password)
                .append("Email", this.email)
                .append("LastActivity", this.lastActivity).toString();
    }

    /**
     * Implementation of the {@linkplain ValidationAwareBuilder} for
     * {@linkplain User} objects.
     *
     * @author Angel L. Villalain Garcia
     * @since 1.0.0
     * @version 1.0.0
     */
    public static class Builder extends
            ValidationAwareBuilder<User, Builder> {
        /**
         * The {@linkplain Set} of {@linkplain Role} associated with this
         * {@linkplain User}
         */
        private Set<Role> roles = new HashSet<Role>();
        /**
         * The user name for this {@linkplain User} persistent entity. This
         * represents an unique key.
         */
        private String userName;
        /**
         * The email address for this {@linkplain User} persistent entity.
         */
        private String email;
        /**
         * the password for this {@linkplain User}
         */
        private String password;

        /**
         * Sets the user name for this {@linkplain User} persistent entity.
         * This represents an unique key.
         *
         * @param username the user name for this {@linkplain User}
         *                 persistent entity. This represents an unique key.
         * @return a reference to this {@linkplain Builder} for method
         * chaining.
         */
        public Builder userName(String username){
            this.userName = username;
            return this;
        }

        /**
         * Sets the email address for this {@linkplain User} persistent entity.
         *
         * @param email the email address for this {@linkplain User}
         *              persistent entity.
         * @return a reference to this {@linkplain Builder} for method
         * chaining.
         */
        public Builder email(String email) {
            this.email = email;
            return this;
        }

        /**
         * Sets the password for this {@linkplain User} persistent entity.
         *
         * @param password the password for this {@linkplain User} persistent
         *                 entity.
         * @return a reference to this {@linkplain Builder} for method
         * chaining.
         */
        public Builder password(String password){
            this.password = password;
            return this;
        }

        /**
         * Sets the {@linkplain Set} of {@linkplain Role} associated with this
         * {@linkplain User} persistent entity.
         *
         * @param roles the {@linkplain Set} of {@linkplain Role} associated
         *              with this {@linkplain User} persistent entity.
         * @return a reference to this {@linkplain Builder} for method
         * chaining.
         */
        public Builder roles(Set<Role> roles) {
            if(roles != null) {
                this.roles.addAll(roles);
            }
            return this;
        }

        /**
         * Adds a {@linkplain Role} to the {@linkplain Set} of {@linkplain
         * Role} associated with this {@linkplain User} persistent entity.
         *
         * @param role the {@linkplain Role} to add.
         * @return a reference to this {@linkplain Builder} for method
         * chaining.
         */
        public Builder role(Role role) {
            if(role != null) {
                this.roles.add(role);
            }
            return this;
        }

        /**
         * @see ValidationAwareBuilder#buildInternal()
         */
        @Override
        protected User buildInternal() {
            return new User(this);
        }
    }

}
