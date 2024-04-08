package io.github.nice7677.tddpractice.service;

import io.github.nice7677.tddpractice.domain.Shiftee;
import io.github.nice7677.tddpractice.domain.User;
import io.github.nice7677.tddpractice.repository.ShifteeRepository;
import io.github.nice7677.tddpractice.service.dto.response.GetOffWorkResponse;
import io.github.nice7677.tddpractice.service.dto.response.GoToWorkResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class CommuteService {

    private final ShifteeRepository shifteeRepository;

    public GoToWorkResponse goToWork(User user) {

        Shiftee shiftee = Shiftee.builder()
                .user(user)
                .goToWorkTime(LocalDateTime.now())
                .date(LocalDate.now())
                .build();

        Shiftee goToWorkShiftee = shifteeRepository.save(shiftee);

        return GoToWorkResponse.builder()
                .shifteeId(goToWorkShiftee.getId())
                .getOffWorkTime(goToWorkShiftee.getGoToWorkTime())
                .date(goToWorkShiftee.getDate())
                .success(true)
                .build();

    }

    public GetOffWorkResponse getOffWork(Long shifteeId) {

        Shiftee shiftee = shifteeRepository.findById(shifteeId);
        shiftee.setGetOffWorkTime(LocalDateTime.now());

        Shiftee getOffWorkShiftee = shifteeRepository.save(shiftee);

        return GetOffWorkResponse.builder()
                .getOffWorkTime(getOffWorkShiftee.getGetOffWorkTime())
                .date(getOffWorkShiftee.getDate())
                .success(true)
                .build();

    }

}
