package com.alkemy.disney.repository.specification;

import com.alkemy.disney.dto.MovieFilterDTO;
import com.alkemy.disney.entity.GenderEntity;
import com.alkemy.disney.entity.MovieEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieSpecification {

    public Specification<MovieEntity> getByFilters(MovieFilterDTO movieFilterDTO){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(movieFilterDTO.getName())){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                "%"+movieFilterDTO.getName().toLowerCase()+"%"
                        )
                );
            }
            if (!ObjectUtils.isEmpty(movieFilterDTO.getGenderId())){
                Join<MovieEntity,GenderEntity> join = root.join("movie",JoinType.INNER);
                Expression<String> movie = join.get("gender_id");
                predicates.add(movie.in(movieFilterDTO.getGenderId())
                );
            }

            query.distinct(true);

            String orderByField = "creation_date";
            query.orderBy(
                    movieFilterDTO.isASC()?
                            criteriaBuilder.asc(root.get(orderByField)):
                            criteriaBuilder.desc(root.get(orderByField))
            );
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

    }

}
