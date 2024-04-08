package io.github.nice7677.tddpractice.service.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
public class GoToWorkResponse {

    private Long shifteeId;

    private LocalDate date;

    private LocalDateTime goToWorkTime;

    private Boolean success;

}
