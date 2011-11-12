package com.crowdaccent.orchestration.gateway.amazon;

public class Overview extends ContentType {

    private String[] instructions;
    
    private String[] information;

    public String[] getInstructions() {
        return instructions;
    }

    public void setInstructions(String[] instructions) {
        this.instructions = instructions;
    }

    public String[] getInformation() {
        return this.information;
    }

    public void setInformation(String[] information) {
        this.information = information;
    }
    
}
