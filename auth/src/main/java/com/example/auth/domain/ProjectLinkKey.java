package com.example.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectLinkKey implements Serializable {
    private String projectId;
    private String accountKey;
    private String linkKey;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectLinkKey that = (ProjectLinkKey) o;
        return projectId.equals(that.projectId) && accountKey.equals(that.accountKey) && linkKey.equals(that.linkKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, accountKey, linkKey);
    }
}
