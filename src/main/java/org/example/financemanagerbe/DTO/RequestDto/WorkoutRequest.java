package org.example.financemanagerbe.DTO.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutRequest {
    private String title;
    private Double duration;
    private LocalDateTime createdAt = LocalDateTime.now();
}
