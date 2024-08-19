package rw.app.urugendo.day.models.Bus.dayBus.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateDayBusDto {
    @NonNull
    private int nOfSeats;
    @NonNull
    private UUID schoolId;
    @NonNull
    private UUID gpsId;
    @NonNull
    private String plateNo;
}
