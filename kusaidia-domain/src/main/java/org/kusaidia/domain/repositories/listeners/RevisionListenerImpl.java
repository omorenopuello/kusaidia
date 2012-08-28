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

package org.kusaidia.domain.repositories.listeners;

import org.hibernate.envers.RevisionListener;
import org.kusaidia.domain.entities.Revision;

/**
 * Implementation of the {@linkplain RevisionListener} interface to create a
 * {@linkplain Revision} entity that will
 * hold all the information related to a change made to an entity,
 * the timestamp, and the user  who made that change.
 * Take into consideration that if more additional data have to be stored in
 * the global {@linkplain Revision} entity, this implementation must reflect
 * those changes, in order to add that information, to the object being
 * persisted.
 *
 * @author Angel L. Villalain
 * @since 1.0.0
 * @author 1.0.0
 */
public class RevisionListenerImpl implements RevisionListener {
    /**
     * @see RevisionListener#newRevision(Object)
     */
    public void newRevision(Object revisionEntity) {
        Revision revision = (Revision) revisionEntity;
//        SecurityContext context = SecurityContextHolder.getContext();
//        Authentication auth = context.getAuthentication();
//        Object principal = (auth != null) ? auth.getPrincipal() : null;
//        if(principal != null && (principal instanceof LdapUserDetailsWrapper)){
//            // principal must be of type LdapUserDetailsWrapper!!! so
//            // don't mess with the security infrastructure!!!
//            LdapUserDetailsWrapper details = (LdapUserDetailsWrapper)
//                    principal;
//            revision.setUser(details.getUser());
//        }
    }
}
