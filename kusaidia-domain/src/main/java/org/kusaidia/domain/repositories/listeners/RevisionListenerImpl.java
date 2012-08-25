package org.kusaidia.domain.repositories.listeners;

import org.hibernate.envers.RevisionListener;
import org.kusaidia.domain.entities.Revision;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

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
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        Object principal = (auth != null) ? auth.getPrincipal() : null;
//        if(principal != null && (principal instanceof LdapUserDetailsWrapper)){
//            // principal must be of type LdapUserDetailsWrapper!!! so
//            // don't mess with the security infrastructure!!!
//            LdapUserDetailsWrapper details = (LdapUserDetailsWrapper)
//                    principal;
//            revision.setUser(details.getUser());
//        }
    }
}
