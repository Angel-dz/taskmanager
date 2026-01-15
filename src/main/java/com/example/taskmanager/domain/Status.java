package com.example.taskmanager.domain;

import java.util.EnumSet;
import java.util.Set;

public enum Status {
    TODO,
    IN_PROGRESS,
    DONE;

    public Set<Status> nextAllowedStatuses() {
        return switch (this) {
            case TODO -> EnumSet.of(IN_PROGRESS);
            case IN_PROGRESS -> EnumSet.of(TODO, DONE);
            case DONE -> EnumSet.of(IN_PROGRESS);
        };
    }

    public boolean canTransitionTo(Status target) {
        return nextAllowedStatuses().contains(target);
    }

}
