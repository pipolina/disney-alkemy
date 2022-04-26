package com.alkemy.disney.repository.specification;

import com.alkemy.disney.dto.CharacterFilterDTO;
import com.alkemy.disney.entity.CharacterEntity;
import com.alkemy.disney.entity.MovieEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterSpecification {

    public Specification<CharacterEntity> getByFilters(CharacterFilterDTO characterFilterDTO){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(characterFilterDTO.getName())){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                "%"+characterFilterDTO.getName().toLowerCase()+"%"
                        )
                );
            }
            if (!CollectionUtils.isEmpty(characterFilterDTO.getMovie())) {
                Join<CharacterEntity, MovieEntity> join = root.join("movies", JoinType.INNER);
                Expression<MovieEntity> movieId = join.get("id");
                predicates.add(
                        movieId.in(characterFilterDTO.getMovie())
                );
            }
            if(!ObjectUtils.isEmpty(characterFilterDTO.getAge())){
                predicates.add(
                        criteriaBuilder.equal(
                                root.get("age"),
                                characterFilterDTO.getAge()
                         )
                );
            }
            query.distinct(true);
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

    }

}
