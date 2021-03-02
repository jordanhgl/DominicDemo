package com.example.hlint_checks.registry;

import com.android.tools.lint.checks.TextFieldDetector;
import com.android.tools.lint.client.api.IssueRegistry;
import com.android.tools.lint.detector.api.Issue;
import com.example.hlint_checks.detectors.NumberFormatIssue;
import com.example.hlint_checks.detectors.ThreadInsDetector;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;


public class CustomIssueRegistry extends IssueRegistry {

    @NotNull
    @Override
    public List<Issue> getIssues() {
        return Arrays.asList(NumberFormatIssue.ISSUE, ThreadInsDetector.ISSUE,
                TextFieldDetector.ISSUE);
    }
}
