package com.example.taskmanager.domain;

import java.util.Set;

public enum Status {
    TODO,
    IN_PROGRESS,
    DONE;

    private Set<Status> allowedTransitions;

    static {
        TODO.allowedTransitions = Set.of(IN_PROGRESS);
        IN_PROGRESS.allowedTransitions = Set.of(TODO, DONE);
        DONE.allowedTransitions = Set.of(IN_PROGRESS);
    }

    public Set<Status> nextAllowedStatuses() {
        return allowedTransitions;
    }

    public boolean canTransitionTo(Status target) {
        return allowedTransitions.contains(target);
    }

}
