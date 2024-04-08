package io.github.nice7677.tddpractice.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
public class Shiftee {

    @Setter
    private Long id;

    @Setter
    private User user;

    // 출근
    private LocalDateTime goToWorkTime;

    // 퇴근
    @Setter
    private LocalDateTime getOffWorkTime;

    private LocalDate date;

}
