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
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class CommuteServiceTest {

    @Autowired
    CommuteService commuteService;

    private final User user = User.builder()
            .id(1L)
            .name("이진우")
            .build();

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

    }

    @DisplayName("퇴근 요청을 했는데 내역을 찾지 못해서 에러가 난다.")
    @Test
    void getOffWorkException() {

        Long shifteeId = 99L;

        assertThatThrownBy(() -> {
            commuteService.getOffWork(shifteeId);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재 하지 않는시프티 내역 입니다.");

    }

}
