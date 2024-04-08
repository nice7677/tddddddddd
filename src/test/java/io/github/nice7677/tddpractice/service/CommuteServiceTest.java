package io.github.nice7677.tddpractice.service;

import io.github.nice7677.tddpractice.domain.User;
import io.github.nice7677.tddpractice.service.dto.response.GetOffWorkResponse;
import io.github.nice7677.tddpractice.service.dto.response.GoToWorkResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CommuteServiceTest {

    @Autowired
    CommuteService commuteService;

    @DisplayName("출근을 한다.")
    @Test
    void goToWork() {

        User user = User.builder()
                .id(1L)
                .name("이진우")
                .build();

        LocalDateTime now = LocalDateTime.now();
        LocalDate today = now.toLocalDate();

        GoToWorkResponse response = commuteService.goToWork(user, now);

        assertThat(response)
                .extracting("date", "goToWorkTime", "success")
                .containsExactly(today, now, true);

        System.out.println(response);

    }

    @DisplayName("퇴근을 한다.")
    @Test
    void getOffWork() {


        User user = User.builder()
                .id(1L)
                .name("이진우")
                .build();

        LocalDateTime now = LocalDateTime.now();
        LocalDate today = now.toLocalDate();

        GoToWorkResponse goToWorkResponse = commuteService.goToWork(user, now);

        GetOffWorkResponse response = commuteService.getOffWork(goToWorkResponse.getShifteeId(), now);

        assertThat(response)
                .extracting("date", "getOffWorkTime", "success")
                .containsExactly(today, now, true);


        System.out.println(response);

    }

}
