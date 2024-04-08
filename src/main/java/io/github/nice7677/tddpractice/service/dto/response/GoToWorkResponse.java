package io.github.nice7677.tddpractice.service.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@ToString
@Getter
public class GoToWorkResponse {

    private Long shifteeId;

    private LocalDate date;

    private LocalDateTime getOffWorkTime;

    private Boolean success;

}
