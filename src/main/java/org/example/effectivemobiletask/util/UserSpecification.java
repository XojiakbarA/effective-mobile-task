package org.example.effectivemobiletask.util;

import jakarta.persistence.criteria.Predicate;
import org.example.effectivemobiletask.dto.request.FilterRequest;
import org.example.effectivemobiletask.entity.User;
import org.example.effectivemobiletask.exception.InvalidDateFormatException;
import org.springframework.data.jpa.domain.Specification;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserSpecification {
    public static Specification<User> filter(List<FilterRequest> filters) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            filters.forEach(filter -> {
                if (filter.getField().equals("phone")) {
                    Predicate predicate = criteriaBuilder.equal(
                            root.join("phones").<String>get("number"),
                            filter.getValue());
                    predicates.add(predicate);
                }
                if (filter.getField().equals("email")) {
                    Predicate predicate = criteriaBuilder.equal(
                            root.join("emails").<String>get("value"),
                            filter.getValue());
                    predicates.add(predicate);
                }
                if (filter.getField().equals("fullName")) {
                    Predicate predicate = criteriaBuilder.like(root.get("fullName"), filter.getValue() + "%");
                    predicates.add(predicate);
                }
                if (filter.getField().equals("birthDate")) {
                    Date date;
                    try {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        date = format.parse(filter.getValue());
                    } catch (ParseException e) {
                        throw new InvalidDateFormatException("birthDate");
                    }
                    Predicate predicate = criteriaBuilder.greaterThan(root.get("birthDate"), date);
                    predicates.add(predicate);
                }
            });
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
