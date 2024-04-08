package io.github.nice7677.tddpractice.service;

import io.github.nice7677.tddpractice.domain.Shiftee;
import io.github.nice7677.tddpractice.domain.User;
import io.github.nice7677.tddpractice.repository.ShifteeRepository;
import io.github.nice7677.tddpractice.service.dto.response.GetOffWorkResponse;
import io.github.nice7677.tddpractice.service.dto.response.GoToWorkResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class CommuteService {

    private final ShifteeRepository shifteeRepository;

    public GoToWorkResponse goToWork(User user, LocalDateTime now) {

        Shiftee goToWorkShiftee = Shiftee.builder()
                .user(user)
                .build();
        goToWorkShiftee.goToWork(now);

        shifteeRepository.save(goToWorkShiftee);

        return GoToWorkResponse.builder()
                .shifteeId(goToWorkShiftee.getId())
                .goToWorkTime(now)
                .date(now.toLocalDate())
                .success(true)
                .build();

    }

    public GetOffWorkResponse getOffWork(Long shifteeId, LocalDateTime now) {

        Shiftee shiftee = shifteeRepository.findById(shifteeId);
        shiftee.getOffWork(now);

        shifteeRepository.save(shiftee);

        return GetOffWorkResponse.builder()
                .getOffWorkTime(now)
                .date(shiftee.getDate())
                .success(true)
                .build();

    }

}
