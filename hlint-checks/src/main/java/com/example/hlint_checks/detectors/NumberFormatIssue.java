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
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiClassType;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiMethodCallExpression;
import com.intellij.psi.PsiType;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.uast.UCallExpression;
import org.jetbrains.uast.UCatchClause;
import org.jetbrains.uast.UTryExpression;
import org.jetbrains.uast.UastUtils;

import java.util.Arrays;
import java.util.List;

public class NumberFormatIssue extends Detector implements SourceCodeScanner {

    public static Issue ISSUE = Issue.create(
            "NumberFormatIssue_ID",
            "NumberFormatIssue_brife",
            "NumberFormatIssue_explaination",
            Category.CORRECTNESS,
            10,
            Severity.ERROR,
            new Implementation(NumberFormatIssue.class, Scope.JAVA_FILE_SCOPE)

    );

    @Nullable
    @Override
    public List<String> getApplicableMethodNames() {
        return Arrays.asList("parseFloat", "valueOf");
    }

    @Override
    public void visitMethod(@NotNull JavaContext context, @NotNull UCallExpression node, @NotNull PsiMethod method) {
        super.visitMethod(context, node, method);
        UTryExpression tryExpression = UastUtils.getParentOfType(node, true, UTryExpression.class);
        if (tryExpression != null) {
            List<UCatchClause> clauses = tryExpression.getCatchClauses();
            for (UCatchClause clause: clauses) {
                if (containTargetException(clause, "java.lang.NumberFormatException")) {
                    context.report(NumberFormatIssue.ISSUE, context.getLocation(node), ISSUE.getBriefDescription(TextFormat.TEXT));
                }
            }
        }
    }

    private boolean containTargetException(UCatchClause clause, String exceptionName) {
        List<PsiType> types =  clause.getTypes();
        for (PsiType type: types) {
            if (type instanceof PsiClassType) {
                PsiClass pClass = ((PsiClassType)type).resolve();
                if (pClass != null && pClass.getQualifiedName().equals(exceptionName)) {
                    return true;
                }
             }
        }
        return false;

    }

}
