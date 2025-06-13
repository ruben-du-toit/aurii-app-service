package za.co.aurii.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityDetails {

    @Column(name = "details_schema") // <- explicitly specify the DB column name
    private String schema;

    private Integer durationMinutes;
    private Integer elapsedMinutes;

    private Double distanceKm;
    private Integer elevationGain;

    private Double avgSpeed;
    private Integer caloriesBurned;
    private Integer perceivedExertion;
    private Integer heartRateAvg;

    private Integer cadence;
    private Double strideLength;

    private Integer totalSets;
    private Integer totalReps;

    private Integer pagesRead;

    private Boolean activityAchieved;

}
