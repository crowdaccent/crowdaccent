package com.crowdaccent.orchestration.gateway.amazon;

public class Question extends ContentType{
    
    private int questionId; 
    private String displayName; 
    private boolean isRequired;
    private String[] supplementData;
    
    public int getQuestionId() {
        return questionId;
    }
    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
    public String getDisplayName() {
        return displayName;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    public boolean isRequired() {
        return isRequired;
    }
    public void setRequired(boolean isRequired) {
        this.isRequired = isRequired;
    }
    public String[] getSupplementData() {
        return this.supplementData;
    }
    public void setSupplementData(String[] supplementData) {
        this.supplementData = supplementData;
    }

}
