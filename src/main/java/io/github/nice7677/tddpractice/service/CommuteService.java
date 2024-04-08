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

        Shiftee goToWorkShiftee = Shiftee.builder()
                .goToWorkTime(LocalDateTime.now())
                .date(LocalDate.now())
                .build();
        goToWorkShiftee.setUser(user);

        shifteeRepository.save(goToWorkShiftee);

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

        shifteeRepository.save(shiftee);

        return GetOffWorkResponse.builder()
                .getOffWorkTime(shiftee.getGetOffWorkTime())
                .date(shiftee.getDate())
                .success(true)
                .build();

    }

}
