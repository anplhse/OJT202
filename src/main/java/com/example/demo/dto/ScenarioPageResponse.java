package com.example.demo.dto;

import java.util.List;

import com.example.demo.model.ScenarioSessionModel;

public class ScenarioPageResponse {
    private PageInfo pageInfo;
    private List<ScenarioSessionModel> content;

    public ScenarioPageResponse() {
    }

    public ScenarioPageResponse(List<ScenarioSessionModel> content, PageInfo pageInfo) {
        this.pageInfo = pageInfo;
        this.content = content;
    }

    public List<ScenarioSessionModel> getContent() {
        return content;
    }

    public void setContent(List<ScenarioSessionModel> content) {
        this.content = content;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }
}
