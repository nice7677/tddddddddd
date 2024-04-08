package io.github.nice7677.tddpractice.domain;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
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



    public void goToWork(LocalDateTime now) {
        this.date = now.toLocalDate();
        this.goToWorkTime = now;
    }

    public void getOffWork(LocalDateTime now) {
        this.goToWorkTime = now;
    }

}
