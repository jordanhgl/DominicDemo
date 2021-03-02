package com.example.hlint_checks.detectors;

import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.android.tools.lint.detector.api.SourceCodeScanner;
import com.android.tools.lint.detector.api.TextFormat;
import com.intellij.psi.JavaElementVisitor;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiNewExpression;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.uast.UCallExpression;

import java.util.Arrays;
import java.util.List;

public class ThreadInsDetector extends Detector implements SourceCodeScanner {
    public static  Issue ISSUE = Issue.create(
            "ThreadInsDetector_ID",
            "ThreadInsDetector_brife_intro",
            "ThreadInsDetector_explain",
            Category.CORRECTNESS,
            9,
            Severity.ERROR,
            new Implementation(ThreadInsDetector.class, Scope.JAVA_FILE_SCOPE)
    );

    @Nullable
    @Override
    public List<String> getApplicableConstructorTypes() {
        return Arrays.asList(Thread.class.getName());
    }

    @Override
    public void visitConstructor(@NotNull JavaContext context, @NotNull UCallExpression node, @NotNull PsiMethod constructor) {
        super.visitConstructor(context, node, constructor);
        context.report(ISSUE, node, context.getLocation(node), ISSUE.getBriefDescription(TextFormat.TEXT));
    }

}
