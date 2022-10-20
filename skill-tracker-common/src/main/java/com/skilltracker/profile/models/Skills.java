package com.skilltracker.profile.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Skills {
    private String skill;
    private int skillLevel;
}
