
package com.szepep.zonky.hw.dto;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "url",
        "name",
        "story",
        "purpose",
        "photos",
        "userId",
        "nickName",
        "termInMonths",
        "interestRate",
        "revenueRate",
        "annuity",
        "premium",
        "rating",
        "topped",
        "amount",
        "remainingInvestment",
        "investmentRate",
        "covered",
        "reservedAmount",
        "zonkyPlusAmount",
        "datePublished",
        "published",
        "deadline",
        "myOtherInvestments",
        "borrowerRelatedInvestmentInfo",
        "investmentsCount",
        "questionsCount",
        "region",
        "mainIncomeType",
        "questionsAllowed",
        "activeLoansCount",
        "insuranceActive",
        "insuranceHistory",
        "fastcash",
        "multicash",
        "annuityWithInsurance"
})
public class Loan {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("url")
    private String url;
    @JsonProperty("name")
    private String name;
    @JsonProperty("story")
    private String story;
    @JsonProperty("purpose")
    private String purpose;
    @JsonProperty("photos")
    private List<Photo> photos = null;
    @JsonProperty("userId")
    private Integer userId;
    @JsonProperty("nickName")
    private String nickName;
    @JsonProperty("termInMonths")
    private Integer termInMonths;
    @JsonProperty("interestRate")
    private Double interestRate;
    @JsonProperty("revenueRate")
    private Double revenueRate;
    @JsonProperty("annuity")
    private Integer annuity;
    @JsonProperty("premium")
    private Object premium;
    @JsonProperty("rating")
    private String rating;
    @JsonProperty("topped")
    private Boolean topped;
    @JsonProperty("amount")
    private Integer amount;
    @JsonProperty("remainingInvestment")
    private Integer remainingInvestment;
    @JsonProperty("investmentRate")
    private Double investmentRate;
    @JsonProperty("covered")
    private Boolean covered;
    @JsonProperty("reservedAmount")
    private Integer reservedAmount;
    @JsonProperty("zonkyPlusAmount")
    private Integer zonkyPlusAmount;
    @JsonProperty("datePublished")
    private String datePublished;
    @JsonProperty("published")
    private Boolean published;
    @JsonProperty("deadline")
    private String deadline;
    @JsonProperty("myOtherInvestments")
    private Object myOtherInvestments;
    @JsonProperty("borrowerRelatedInvestmentInfo")
    private Object borrowerRelatedInvestmentInfo;
    @JsonProperty("investmentsCount")
    private Integer investmentsCount;
    @JsonProperty("questionsCount")
    private Integer questionsCount;
    @JsonProperty("region")
    private String region;
    @JsonProperty("mainIncomeType")
    private String mainIncomeType;
    @JsonProperty("questionsAllowed")
    private Boolean questionsAllowed;
    @JsonProperty("activeLoansCount")
    private Integer activeLoansCount;
    @JsonProperty("insuranceActive")
    private Boolean insuranceActive;
    @JsonProperty("insuranceHistory")
    private List<InsuranceHistory> insuranceHistory = null;
    @JsonProperty("fastcash")
    private Boolean fastcash;
    @JsonProperty("multicash")
    private Boolean multicash;
    @JsonProperty("annuityWithInsurance")
    private Integer annuityWithInsurance;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("story")
    public String getStory() {
        return story;
    }

    @JsonProperty("story")
    public void setStory(String story) {
        this.story = story;
    }

    @JsonProperty("purpose")
    public String getPurpose() {
        return purpose;
    }

    @JsonProperty("purpose")
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    @JsonProperty("photos")
    public List<Photo> getPhotos() {
        return photos;
    }

    @JsonProperty("photos")
    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    @JsonProperty("userId")
    public Integer getUserId() {
        return userId;
    }

    @JsonProperty("userId")
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @JsonProperty("nickName")
    public String getNickName() {
        return nickName;
    }

    @JsonProperty("nickName")
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @JsonProperty("termInMonths")
    public Integer getTermInMonths() {
        return termInMonths;
    }

    @JsonProperty("termInMonths")
    public void setTermInMonths(Integer termInMonths) {
        this.termInMonths = termInMonths;
    }

    @JsonProperty("interestRate")
    public Double getInterestRate() {
        return interestRate;
    }

    @JsonProperty("interestRate")
    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    @JsonProperty("revenueRate")
    public Double getRevenueRate() {
        return revenueRate;
    }

    @JsonProperty("revenueRate")
    public void setRevenueRate(Double revenueRate) {
        this.revenueRate = revenueRate;
    }

    @JsonProperty("annuity")
    public Integer getAnnuity() {
        return annuity;
    }

    @JsonProperty("annuity")
    public void setAnnuity(Integer annuity) {
        this.annuity = annuity;
    }

    @JsonProperty("premium")
    public Object getPremium() {
        return premium;
    }

    @JsonProperty("premium")
    public void setPremium(Object premium) {
        this.premium = premium;
    }

    @JsonProperty("rating")
    public String getRating() {
        return rating;
    }

    @JsonProperty("rating")
    public void setRating(String rating) {
        this.rating = rating;
    }

    @JsonProperty("topped")
    public Boolean getTopped() {
        return topped;
    }

    @JsonProperty("topped")
    public void setTopped(Boolean topped) {
        this.topped = topped;
    }

    @JsonProperty("amount")
    public Integer getAmount() {
        return amount;
    }

    @JsonProperty("amount")
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @JsonProperty("remainingInvestment")
    public Integer getRemainingInvestment() {
        return remainingInvestment;
    }

    @JsonProperty("remainingInvestment")
    public void setRemainingInvestment(Integer remainingInvestment) {
        this.remainingInvestment = remainingInvestment;
    }

    @JsonProperty("investmentRate")
    public Double getInvestmentRate() {
        return investmentRate;
    }

    @JsonProperty("investmentRate")
    public void setInvestmentRate(Double investmentRate) {
        this.investmentRate = investmentRate;
    }

    @JsonProperty("covered")
    public Boolean getCovered() {
        return covered;
    }

    @JsonProperty("covered")
    public void setCovered(Boolean covered) {
        this.covered = covered;
    }

    @JsonProperty("reservedAmount")
    public Integer getReservedAmount() {
        return reservedAmount;
    }

    @JsonProperty("reservedAmount")
    public void setReservedAmount(Integer reservedAmount) {
        this.reservedAmount = reservedAmount;
    }

    @JsonProperty("zonkyPlusAmount")
    public Integer getZonkyPlusAmount() {
        return zonkyPlusAmount;
    }

    @JsonProperty("zonkyPlusAmount")
    public void setZonkyPlusAmount(Integer zonkyPlusAmount) {
        this.zonkyPlusAmount = zonkyPlusAmount;
    }

    @JsonProperty("datePublished")
    public String getDatePublished() {
        return datePublished;
    }

    @JsonProperty("datePublished")
    public void setDatePublished(String datePublished) {
        this.datePublished = datePublished;
    }

    @JsonProperty("published")
    public Boolean getPublished() {
        return published;
    }

    @JsonProperty("published")
    public void setPublished(Boolean published) {
        this.published = published;
    }

    @JsonProperty("deadline")
    public String getDeadline() {
        return deadline;
    }

    @JsonProperty("deadline")
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    @JsonProperty("myOtherInvestments")
    public Object getMyOtherInvestments() {
        return myOtherInvestments;
    }

    @JsonProperty("myOtherInvestments")
    public void setMyOtherInvestments(Object myOtherInvestments) {
        this.myOtherInvestments = myOtherInvestments;
    }

    @JsonProperty("borrowerRelatedInvestmentInfo")
    public Object getBorrowerRelatedInvestmentInfo() {
        return borrowerRelatedInvestmentInfo;
    }

    @JsonProperty("borrowerRelatedInvestmentInfo")
    public void setBorrowerRelatedInvestmentInfo(Object borrowerRelatedInvestmentInfo) {
        this.borrowerRelatedInvestmentInfo = borrowerRelatedInvestmentInfo;
    }

    @JsonProperty("investmentsCount")
    public Integer getInvestmentsCount() {
        return investmentsCount;
    }

    @JsonProperty("investmentsCount")
    public void setInvestmentsCount(Integer investmentsCount) {
        this.investmentsCount = investmentsCount;
    }

    @JsonProperty("questionsCount")
    public Integer getQuestionsCount() {
        return questionsCount;
    }

    @JsonProperty("questionsCount")
    public void setQuestionsCount(Integer questionsCount) {
        this.questionsCount = questionsCount;
    }

    @JsonProperty("region")
    public String getRegion() {
        return region;
    }

    @JsonProperty("region")
    public void setRegion(String region) {
        this.region = region;
    }

    @JsonProperty("mainIncomeType")
    public String getMainIncomeType() {
        return mainIncomeType;
    }

    @JsonProperty("mainIncomeType")
    public void setMainIncomeType(String mainIncomeType) {
        this.mainIncomeType = mainIncomeType;
    }

    @JsonProperty("questionsAllowed")
    public Boolean getQuestionsAllowed() {
        return questionsAllowed;
    }

    @JsonProperty("questionsAllowed")
    public void setQuestionsAllowed(Boolean questionsAllowed) {
        this.questionsAllowed = questionsAllowed;
    }

    @JsonProperty("activeLoansCount")
    public Integer getActiveLoansCount() {
        return activeLoansCount;
    }

    @JsonProperty("activeLoansCount")
    public void setActiveLoansCount(Integer activeLoansCount) {
        this.activeLoansCount = activeLoansCount;
    }

    @JsonProperty("insuranceActive")
    public Boolean getInsuranceActive() {
        return insuranceActive;
    }

    @JsonProperty("insuranceActive")
    public void setInsuranceActive(Boolean insuranceActive) {
        this.insuranceActive = insuranceActive;
    }

    @JsonProperty("insuranceHistory")
    public List<InsuranceHistory> getInsuranceHistory() {
        return insuranceHistory;
    }

    @JsonProperty("insuranceHistory")
    public void setInsuranceHistory(List<InsuranceHistory> insuranceHistory) {
        this.insuranceHistory = insuranceHistory;
    }

    @JsonProperty("fastcash")
    public Boolean getFastcash() {
        return fastcash;
    }

    @JsonProperty("fastcash")
    public void setFastcash(Boolean fastcash) {
        this.fastcash = fastcash;
    }

    @JsonProperty("multicash")
    public Boolean getMulticash() {
        return multicash;
    }

    @JsonProperty("multicash")
    public void setMulticash(Boolean multicash) {
        this.multicash = multicash;
    }

    @JsonProperty("annuityWithInsurance")
    public Integer getAnnuityWithInsurance() {
        return annuityWithInsurance;
    }

    @JsonProperty("annuityWithInsurance")
    public void setAnnuityWithInsurance(Integer annuityWithInsurance) {
        this.annuityWithInsurance = annuityWithInsurance;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
