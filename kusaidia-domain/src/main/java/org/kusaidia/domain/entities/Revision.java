package org.kusaidia.domain.entities;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;
import org.kusaidia.domain.repositories.listeners.RevisionListenerImpl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Revision Persistent Object. A Revision is an object used to track changes to
 * other Domain Objects made by a user. Since this object is
 * global, (meaning also all revisions persistent object will go to the same
 * table), the Id for this object is shared across all objects.
 *
 * @author Angel L. Villalain
 * @since 1.0.0
 * @version 1.0.0
 */
@Entity(name = "Revision")
@Table(name = "revision")
@RevisionEntity(RevisionListenerImpl.class)
public class Revision {
    /**
     * The auto generated identity for this persistent object. This represents
     * the PK.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    @RevisionNumber
    private Long id;
    /**
     * The {@linkplain User} who made this revision.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "user_id", referencedColumnName = "id",
            updatable = false)
    @ForeignKey(name = "FK_user")
    private User user;
    /**
     * the {@linkplain java.util.Date} timestamp this {@linkplain Revision} was made.
     */
    @RevisionTimestamp
    @Column(name = "timestamp", updatable = false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    /**
     * Returns the auto generated identity for this persistent object.
     *
     * @return the auto generated identity for this persistent object.
     */
    public long getId(){
        return this.id;
    }

    /**
     * Returns the {@linkplain User} who made this revision.
     *
     * @return the {@linkplain User} who made this revision.
     */
    public User getUser(){
        return this.user;
    }

    /**
     * Sets the {@linkplain User} who made this revision. This method is added
     * due to a limitation on the Hibernate Envers API. Please make sure no
     * other code calls this method since this Revision persistent object
     * will not be updated.
     *
     * @param user the {@linkplain User} who made this revision.
     */
    public void setUser(User user){
        this.user = user;
    }

    /**
     * Returns the {@linkplain Date} timestamp this {@linkplain Revision} was
     * made.
     *
     * @return the {@linkplain Date} timestamp this {@linkplain Revision} was
     * made.
     */
    public Date getTimestamp(){
        return new Date(this.timestamp.getTime());
    }

    /**
     * @see Object#hashCode()
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

