package com.blumental.lifesliceapp.model;

public class TagsResponse {
    private String code;

    private String success;

    private String error;

    private TagsData data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public TagsData getData() {
        return data;
    }

    public void setData(TagsData data) {
        this.data = data;
    }
}
