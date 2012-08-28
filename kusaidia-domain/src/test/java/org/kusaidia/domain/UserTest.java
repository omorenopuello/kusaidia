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

package org.kusaidia.domain;

import org.junit.Before;
import org.junit.Test;
import org.kusaidia.domain.entities.EnumEntity;
import org.kusaidia.domain.entities.Role;
import org.kusaidia.domain.entities.User;

import javax.validation.ConstraintViolationException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author Angel L. Villala&iacute;n-Garc&iacute;a
 * @since 1.0.0
 * @version 1.0.0
 */
public class UserTest {
    private Role defaultRole;

    @Before
    public void setDefaultRole() throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException,
            InstantiationException, NoSuchFieldException {
        Constructor c = Role.class.getDeclaredConstructor();
        defaultRole = (Role) c.newInstance();
        Class clazz = EnumEntity.class;
        Field f = clazz.getDeclaredField("type");
        f.setAccessible(true);
        f.set(defaultRole, Role.Type.ROLE_USER);
        f = clazz.getDeclaredField("description");
        f.setAccessible(true);
        f.set(defaultRole, "description");
    }

    @Test
    public void testValid() throws Exception {
        User validUser = new User.Builder().userName("username")
                .email("email@email.com").password("password").role(defaultRole)
                .build();
    }

    @Test(expected = ConstraintViolationException.class)
    public void testInvalidEmail() throws Exception {
        User validUser = new User.Builder().userName("username")
                .email("emailemail.com").password("password").role(defaultRole)
                .build();
    }

    @Test(expected = ConstraintViolationException.class)
    public void testEmptyEmail() throws Exception {
        User validUser = new User.Builder().userName("username")
                .email("").password("password").role(defaultRole)
                .build();
    }

    @Test(expected = ConstraintViolationException.class)
    public void testNullEmail() throws Exception {
        User validUser = new User.Builder().userName("username")
                .email(null).password("password").role(defaultRole)
                .build();
    }

    @Test(expected = ConstraintViolationException.class)
    public void testEmptyUsername() throws Exception {
        User validUser = new User.Builder().userName("")
                .email("email@email.com").password("password").role(defaultRole)
                .build();
    }

    @Test(expected = ConstraintViolationException.class)
    public void testNullUsername() throws Exception {
        User validUser = new User.Builder().userName(null)
                .email("email@email.com").password("password").role(defaultRole)
                .build();
    }

    @Test(expected = ConstraintViolationException.class)
    public void testEmptyPassword() throws Exception {
        User validUser = new User.Builder().userName("username")
                .email("email@email.com").password("").role(defaultRole)
                .build();
    }

    @Test(expected = ConstraintViolationException.class)
    public void testNullPassword() throws Exception {
        User validUser = new User.Builder().userName("username")
                .email("email@email.com").password(null).role(defaultRole)
                .build();
    }

    @Test(expected = ConstraintViolationException.class)
    public void testEmptyRole() throws Exception {
        User validUser = new User.Builder().userName("username")
                .email("email@email.com").password("password")
                .build();
    }

    @Test(expected = ConstraintViolationException.class)
    public void testNullRole() throws Exception {
        User validUser = new User.Builder().userName("username")
                .email("email@email.com").password("password").role(null)
                .build();
    }

    @Test(expected = ConstraintViolationException.class)
    public void testNullRoles() throws Exception {
        User validUser = new User.Builder().userName("username")
                .email("email@email.com").password("password").roles(null)
                .build();
    }
}
