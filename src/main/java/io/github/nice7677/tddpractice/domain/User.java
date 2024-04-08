package io.github.nice7677.tddpractice.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User {

    private Long id;

    private String name;

}
