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
