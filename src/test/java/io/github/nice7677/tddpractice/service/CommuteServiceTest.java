package io.github.nice7677.tddpractice.service;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.ConstructorPropertiesArbitraryIntrospector;
import io.github.nice7677.tddpractice.domain.Shiftee;
import io.github.nice7677.tddpractice.domain.User;
import io.github.nice7677.tddpractice.repository.ShifteeRepository;
import io.github.nice7677.tddpractice.service.dto.response.GetOffWorkResponse;
import io.github.nice7677.tddpractice.service.dto.response.GoToWorkResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CommuteServiceTest {

    @Mock
    ShifteeRepository shifteeRepository;

    @InjectMocks
    CommuteService commuteService;

    private final User user = User.builder()
            .id(1L)
            .name("이진우")
            .build();

    private final LocalDateTime now = LocalDateTime.now();
    private final LocalDate today = now.toLocalDate();
    private final Long shifteeId = 1L;

    @DisplayName("출근을 한다.")
    @Test
    void goToWork() {

        Shiftee shiftee = Shiftee.builder()
                .id(shifteeId)
                .user(user)
                .goToWorkTime(now)
                .date(now.toLocalDate())
                .build();

        given(shifteeRepository.save(any(Shiftee.class))).willReturn(shiftee);

        GoToWorkResponse response = commuteService.goToWork(user, now);

        assertThat(response)
                .extracting("shifteeId", "date", "goToWorkTime", "success")
                .containsExactly(shifteeId, today, now, true);

    }

    @DisplayName("퇴근을 한다.")
    @Test
    void getOffWork() {

        Shiftee shiftee = Shiftee.builder()
                .id(shifteeId)
                .user(user)
                .goToWorkTime(now)
                .date(now.toLocalDate())
                .build();
        shiftee.getOffWork(now);

        given(shifteeRepository.findById(shiftee.getId())).willReturn(shiftee);
        given(shifteeRepository.save(any(Shiftee.class))).willReturn(shiftee);

        GetOffWorkResponse response = commuteService.getOffWork(shiftee.getId(), now);

        assertThat(response)
                .extracting("shifteeId", "date", "getOffWorkTime", "success")
                .containsExactly(shiftee.getId(), shiftee.getDate(), now, true);

    }

    @DisplayName("퇴근 요청을 했는데 내역을 찾지 못해서 에러가 난다.")
    @Test
    void getOffWorkException() {

        given(shifteeRepository.findById(anyLong())).willThrow(new IllegalArgumentException("존재 하지 않는시프티 내역 입니다."));

        assertThatThrownBy(() -> {
            commuteService.getOffWork(shifteeId, now);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재 하지 않는시프티 내역 입니다.");

    }

}
