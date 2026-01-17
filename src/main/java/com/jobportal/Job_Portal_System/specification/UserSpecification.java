package com.jobportal.Job_Portal_System.specification;

import org.springframework.data.jpa.domain.Specification;

import com.jobportal.Job_Portal_System.model.User;

public class UserSpecification {

    public static Specification<User> hasName(String name) {

        return (root, query, cb) ->
            name == null || name.isBlank()
                ? null
                : cb.like(
                    cb.lower(root.get("name")),
                    "%" + name.toLowerCase() + "%"
                );
    }

    public static Specification<User> hasEmail(String email) {

        return (root, query, cb) ->
            email == null || email.isBlank()
                ? null
                : cb.like(
                    cb.lower(root.get("email")),
                    "%" + email.toLowerCase() + "%"
                );
    }

    public static Specification<User> hasRole(Enum<?> role) {

        return (root, query, cb) ->
            role == null
                ? null
                : cb.equal(root.get("role"), role);
    }
    
}
