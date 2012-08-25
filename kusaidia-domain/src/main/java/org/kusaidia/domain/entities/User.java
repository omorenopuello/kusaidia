package org.kusaidia.domain.entities;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.hibernate.validator.constraints.NotEmpty;
import org.jasypt.digest.StringDigester;
import org.jasypt.hibernate4.type.EncryptedStringType;
import org.kusaidia.util.logging.LogLevel;
import org.kusaidia.util.logging.Loggable;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import java.util.Date;
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
@Configurable(preConstruction = true, autowire = Autowire.BY_TYPE)
public class User extends AbstractEntity {
    /**
     * the lenght of the {@code username} field in the database
     */
    public static final int USERNAME_LENGTH = 50;
    /**
     * the length of the {@code email} field in the database
     */
    public static final int EMAIL_LENGTH = 255;
    /**
     * the length of the {@code password} field in the database
     */
    public static final int PASSWORD_LENGTH = 100;
    /**
     * the {@linkplain StringDigester} used to create a digest from the
     * specified plain test password
     */
    @Autowired
    @Transient
    private StringDigester digester;

//    public void setDigester(StringDigester digester) {
//        this.digester = digester;
//    }
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
    @NotEmpty
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
    private Date lastActivity = new Date();
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

    public User(String username, String email, String password) {
        this.userName = username;
        this.email = email;
        this.password = password;
    }

    @Loggable(LogLevel.DEBUG)
    public String getUserName() {return userName;}

}
