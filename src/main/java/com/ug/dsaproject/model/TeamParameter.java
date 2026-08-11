package com.ug.dsaproject.model;

/**
 * Represents a row from data/team_parameters.csv — one team member's
 * derived algorithm parameters. Use YOUR row as the input parameter(s)
 * for the structure/algorithm you own, per the project brief's requirement
 * that parameters be derived from index numbers.
 */
public class TeamParameter {
    private final String memberName;
    private final String indexNumber;
    private final String assignedRole;
    private final int priorityWeight;
    private final double routePenaltyFactor;
    private final int hashTableSize;
    private final int randomSeed;

    public TeamParameter(String memberName, String indexNumber, String assignedRole,
                          int priorityWeight, double routePenaltyFactor,
                          int hashTableSize, int randomSeed) {
        this.memberName = memberName;
        this.indexNumber = indexNumber;
        this.assignedRole = assignedRole;
        this.priorityWeight = priorityWeight;
        this.routePenaltyFactor = routePenaltyFactor;
        this.hashTableSize = hashTableSize;
        this.randomSeed = randomSeed;
    }

    public String getMemberName() { return memberName; }
    public String getIndexNumber() { return indexNumber; }
    public String getAssignedRole() { return assignedRole; }
    public int getPriorityWeight() { return priorityWeight; }
    public double getRoutePenaltyFactor() { return routePenaltyFactor; }
    public int getHashTableSize() { return hashTableSize; }
    public int getRandomSeed() { return randomSeed; }

    @Override
    public String toString() {
        return memberName + " (" + assignedRole + ") seed=" + randomSeed;
    }
}
