package io.github.nice7677.tddpractice.repository;

import io.github.nice7677.tddpractice.domain.Shiftee;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public final class ShifteeRepository {

    private final AtomicLong id = new AtomicLong();
    private final Map<Long, Shiftee> shiftees = new ConcurrentHashMap<>();

    public Shiftee save(Shiftee shiftee) {
        Long shifteeId = id.getAndIncrement();
        shiftee.setId(shifteeId);
        shiftees.put(shifteeId, shiftee);
        return shiftee;
    }

    public Shiftee findById(Long id) {
        Shiftee shiftee = shiftees.getOrDefault(id, null);
        if (Objects.isNull(shiftee)) {
            throw new IllegalArgumentException("존재 하지 않는시프티 내역 입니다.");
        }
        return shiftee;
    }

}
